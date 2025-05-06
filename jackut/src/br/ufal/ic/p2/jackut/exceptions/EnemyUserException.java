package br.ufal.ic.p2.jackut.exceptions;

public class EnemyUserException extends Exception {

    public EnemyUserException(String userName) {
        super("Função inválida: " + userName + " é seu inimigo.");
    }
}
