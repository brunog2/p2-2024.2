package br.ufal.ic.p2.jackut.validators;

import br.ufal.ic.p2.jackut.exceptions.*;
import br.ufal.ic.p2.jackut.models.User;
import java.util.List;

/**
 * Validador responsável por verificar as regras de criação e autenticação de usuários no sistema Jackut.
 */
public class UserValidator {

    /**
     * Valida a criação de um novo usuário.
     *
     * @param login Nome de usuário único.
     * @param senha Senha do usuário.
     * @param nome Nome real do usuário.
     * @param users Lista de usuários existentes.
     * @throws UserException Se o login já estiver em uso ou os dados forem inválidos.
     */
    public static void validateNewUser(String login, String senha, String nome, List<User> users) throws UserAlreadyExistsException, InvalidLoginException, InvalidPasswordException {
        if (users.stream().anyMatch(user -> user.getLogin().equals(login))) {
            throw new UserAlreadyExistsException();
        }
        if (login == null || login.isEmpty() || login.contains(" ")) {
            throw new InvalidLoginException();
        }
        if (senha == null || senha.isEmpty()) {
            throw new InvalidPasswordException();
        }
    }

    /**
     * Valida o login de um usuário.
     *
     * @param login Nome de usuário.
     * @param senha Senha do usuário.
     * @param users Lista de usuários existentes.
     * @return O usuário autenticado.
     * @throws UserException Se o login ou senha forem inválidos.
     */
    public static User validateLogin(String login, String senha, List<User> users) throws InvalidLoginOrPasswordException {
        return users.stream()
                .filter(user -> user.getLogin().equals(login) && user.getSenha().equals(senha))
                .findFirst()
                .orElseThrow(() -> new InvalidLoginOrPasswordException());
    }
}