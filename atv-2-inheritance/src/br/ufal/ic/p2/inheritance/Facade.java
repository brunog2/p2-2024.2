package br.ufal.ic.p2.inheritance;

import br.ufal.ic.p2.inheritance.models.*;
import br.ufal.ic.p2.inheritance.models.product.*;

public class Facade {
    public Facade() {
        ShoppingCart cart = new ShoppingCart(101);

        TV tv = new TV("Samsung", 1999.99, 55);
        Refrigerator fridge = new Refrigerator("Electrolux", 2599.90, 400);
        Stove stove = new Stove("Brastemp", 1599.00, 4);

        cart.addProduct(tv);
        cart.addProduct(fridge);
        cart.addProduct(stove);

        System.out.println(cart.getContents());
        System.out.println("Total price: $" + cart.getTotalPrice());
        System.out.println("Total items: " + cart.getItemCount());
    }
}
