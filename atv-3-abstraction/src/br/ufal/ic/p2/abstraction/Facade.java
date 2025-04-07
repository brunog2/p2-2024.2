package br.ufal.ic.p2.abstraction;

import br.ufal.ic.p2.abstraction.models.Company;
import br.ufal.ic.p2.abstraction.models.Individual;
import br.ufal.ic.p2.abstraction.models.TaxPayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Facade {
    public Facade() {
        Scanner sc = new Scanner(System.in);

        List<TaxPayer> taxPayers = new ArrayList<>();

        System.out.print("Enter the number of tax payers: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("Tax payer #" + i + " data:");
            System.out.print("Individual or company (i/c)? ");
            char type = sc.next().charAt(0);
            System.out.print("Name: ");
            sc.nextLine(); // consumir quebra de linha
            String name = sc.nextLine();
            System.out.print("Annual income: ");
            double income = sc.nextDouble();

            if (type == 'i') {
                System.out.print("Health expenditures: ");
                double health = sc.nextDouble();
                taxPayers.add(new Individual(name, income, health));
            } else {
                System.out.print("Number of employees: ");
                int employees = sc.nextInt();
                taxPayers.add(new Company(name, income, employees));
            }
        }

        System.out.println("\nTAXES PAID:");
        double totalTaxes = 0.0;
        for (TaxPayer tp : taxPayers) {
            double tax = tp.tax();
            System.out.printf("%s: $ %.2f\n", tp.getName(), tax);
            totalTaxes += tax;
        }

        System.out.printf("\nTOTAL TAXES: $ %.2f\n", totalTaxes);
        sc.close();
    }
}
