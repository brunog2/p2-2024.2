package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o login é inválido.
 */
public class InvalidLoginException extends Exception {
    public InvalidLoginException() {
        super("Login inválido.");
    }
}
