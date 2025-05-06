package br.ufal.ic.p2.jackut.exceptions;

    public class UserCannotSendMessageToHimselfException extends Exception {
    public UserCannotSendMessageToHimselfException() {
        super("Usuário não pode enviar recado para si mesmo.");
    }
}
