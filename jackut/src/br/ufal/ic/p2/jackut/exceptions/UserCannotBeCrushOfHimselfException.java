package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta ser paquera de si mesmo.
 */
public class UserCannotBeCrushOfHimselfException extends Exception {
    public UserCannotBeCrushOfHimselfException() {
        super("Usuário não pode ser paquera de si mesmo.");
    }
}
