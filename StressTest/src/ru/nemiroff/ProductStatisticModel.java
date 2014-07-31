package ru.nemiroff;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class ProductStatisticModel extends StatisticModel {

    private String productName;

    private int ordersCount = 0;

    private int productsQt = 0;

    private double sum = 0;

    public ProductStatisticModel(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public synchronized void addDeal(int quantity, double sum) {
        ordersCount++;
        this.productsQt += quantity;
        this.sum += sum;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public int getProductsQt() {
        return productsQt;
    }

    public double getSum() {
        return sum;
    }
}
