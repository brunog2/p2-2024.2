package br.ufal.ic.p2.inheritance.models.product;

public class Refrigerator extends Product {
    private int size;

    public Refrigerator(String brand, double price, int size) {
        super(brand, price);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Refrigerator [" + brand + ", " + size + "L, $" + price + "]";
    }
}
