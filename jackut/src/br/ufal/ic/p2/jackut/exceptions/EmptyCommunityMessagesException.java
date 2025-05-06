package br.ufal.ic.p2.jackut.exceptions;

public class EmptyCommunityMessagesException  extends Exception {
    public EmptyCommunityMessagesException() {
        super("Não há mensagens.");
    }
}
