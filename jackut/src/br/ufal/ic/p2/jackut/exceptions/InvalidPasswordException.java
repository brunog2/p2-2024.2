package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando a senha é inválida.
 */
public class InvalidPasswordException extends Exception {
    public InvalidPasswordException() {
        super("Senha inválida.");
    }
}
