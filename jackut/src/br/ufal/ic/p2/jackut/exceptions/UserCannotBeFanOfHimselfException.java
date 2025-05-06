package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta ser fã de si mesmo.
 */
public class UserCannotBeFanOfHimselfException extends Exception {

    /**
     * Construtor da exceção UserCannotBeFanOfHimselfException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário não pode ser fã de si mesmo.
     */
    public UserCannotBeFanOfHimselfException() {
        super("Usuário não pode ser fã de si mesmo.");
    }
}