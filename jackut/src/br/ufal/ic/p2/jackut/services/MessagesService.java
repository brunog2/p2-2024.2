package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.Message;
import br.ufal.ic.p2.jackut.models.User;

import java.util.List;

/**
 * Serviço responsável pelo gerenciamento de mensagens no sistema Jackut.
 */
public class MessagesService {
    private UserService userService;
    private RelationshipService relationshipService;

    /**
     * Construtor do MessagesService.
     *
     * @param userService Serviço de usuários.
     * @param relationshipService Serviço de relacionamentos entre usuários.
     */
    public MessagesService(UserService userService, RelationshipService relationshipService) {
        this.userService = userService;
        this.relationshipService = relationshipService;
    }

    /**
     * Envia um recado de um usuário para outro.
     *
     * @param sessionId    ID da sessão do usuário remetente.
     * @param destinatario Login do usuário destinatário.
     * @param recado       Conteúdo do recado.
     * @throws UserCannotSendMessageToHimselfException Se o usuário tentar enviar um recado para si mesmo.
     * @throws EnemyUserException Se o destinatário for inimigo do remetente.
     * @throws UserNotExistsException Se o remetente ou o destinatário não existirem.
     */
    public void enviarRecado(String sessionId, String destinatario, String recado) throws UserCannotSendMessageToHimselfException, EnemyUserException, UserNotExistsException {
        User user = this.userService.getUser(sessionId);
        User receiver = this.userService.getUser(destinatario);

        if (user == null || receiver == null) throw new UserNotExistsException();

        if (user.getLogin().equals(destinatario)) {
            throw new UserCannotSendMessageToHimselfException();
        }

        if (relationshipService.isEnemy(destinatario, sessionId)) {
            throw new EnemyUserException(receiver.getNome());
        }

        Message message = new Message(recado, user, receiver);
        receiver.addMessage(message);
    }

    /**
     * Lê o próximo recado da lista de recados de um usuário.
     *
     * @param sessionId ID da sessão do usuário.
     * @return O conteúdo do recado.
     * @throws EmptyUserMessagesException Se não houver recados para o usuário.
     * @throws UserNotExistsException Se o usuário não existir.
     */
    public String lerRecado(String sessionId) throws EmptyUserMessagesException, UserNotExistsException {
        User user = this.userService.getUser(sessionId);

        if (user == null) throw new UserNotExistsException();

        List<Message> messages = user.getMessages();

        if (messages.isEmpty()) {
            throw new EmptyUserMessagesException();
        }

        String message = messages.get(0).getContent();

        messages.remove(0);

        return message;
    }
}