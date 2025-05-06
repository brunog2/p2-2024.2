package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando a comunidade especificada não existe.
 */
public class CommunityNotExistsException extends Exception {

    /**
     * Construtor da exceção CommunityNotExistsException.
     * Inicializa a exceção com uma mensagem padrão indicando que a comunidade não existe.
     */
    public CommunityNotExistsException() {
        super("Comunidade não existe.");
    }
}