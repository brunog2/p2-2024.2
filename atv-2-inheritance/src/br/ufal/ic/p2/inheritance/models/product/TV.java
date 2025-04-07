package br.ufal.ic.p2.inheritance.models.product;

public class TV extends Product {
    private int inches;

    public TV(String brand, double price, int inches) {
        super(brand, price);
        this.inches = inches;
    }

    public int getInches() {
        return inches;
    }

    @Override
    public String toString() {
        return "TV [" + brand + ", " + inches + " inches, $" + price + "]";
    }
}
