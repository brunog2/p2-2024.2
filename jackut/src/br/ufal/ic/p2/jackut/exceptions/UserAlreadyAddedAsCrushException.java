package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário já foi adicionado como paquera.
 */
public class UserAlreadyAddedAsCrushException extends Exception {

    /**
     * Construtor da exceção UserAlreadyAddedAsCrushException.
     * Inicializa a exceção com uma mensagem padrão indicando que o usuário já foi adicionado como paquera.
     */
    public UserAlreadyAddedAsCrushException() {
        super("Usuário já está adicionado como paquera.");
    }
}