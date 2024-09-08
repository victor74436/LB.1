package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int productId) {
        products.removeIf(product -> product.getId() == productId);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "Кошик порожній.";
        }
        StringBuilder sb = new StringBuilder("Товари у кошику:\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        return sb.toString();
    }
}
