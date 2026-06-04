package com.aittr.supermarket.supermarket;

import java.util.Comparator;

public class ComparatorProductsByNameReverse
        implements Comparator<Product> {

    @Override
    public int compare(Product p1,
                       Product p2) {

        String name1 =
                p1.getName();

        String name2 =
                p2.getName();

        return name2.compareToIgnoreCase(
                name1);
    }
}