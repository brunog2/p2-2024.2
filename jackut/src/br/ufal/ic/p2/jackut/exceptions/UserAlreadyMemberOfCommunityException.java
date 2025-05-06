package br.ufal.ic.p2.jackut.exceptions;

public class UserAlreadyMemberOfCommunityException extends Exception {
    public UserAlreadyMemberOfCommunityException() {
        super("Usuario já faz parte dessa comunidade.");
    }
}
