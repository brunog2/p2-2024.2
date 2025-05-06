package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando uma conta com o mesmo nome de usuário já existe.
 */
public class UserAlreadyExistsException extends Exception {

    /**
     * Construtor da exceção UserAlreadyExistsException.
     * Inicializa a exceção com uma mensagem padrão indicando que uma conta com o mesmo nome já existe.
     */
    public UserAlreadyExistsException() {
        super("Conta com esse nome já existe.");
    }
}