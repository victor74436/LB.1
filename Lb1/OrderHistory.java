package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        if (orders.isEmpty()) {
            return "Історія замовлень пуста.";
        }
        StringBuilder sb = new StringBuilder("Історія замовлень:\n");
        for (Order order : orders) {
            sb.append(order).append("\n");
        }
        return sb.toString();
    }
}
