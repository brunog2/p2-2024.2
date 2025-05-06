package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.validators.FriendValidator;

/**
 * Serviço responsável por gerenciar as operações relacionadas a amigos no sistema Jackut.
 */
public class FriendService {
    private UserService userService;
    private RelationshipService relationshipService;
    private FriendValidator friendValidator;

    /**
     * Construtor do FriendService.
     *
     * @param userService Serviço de usuários.
     * @param relationshipService Serviço de relacionamentos entre usuários.
     */
    public FriendService(UserService userService, RelationshipService relationshipService) {
        this.userService = userService;
        this.relationshipService = relationshipService;
        this.friendValidator = new FriendValidator(userService, relationshipService);
    }

    /**
     * Adiciona um amigo para um usuário.
     *
     * @param login Nome de usuário do remetente.
     * @param amigo Nome de usuário do amigo a ser adicionado.
     * @throws UserNotExistsException Se o usuário ou o amigo não existirem.
     * @throws EnemyUserException Se o amigo for inimigo do usuário.
     * @throws UserAlreadyRequestedAsFriendException Se o pedido de amizade já tiver sido enviado.
     * @throws UserCannotAddHimselfAsFriendException Se o usuário tentar adicionar a si mesmo como amigo.
     * @throws UserAlreadyAddedAsFriendException Se o amigo já tiver sido adicionado.
     */
    public void adicionarAmigo(String login, String amigo) throws UserNotExistsException, EnemyUserException, UserAlreadyRequestedAsFriendException, UserCannotAddHimselfAsFriendException, UserAlreadyAddedAsFriendException {
        User user = this.userService.getUser(login);
        User friend = this.userService.getUser(amigo);

        this.friendValidator.validateAddFriend(login, amigo, user, friend);

        user.getFriendsRequest().add(friend);

        if (friend.getFriendsRequest().contains(user)) {
            user.getFriendsRequest().remove(friend);
            friend.getFriendsRequest().remove(user);
            user.getFriends().add(friend);
            friend.getFriends().add(user);
        }
    }

    /**
     * Obtém a lista de amigos de um usuário.
     *
     * @param login Nome de usuário.
     * @return Uma string formatada contendo os amigos do usuário.
     * @throws UserNotFoundException Se o usuário não for encontrado.
     */
    public String getAmigos(String login) throws UserNotFoundException {
        User user = this.userService.getUser(login);

        if (user == null) throw new UserNotFoundException();

        String friends = String.join(
                ",",
                user
                        .getFriends()
                        .stream()
                        .map(User::getLogin)
                        .toArray(String[]::new)
        );

        return "{" + friends + "}";
    }

    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login Nome de usuário do remetente.
     * @param amigo Nome de usuário do amigo.
     * @param user Usuário remetente.
     * @param friend Usuário destinatário.
     * @return true se forem amigos, false caso contrário.
     */
    public boolean ehAmigo(String login, String amigo, User user, User friend) {
        return this.friendValidator.ehAmigo(login, amigo, user, friend);
    }
}