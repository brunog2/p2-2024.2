package br.ufal.ic.p2.jackut.exceptions;

public class UserAlreadyExistsException extends Exception {

    /**
     * Construtor da exceção UserException.
     */
    public UserAlreadyExistsException() {
        super("Conta com esse nome já existe.");
    }
}