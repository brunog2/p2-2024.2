package br.ufal.ic.p2.jackut.exceptions;

public class UserAlreadyExistsException extends Exception {

    /**
     * Construtor da exceção UserException.
     *
     * @param message Mensagem de erro detalhada.
     */
    public UserAlreadyExistsException(String message) {
        super("Conta com esse nome já existe.");
    }
}