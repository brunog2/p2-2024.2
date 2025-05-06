package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o login é inválido.
 */
public class InvalidLoginException extends Exception {

    /**
     * Construtor da exceção InvalidLoginException.
     * Inicializa a exceção com uma mensagem padrão indicando que o login é inválido.
     */
    public InvalidLoginException() {
        super("Login inválido.");
    }
}