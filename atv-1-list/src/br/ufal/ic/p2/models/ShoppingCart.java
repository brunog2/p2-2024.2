package br.ufal.ic.p2.models;

import java.util.ArrayList;

public class ShoppingCart {
    private int customerID;
    private ArrayList<Product> productList;

    public ShoppingCart(int customerID) {
        this.customerID = customerID;
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product p) {
        productList.add(p);
    }

    public void removeProduct(Product p) {
        productList.remove(p);
    }

    public String getContents() {
        if (productList.isEmpty()) return "Carrinho vazio.";

        StringBuilder sb = new StringBuilder();
        for (Product p : productList) {
            sb.append(p.getName()).append(" - R$ ").append(p.getPrice()).append("\n");
        }
        return sb.toString();
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getItemCount() {
        return productList.size();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product p : productList) {
            total += p.getPrice();
        }
        return total;
    }
}
