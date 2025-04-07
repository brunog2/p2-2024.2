package br.ufal.ic.p2.abstraction.models;

public abstract class TaxPayer {
    protected String name;
    protected double annualIncome;

    public TaxPayer(String name, double annualIncome) {
        this.name = name;
        this.annualIncome = annualIncome;
    }

    public abstract double tax();

    public String getName() {
        return name;
    }
}