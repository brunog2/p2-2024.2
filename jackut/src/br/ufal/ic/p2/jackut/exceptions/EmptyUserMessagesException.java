package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando não há recados para o usuário.
 */
public class EmptyUserMessagesException extends Exception {

    /**
     * Construtor da exceção EmptyUserMessagesException.
     * Inicializa a exceção com uma mensagem padrão indicando que não há recados.
     */
    public EmptyUserMessagesException() {
        super("Não há recados.");
    }
}