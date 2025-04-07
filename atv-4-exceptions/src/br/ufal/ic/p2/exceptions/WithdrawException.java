package br.ufal.ic.p2.exceptions;

public class WithdrawException extends Exception {
    public WithdrawException(String message) {
        super("Withdraw Error: " + message);
    }
}
