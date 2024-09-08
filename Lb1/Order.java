package org.example;

public class Order {
    private Cart cart;

    // Конструктор класу
    public Order(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Замовлення:\n" + cart;
    }
}
