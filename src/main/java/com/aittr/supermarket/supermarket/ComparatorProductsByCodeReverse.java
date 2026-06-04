package com.aittr.supermarket.supermarket;

import java.util.Comparator;

public class ComparatorProductsByCodeReverse
        implements Comparator<Product> {

    @Override
    public int compare(Product p1,
                       Product p2) {

        int code1 =
                p1.getCode();

        int code2 =
                p2.getCode();

        return Integer.compare(
                code2,
                code1);
    }
}
