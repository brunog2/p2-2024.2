package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário já foi adicionado como ídolo.
 */
public class UserAlreadyAddedAsIdolException extends Exception {
    public UserAlreadyAddedAsIdolException() {
        super("Usuário já está adicionado como ídolo.");
    }
}
