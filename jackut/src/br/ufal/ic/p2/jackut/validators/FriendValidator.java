package br.ufal.ic.p2.jackut.validators;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.services.RelationshipService;
import br.ufal.ic.p2.jackut.services.UserService;

/**
 * Validador responsável por verificar as regras de adição de amigos no sistema Jackut.
 */
public class FriendValidator {
    private UserService userService;
    private RelationshipService relationshipService;

    /**
     * Construtor do FriendValidator.
     *
     * @param userService Serviço de usuários.
     * @param relationshipService Serviço de relacionamentos.
     */
    public FriendValidator(UserService userService, RelationshipService relationshipService) {
        this.userService = userService;
        this.relationshipService = relationshipService;
    }

    /**
     * Valida a adição de um amigo para um usuário.
     *
     * @param login Nome de usuário do remetente.
     * @param amigo Nome de usuário do destinatário.
     * @param user Usuário remetente.
     * @param friend Usuário destinatário.
     * @throws UserNotExistsException Se o usuário ou o amigo não existirem.
     * @throws EnemyUserException Se o amigo for um inimigo do usuário.
     * @throws UserAlreadyRequestedAsFriendException Se o amigo já tiver sido solicitado como amigo.
     * @throws UserCannotAddHimselfAsFriendException Se o usuário tentar adicionar a si mesmo como amigo.
     * @throws UserAlreadyAddedAsFriendException Se o amigo já tiver sido adicionado como amigo.
     */
    public void validateAddFriend(String login, String amigo, User user, User friend) throws UserNotExistsException, EnemyUserException, UserAlreadyRequestedAsFriendException, UserCannotAddHimselfAsFriendException, UserAlreadyAddedAsFriendException {
        if (user == null || friend == null) {
            throw new UserNotExistsException();
        }

        if (this.relationshipService.isEnemy(amigo, login)) {
            throw new EnemyUserException(this.userService.getUser(amigo).getNome());
        }

        if (user.getFriendsRequest().contains(friend)) {
            throw new UserAlreadyRequestedAsFriendException();
        }

        if (user.getLogin().equals(amigo)) {
            throw new UserCannotAddHimselfAsFriendException();
        }

        if (this.ehAmigo(login, amigo, user, friend)) {
            throw new UserAlreadyAddedAsFriendException();
        }
    }

    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login Nome de usuário do remetente.
     * @param amigo Nome de usuário do destinatário.
     * @param user Usuário remetente.
     * @param friend Usuário destinatário.
     * @return true se forem amigos, false caso contrário.
     */
    public boolean ehAmigo(String login, String amigo, User user, User friend) {
        User currentUser = (user != null) ? user : this.userService.getUser(login);
        User friendUser = (friend != null) ? friend : this.userService.getUser(amigo);

        if (currentUser == null || friendUser == null) {
            return false;
        }

        return currentUser.getFriends().contains(friendUser);
    }
}