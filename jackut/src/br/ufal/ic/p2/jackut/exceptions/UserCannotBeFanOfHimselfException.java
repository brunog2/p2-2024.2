package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta ser fã de si mesmo.
 */
public class UserCannotBeFanOfHimselfException extends Exception {
    public UserCannotBeFanOfHimselfException() {
        super("Usuário não pode ser fã de si mesmo.");
    }
}
