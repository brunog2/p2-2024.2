package br.ufal.ic.p2;

import br.ufal.ic.p2.exceptions.DepositException;
import br.ufal.ic.p2.exceptions.WithdrawException;
import br.ufal.ic.p2.models.Account;

import java.util.Scanner;

public class Facade {
    private Account account;
    private final Scanner scanner;

    public Facade() {
        this.scanner = new Scanner(System.in);
    }

    public void initAccount() {
        System.out.println("Enter account data");
        System.out.print("Number: ");
        int number = this.scanner.nextInt();
        this.scanner.nextLine();

        System.out.print("Holder: ");
        String holder = this.scanner.nextLine();

        System.out.print("Initial balance: ");
        double balance = this.scanner.nextDouble();

        System.out.print("Withdraw limit: ");
        double withdrawLimit = this.scanner.nextDouble();

        this.account = new Account(number, holder, balance, withdrawLimit);
    }

    public void deposit() throws DepositException {
        System.out.print("Enter amount for deposit: ");
        double amount = this.scanner.nextDouble();
        this.account.deposit(amount);
    }

    public void withdraw() throws WithdrawException {
        System.out.print("Enter amount for withdraw: ");
        double amount = this.scanner.nextDouble();
        this.account.withdraw(amount);
    }

    public void getBalance() {
        System.out.println("New balance: " + this.account.getBalance());
    }
}
