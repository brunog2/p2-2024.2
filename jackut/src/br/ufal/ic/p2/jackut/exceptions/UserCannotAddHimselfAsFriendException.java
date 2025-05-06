package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta se adicionar como amigo.
 */
public class UserCannotAddHimselfAsFriendException extends Exception {

    /**
     * Construtor da exceção UserCannotAddHimselfAsFriendException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário não pode adicionar a si mesmo como amigo.
     */
    public UserCannotAddHimselfAsFriendException() {
        super("Usuário não pode adicionar a si mesmo como amigo.");
    }
}