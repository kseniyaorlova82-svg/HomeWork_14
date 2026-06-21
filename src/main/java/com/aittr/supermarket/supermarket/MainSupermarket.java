package com.aittr.supermarket.supermarket;

import java.io.File;

public class MainSupermarket {

    public static void main(String[] args) {

        String home =
                System.getProperty("user.home");

        String folderPath =
                home + File.separator + "supermarket";

        String fileName =
                "products.txt";

        Supermarket shop =
                new Supermarket(
                        "Fresh Market",
                        "Berlin, Alexanderplatz 10");

        Product p1 =
                new Product("Apple", 201, "kg", 4.50);

        Product p2 =
                new Product("Banana", 202, "kg", 3.80);

        Product p3 =
                new Product("Orange", 203, "kg", 5.20);

        Product p4 =
                new Product("Tomato", 204, "kg", 6.10);

        Product p5 =
                new Product("Potato", 205, "kg", 2.90);

        Product p6 =
                new Product("Cucumber", 206, "kg", 4.70);

        Product p7 =
                new Product("Chocolate", 207, "pack", 7.50);

        Product p8 =
                new Product("Water", 208, "liter", 1.80);

        Product p9 =
                new Product("Yogurt", 209, "piece", 2.40);

        Product p10 =
                new Product("Pizza", 210, "piece", 12.90);

        shop.addProduct(p1, 30);
        shop.addProduct(p2, 45);
        shop.addProduct(p3, 20);
        shop.addProduct(p4, 60);
        shop.addProduct(p5, 100);
        shop.addProduct(p6, 35);
        shop.addProduct(p7, 70);
        shop.addProduct(p8, 150);
        shop.addProduct(p9, 80);
        shop.addProduct(p10, 25);

        shop.printProducts();

        shop.saveProductsToFile(
                shop.getStock(),
                folderPath,
                fileName);

        shop.readProductsFromFile(
                shop.getStock(),
                folderPath,
                fileName);

        shop.printProducts();

        shop.fillProducts();

        shop.printProducts();

        shop.saveProductsToFile(
                shop.getStock(),
                folderPath,
                fileName);
    }
}