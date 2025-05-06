package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta se adicionar como amigo.
 */
public class UserCannotAddHimselfAsFriendException extends Exception {
    public UserCannotAddHimselfAsFriendException() {
        super("Usuário não pode adicionar a si mesmo como amigo.");
    }
}
