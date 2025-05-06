package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário já enviou solicitação de amizade.
 */
public class UserAlreadyRequestedAsFriendException extends Exception {
    public UserAlreadyRequestedAsFriendException() {
        super("Usuário já está adicionado como amigo, esperando aceitação do convite.");
    }
}
