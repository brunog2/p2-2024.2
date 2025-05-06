package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta ser inimigo de si mesmo.
 */
public class UserCannotBeEnemyOfHimselfException extends Exception {

    /**
     * Construtor da exceção UserCannotBeEnemyOfHimselfException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário não pode ser inimigo de si mesmo.
     */
    public UserCannotBeEnemyOfHimselfException() {
        super("Usuário não pode ser inimigo de si mesmo.");
    }
}