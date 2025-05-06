package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando uma função é inválida devido ao usuário ser um inimigo.
 */
public class EnemyUserException extends Exception {

    /**
     * Construtor da exceção EnemyUserException.
     * Inicializa a exceção com uma mensagem indicando que a função é inválida
     * porque o usuário especificado é um inimigo.
     *
     * @param userName o nome do usuário que é inimigo.
     */
    public EnemyUserException(String userName) {
        super("Função inválida: " + userName + " é seu inimigo.");
    }
}