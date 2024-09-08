package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Створення товарів
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        Cart cart = new Cart();
        OrderHistory orderHistory = new OrderHistory();
        List<Product> allProducts = List.of(product1, product2, product3);

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Для зчитування залишкового символу нового рядка

            switch (choice) {
                case 1:
                    // Перегляд списку товарів
                    System.out.println("Список товарів:");
                    allProducts.forEach(System.out::println);
                    break;

                case 2:
                    // Додавання товару до кошика
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int addId = scanner.nextInt();
                    scanner.nextLine(); // Для зчитування залишкового символу нового рядка
                    Product productToAdd = allProducts.stream()
                            .filter(product -> product.getId() == addId)
                            .findFirst()
                            .orElse(null);
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println(productToAdd.getName() + " додано до кошика.");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;

                case 3:
                    // Видалення товару з кошика
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int removeId = scanner.nextInt();
                    scanner.nextLine(); // Для зчитування залишкового символу нового рядка
                    cart.removeProduct(removeId);
                    System.out.println("Товар з ID " + removeId + " видалено з кошика.");
                    break;

                case 4:
                    // Перегляд кошика
                    System.out.println(cart);
                    break;

                case 5:
                    // Оформлення замовлення
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.addOrder(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear(); // Очищення кошика після оформлення замовлення
                    }
                    break;

                case 6:
                    // Перегляд історії замовлень
                    System.out.println(orderHistory);
                    break;

                case 7:
                    // Пошук товарів
                    System.out.println("Виберіть тип пошуку: 1 - Назва, 2 - Категорія");
                    int searchType = scanner.nextInt();
                    scanner.nextLine(); // Для зчитування залишкового символу нового рядка
                    if (searchType == 1) {
                        System.out.println("Введіть назву товару:");
                        String name = scanner.nextLine().toLowerCase();
                        List<Product> foundByName = allProducts.stream()
                                .filter(product -> product.getName().toLowerCase().contains(name))
                                .collect(Collectors.toList());
                        if (!foundByName.isEmpty()) {
                            System.out.println("Результати пошуку за назвою:");
                            foundByName.forEach(System.out::println);
                        } else {
                            System.out.println("Товари з такою назвою не знайдено.");
                        }
                    } else if (searchType == 2) {
                        System.out.println("Введіть назву категорії:");
                        String categoryName = scanner.nextLine().toLowerCase();
                        List<Product> foundByCategory = allProducts.stream()
                                .filter(product -> product.getCategory().getName().toLowerCase().contains(categoryName))
                                .collect(Collectors.toList());
                        if (!foundByCategory.isEmpty()) {
                            System.out.println("Результати пошуку за категорією:");
                            foundByCategory.forEach(System.out::println);
                        } else {
                            System.out.println("Товари з такою категорією не знайдено.");
                        }
                    } else {
                        System.out.println("Невірний тип пошуку.");
                    }
                    break;

                case 0:
                    // Вихід з програми
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    scanner.close();
                    return;

                default:
                    // Невідома опція
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}