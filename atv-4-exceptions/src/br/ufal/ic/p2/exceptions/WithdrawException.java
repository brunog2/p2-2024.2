package br.ufal.ic.p2.exceptions;

public class WithdrawException extends RuntimeException {
    public WithdrawException(String message) {
        super("Withdraw Error: " + message);
    }
}
