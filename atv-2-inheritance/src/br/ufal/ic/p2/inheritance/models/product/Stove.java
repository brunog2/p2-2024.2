package br.ufal.ic.p2.inheritance.models.product;

public class Stove extends Product {
    private int burners;

    public Stove(String brand, double price, int burners) {
        super(brand, price);
        this.burners = burners;
    }

    public int getBurners() {
        return burners;
    }

    @Override
    public String toString() {
        return "Stove [" + brand + ", " + burners + " burners, $" + price + "]";
    }
}
