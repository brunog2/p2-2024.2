package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta ser inimigo de si mesmo.
 */
public class UserCannotBeEnemyOfHimselfException extends Exception {
    public UserCannotBeEnemyOfHimselfException() {
        super("Usuário não pode ser inimigo de si mesmo.");
    }
}
