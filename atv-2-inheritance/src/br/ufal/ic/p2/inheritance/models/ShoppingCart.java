package br.ufal.ic.p2.inheritance.models;
import br.ufal.ic.p2.inheritance.models.product.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int customerID;
    private List<Product> productList;

    public ShoppingCart(int customerID) {
        this.customerID = customerID;
        this.productList = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public String getContents() {
        StringBuilder contents = new StringBuilder("Cart contents:\n");
        for (Product p : productList) {
            contents.append(p.toString()).append("\n");
        }
        return contents.toString();
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getItemCount() {
        return productList.size();
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Product p : productList) {
            total += p.getPrice();
        }
        return total;
    }
}
