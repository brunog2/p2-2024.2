package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um atributo obrigatório não é preenchido.
 */
public class AttributeNotFilledException extends Exception {

    /**
     * Construtor da exceção AttributeNotFilledException.
     * Inicializa a exceção com uma mensagem padrão indicando que um atributo obrigatório não foi preenchido.
     */
    public AttributeNotFilledException() {
        super("Atributo não preenchido.");
    }
}