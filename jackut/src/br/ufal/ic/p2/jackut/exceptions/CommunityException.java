package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção personalizada para erros relacionados a usuários no sistema Jackut.
 */
public class CommunityException extends Exception {

    /**
     * Construtor da exceção CommunityException.
     *
     * @param message Mensagem de erro detalhada.
     */
    public CommunityException(String message) {
        super(message);
    }
}