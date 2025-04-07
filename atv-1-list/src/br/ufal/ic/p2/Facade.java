package br.ufal.ic.p2;

import br.ufal.ic.p2.models.Product;
import br.ufal.ic.p2.models.ShoppingCart;

public class Facade {
public    Facade(){
        ShoppingCart cart = new ShoppingCart(1234);
        Product p1 = new Product("Notebook", 3500.00);
        Product p2 = new Product("Mouse", 120.00);

        cart.addProduct(p1);
        cart.addProduct(p2);

        System.out.println("Conteúdo do carrinho:");
        System.out.println(cart.getContents());
        System.out.println("Total: R$ " + cart.getTotalPrice());
        System.out.println("Itens: " + cart.getItemCount());
        System.out.println("Cliente ID: " + cart.getCustomerID());
    }
}
