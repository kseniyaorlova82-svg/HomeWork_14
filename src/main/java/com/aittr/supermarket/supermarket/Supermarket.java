package com.aittr.supermarket.supermarket;

import java.io.*;
import java.util.ArrayList;

public class Supermarket {

    private String name;
    private String address;

    private ArrayList<Product> stock =
            new ArrayList<>();

    public Supermarket(String name, String address) {
        super();
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getStock() {
        return stock;
    }

    public void addProduct(Product p, double quantity) {

        if (stock.contains(p) == false) {

            p.setQuantity(quantity);

            stock.add(p);

            stock.sort(null);

        } else {

            for (Product pr : stock) {

                if (pr.getCode() == p.getCode()) {

                    double q = pr.getQuantity();

                    q += quantity;

                    pr.setQuantity(q);

                    break;
                }
            }
        }
    }

    public void printProducts() {

        System.out.println("************************");
        System.out.println("Supermarket: " + name);
        System.out.println("Address: " + address);
        System.out.println("\t\t\tStock: ");

        for (Product pr : stock) {
            System.out.println(pr);
        }

        System.out.println("************************");
    }

    public void removeProduct(int code, double quantity) {

        for (Product pr : stock) {

            if (pr.getCode() == code) {

                double q = pr.getQuantity();

                q -= quantity;

                if (q < 0) {
                    q = 0;
                }

                pr.setQuantity(q);

                return;
            }
        }

        System.out.println("_________________________");
        System.out.println("Wrong code !!!!!  " + code);
        System.out.println("_________________________");
    }

    public void reducePrice(int code, double percent) {

        for (Product pr : stock) {

            if (pr.getCode() == code) {

                double price = pr.getPrice();

                double discount =
                        (price * percent) / 100;

                price -= discount;

                pr.setPrice(price);

                return;
            }
        }

        System.out.println("_________________________");
        System.out.println("Wrong code !!!!!  " + code);
        System.out.println("_________________________");
    }

    public void findProduct(int code) {

        for (Product pr : stock) {

            if (pr.getCode() == code) {

                System.out.println("Product found:");
                System.out.println(pr);

                return;
            }
        }

        System.out.println("Product not found. Wrong code: " + code);
    }

    public void removeProductFromStock(int code) {

        for (int i = 0; i < stock.size(); i++) {

            if (stock.get(i).getCode() == code) {

                stock.remove(i);

                return;
            }
        }

        System.out.println("Product not found. Wrong code: " + code);
    }

    public void reducePriceForAll(double percent) {

        for (Product pr : stock) {

            double price = pr.getPrice();

            double discount =
                    (price * percent) / 100;

            price -= discount;

            pr.setPrice(price);
        }
    }

    public double totalCost() {

        double sum = 0;

        for (Product pr : stock) {

            sum += pr.getPrice()
                    * pr.getQuantity();
        }

        return sum;
    }

    public void sortProductsBy(int criteria) {

        switch (criteria) {

            case ISortingCriteria.SORT_BY_PRICE:

                ComparatorProductsByPrice comp1 =
                        new ComparatorProductsByPrice();

                stock.sort(comp1);

                break;

            case ISortingCriteria.SORT_BY_NAME_REVERSE:

                ComparatorProductsByNameReverse comp2 =
                        new ComparatorProductsByNameReverse();

                stock.sort(comp2);

                break;

            case ISortingCriteria.SORT_BY_CODE_REVERSE:

                ComparatorProductsByCodeReverse comp3 =
                        new ComparatorProductsByCodeReverse();

                stock.sort(comp3);

                break;

            case ISortingCriteria.SORT_BY_QUANTITY:

                ComparatorProductsByQuantity comp4 =
                        new ComparatorProductsByQuantity();

                stock.sort(comp4);

                break;

            case ISortingCriteria.SORT_BY_QUANTITY_REVERSE:

                ComparatorProductsByQuantityReverse comp5 =
                        new ComparatorProductsByQuantityReverse();

                stock.sort(comp5);

                break;

            default:

                System.out.println("Wrong sorting criteria");
        }
    }

    public void readProductsFromFile(ArrayList<Product> stock,
                                     String folderPath,
                                     String fileName) {

        String filePath = folderPath + File.separator + fileName;

        File fl = new File(filePath);

        if (fl.exists() == false) {

            System.out.println("file " + filePath + " not exists");

            return;
        }

        try {

            FileReader fr = new FileReader(fl);

            BufferedReader br = new BufferedReader(fr);

            while (true) {

                String fromFile = br.readLine();

                if (fromFile == null) {

                    br.close();

                    break;
                }

                // "Apple#201#kg#4.5#30"
                String[] ar = fromFile.split("#");

                String name = ar[0];

                int code = Integer.parseInt(ar[1]);

                String unit = ar[2];

                double price = Double.parseDouble(ar[3]);

                double quantity = Double.parseDouble(ar[4]);

                Product p =
                        new Product(name, code, unit, price);

                addProduct(p, quantity);
            }

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    public void saveProductsToFile(ArrayList<Product> stock,
                                   String folderPath,
                                   String fileName) {

        File folder = new File(folderPath);

        if (folder.exists() == false)
            folder.mkdirs();

        String filePath =
                folderPath + File.separator + fileName;

        File fl = new File(filePath);

        if (fl.exists() == false) {

            try {

                fl.createNewFile();

            } catch (IOException e) {

                System.out.println(e.getMessage());
            }
        }

        try {

            FileWriter fw = new FileWriter(fl);

            BufferedWriter bw = new BufferedWriter(fw);

            for (Product p : stock) {

                String toFile =
                        p.getName()
                                + "#" + p.getCode() + "#"
                                + p.getUnit() + "#"
                                + p.getPrice() + "#"
                                + p.getQuantity();

                bw.write(toFile);

                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    public void fillProducts() {

        InputStreamReader isr =
                new InputStreamReader(System.in);

        BufferedReader br =
                new BufferedReader(isr);

        while (true) {

            try {

                System.out.print("Enter name >>>");

                String name = br.readLine();

                System.out.print("Enter code >>>");

                String answer = br.readLine();

                int code = Integer.parseInt(answer);

                System.out.print("Enter unit >>>");

                String unit = br.readLine();

                System.out.print("Enter price >>>");

                answer = br.readLine();

                double price = Double.parseDouble(answer);

                System.out.print("Enter quantity >>>");

                answer = br.readLine();

                double quantity = Double.parseDouble(answer);

                Product p =
                        new Product(name, code, unit, price);

                addProduct(p, quantity);

                System.out.print("Another one? yes/no >>>");

                answer = br.readLine();

                if (answer.equalsIgnoreCase("no")) {

                    br.close();

                    break;
                }

            } catch (IOException e) {

                System.out.println(e.getMessage());
            }
        }
    }
}