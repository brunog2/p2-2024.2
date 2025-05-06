package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando uma comunidade com o mesmo nome já existe.
 */
public class CommunityAlreadyExistsException extends Exception {

    /**
     * Construtor da exceção CommunityAlreadyExistsException.
     * Inicializa a exceção com uma mensagem padrão indicando que a comunidade já existe.
     */
    public CommunityAlreadyExistsException() {
        super("Comunidade com esse nome já existe.");
    }
}