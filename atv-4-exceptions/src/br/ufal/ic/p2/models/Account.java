package br.ufal.ic.p2.models;

import br.ufal.ic.p2.exceptions.DepositException;
import br.ufal.ic.p2.exceptions.WithdrawException;

public class Account {
    private Integer number;
    private String holder;
    private double balance;
    private double withdrawLimit;

    public Account(Integer number, String holder, double balance, double withdrawLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrawLimit = withdrawLimit;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public void setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }


    public void deposit(double amount) throws DepositException {
        if (amount <= 0) throw new DepositException("Amount must be positive");
        this.balance += amount;
    }

    public void withdraw(double amount) throws WithdrawException {
        if (amount > this.withdrawLimit) {
            throw new WithdrawException("The amount exceeds withdraw limit");
        }
        if (amount > this.balance) {
            throw new WithdrawException("Not enough balance");
        }
        this.balance -= amount;
    }
}
