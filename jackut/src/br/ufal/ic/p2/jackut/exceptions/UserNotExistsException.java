package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção que indica que um usuário não está cadastrado no sistema.
 */
public class UserNotExistsException extends Exception {

    /**
     * Construtor da exceção UserNotExistsException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário não está cadastrado.
     */
    public UserNotExistsException() {
        super("Usuário não cadastrado.");
    }
}