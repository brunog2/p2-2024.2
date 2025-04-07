package br.ufal.ic.p2.exceptions;

public class DepositException extends Exception {
    public DepositException(String message) {
        super("Deposit Error: " + message);
    }
}
