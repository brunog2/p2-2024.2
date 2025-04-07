package br.ufal.ic.p2.models;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
