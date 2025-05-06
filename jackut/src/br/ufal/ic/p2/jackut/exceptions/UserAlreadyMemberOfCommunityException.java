package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário já é membro de uma comunidade.
 */
public class UserAlreadyMemberOfCommunityException extends Exception {

    /**
     * Construtor da exceção UserAlreadyMemberOfCommunityException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário já faz parte da comunidade.
     */
    public UserAlreadyMemberOfCommunityException() {
        super("Usuario já faz parte dessa comunidade.");
    }
}