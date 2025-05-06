package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta ser paquera de si mesmo.
 */
public class UserCannotBeCrushOfHimselfException extends Exception {

    /**
     * Construtor da exceção UserCannotBeCrushOfHimselfException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário não pode ser paquera de si mesmo.
     */
    public UserCannotBeCrushOfHimselfException() {
        super("Usuário não pode ser paquera de si mesmo.");
    }
}