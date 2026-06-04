package com.aittr.supermarket.supermarket;

import java.util.Comparator;

public class ComparatorProductsByQuantityReverse
        implements Comparator<Product> {

    @Override
    public int compare(Product p1,
                       Product p2) {

        double q1 =
                p1.getQuantity();

        double q2 =
                p2.getQuantity();

        return Double.compare(
                q2,
                q1);
    }
}