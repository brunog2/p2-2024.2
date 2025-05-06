package br.ufal.ic.p2.jackut.exceptions;

public class EmptyUserMessagesException extends Exception {
    public EmptyUserMessagesException() {
        super("Não há recados.");
    }
}
