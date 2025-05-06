package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção que indica que um usuário tentou enviar um recado para si mesmo.
 */
public class UserCannotSendMessageToHimselfException extends Exception {

    /**
     * Construtor da exceção UserCannotSendMessageToHimselfException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário não pode enviar recado para si mesmo.
     */
    public UserCannotSendMessageToHimselfException() {
        super("Usuário não pode enviar recado para si mesmo.");
    }
}