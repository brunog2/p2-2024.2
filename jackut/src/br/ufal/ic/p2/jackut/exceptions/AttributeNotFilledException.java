package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um atributo obrigatório não é preenchido.
 */
public class AttributeNotFilledException extends Exception {
    public AttributeNotFilledException() {
        super("Atributo não preenchido.");
    }
}
