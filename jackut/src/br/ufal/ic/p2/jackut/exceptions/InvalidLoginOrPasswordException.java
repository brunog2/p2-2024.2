package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o login ou a senha fornecidos são inválidos.
 */
public class InvalidLoginOrPasswordException extends Exception {

    /**
     * Construtor da exceção InvalidLoginOrPasswordException.
     * Inicializa a exceção com uma mensagem padrão indicando que o login ou a senha são inválidos.
     */
    public InvalidLoginOrPasswordException() {
        super("Login ou senha inválidos.");
    }
}