package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o inimigo já está adicionado.
 */
public class UserAlreadyAddedAsEnemyException extends Exception {

    /**
     * Construtor da exceção UserAlreadyAddedAsEnemyException.
     * Inicializa a exceção com uma mensagem padrão indicando que o inimigo já está adicionado.
     */
    public UserAlreadyAddedAsEnemyException() {
        super("Usuário já está adicionado como inimigo.");
    }
}