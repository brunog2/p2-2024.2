package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário já está adicionado como amigo.
 */
public class UserAlreadyAddedAsFriendException extends Exception {
    public UserAlreadyAddedAsFriendException() {
        super("Usuário já está adicionado como amigo.");
    }
}
