package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção que indica que um usuário não foi encontrado no sistema.
 */
public class UserNotFoundException extends Exception {

    /**
     * Construtor da exceção UserNotFoundException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário não foi encontrado.
     */
    public UserNotFoundException() {
        super("Usuário não encontrado.");
    }
}