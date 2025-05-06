package br.ufal.ic.p2.jackut.exceptions;

public class UserNotExistsException extends Exception {

    /**
     * Construtor da exceção UserNotExistsException.
     *
     */
    public UserNotExistsException() {
        super("Usuário não cadastrado.");
    }
}