package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário já está adicionado como amigo.
 */
public class UserAlreadyAddedAsFriendException extends Exception {

    /**
     * Construtor da exceção UserAlreadyAddedAsFriendException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário já está adicionado como amigo.
     */
    public UserAlreadyAddedAsFriendException() {
        super("Usuário já está adicionado como amigo.");
    }
}