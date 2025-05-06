package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário já foi adicionado como ídolo.
 */
public class UserAlreadyAddedAsIdolException extends Exception {

    /**
     * Construtor da exceção UserAlreadyAddedAsIdolException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário já foi adicionado como ídolo.
     */
    public UserAlreadyAddedAsIdolException() {
        super("Usuário já está adicionado como ídolo.");
    }
}