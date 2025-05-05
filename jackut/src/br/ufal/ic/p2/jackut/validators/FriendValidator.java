package br.ufal.ic.p2.jackut.validators;

import br.ufal.ic.p2.jackut.exceptions.UserException;
import br.ufal.ic.p2.jackut.models.User;
import br.ufal.ic.p2.jackut.services.FriendService;
import br.ufal.ic.p2.jackut.services.RelationshipService;
import br.ufal.ic.p2.jackut.services.UserService;

import javax.management.relation.Relation;

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
     */
    public FriendValidator(UserService userService, RelationshipService relationshipService) {
        this.userService = userService;
        this.relationshipService = relationshipService;
    }

    /**
     * Valida a adição de um amigo para um usuário.
     *
     * @param login Nome de usuário.
     * @param amigo Nome do amigo.
     * @param user  Usuário remetente.
     * @param friend Usuário destinatário.
     * @throws UserException Se houver algum problema na validação.
     */
    public void validateAddFriend(String login, String amigo, User user, User friend) throws UserException {
        if (user == null || friend == null) {
            throw new UserException("Usuário não cadastrado.");
        }

//        System.out.println("Validando adição de amigo: " + login + " -> " + amigo + " -> " + this.relationshipService.isEnemy(login, amigo)  + " -> " + this.userService.getUser(amigo).getEnemies());

        if (this.relationshipService.isEnemy(amigo, login)) {
            throw new UserException("Função inválida: "+ this.userService.getUser(amigo).getNome() + " é seu inimigo.");
        }

        if (user.getFriendsRequest().contains(friend)) {
            throw new UserException("Usuário já está adicionado como amigo, esperando aceitação do convite.");
        }

        if (user.getLogin().equals(amigo)) {
            throw new UserException("Usuário não pode adicionar a si mesmo como amigo.");
        }

        if (this.ehAmigo(login, amigo, user, friend)) {
            throw new UserException("Usuário já está adicionado como amigo.");
        }
    }

    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login Nome de usuário.
     * @param amigo Nome do amigo.
     * @param user  Usuário remetente.
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