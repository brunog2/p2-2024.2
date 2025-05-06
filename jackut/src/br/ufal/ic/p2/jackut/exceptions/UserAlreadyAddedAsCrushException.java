package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário já foi adicionado como paquera.
 */
public class UserAlreadyAddedAsCrushException extends Exception {
    public UserAlreadyAddedAsCrushException() {
        super("Usuário já está adicionado como paquera.");
    }
}
