package br.ufal.ic.p2.abstraction.models;

public class Company extends TaxPayer {
    private final int numberOfEmployees;

    public Company(String name, double annualIncome, int numberOfEmployees) {
        super(name, annualIncome);
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public double tax() {
        return annualIncome * (numberOfEmployees > 10 ? 0.14 : 0.16);
    }
}
