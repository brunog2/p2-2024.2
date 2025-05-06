package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando não há mensagens em uma comunidade.
 */
public class EmptyCommunityMessagesException extends Exception {

    /**
     * Construtor da exceção EmptyCommunityMessagesException.
     * Inicializa a exceção com uma mensagem padrão indicando que não há mensagens.
     */
    public EmptyCommunityMessagesException() {
        super("Não há mensagens.");
    }
}