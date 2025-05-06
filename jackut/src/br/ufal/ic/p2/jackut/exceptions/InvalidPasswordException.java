package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando a senha fornecida é inválida.
 */
public class InvalidPasswordException extends Exception {

    /**
     * Construtor da exceção InvalidPasswordException.
     * Inicializa a exceção com uma mensagem padrão indicando que a senha é inválida.
     */
    public InvalidPasswordException() {
        super("Senha inválida.");
    }
}