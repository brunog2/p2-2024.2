package br.ufal.ic.p2.abstraction.models;

public class Individual extends TaxPayer {
    private final double healthExpenditures;

    public Individual(String name, double annualIncome, double healthExpenditures) {
        super(name, annualIncome);
        this.healthExpenditures = healthExpenditures;
    }

    @Override
    public double tax() {
        double basicTax = (annualIncome < 20000.0) ? annualIncome * 0.15 : annualIncome * 0.25;
        basicTax -= healthExpenditures * 0.5;
        return (basicTax < 0) ? 0 : basicTax;
    }
}