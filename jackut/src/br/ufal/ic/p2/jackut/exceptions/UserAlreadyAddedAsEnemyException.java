package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o inimigo já está adicionado.
 */
public class UserAlreadyAddedAsEnemyException extends Exception {
    public UserAlreadyAddedAsEnemyException() {
        super("Usuário já está adicionado como inimigo.");
    }
}
