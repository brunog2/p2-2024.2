package br.ufal.ic.p2.jackut.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Usuário não encontrado.");
    }


}
