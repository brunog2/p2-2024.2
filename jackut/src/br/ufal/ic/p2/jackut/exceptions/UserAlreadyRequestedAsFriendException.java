package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário já enviou uma solicitação de amizade.
 */
public class UserAlreadyRequestedAsFriendException extends Exception {

    /**
     * Construtor da exceção UserAlreadyRequestedAsFriendException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário já enviou uma solicitação de amizade
     * e está aguardando a aceitação do convite.
     */
    public UserAlreadyRequestedAsFriendException() {
        super("Usuário já está adicionado como amigo, esperando aceitação do convite.");
    }
}