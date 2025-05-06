package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o login é inválido.
 */
public class InvalidLoginOrPasswordException extends Exception {
    public InvalidLoginOrPasswordException() {
        super("Login ou senha inválidos.");
    }
}
