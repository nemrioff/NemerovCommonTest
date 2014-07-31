package ru.nemiroff;

import java.math.BigDecimal;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class ProductStatisticModel extends StatisticModel {

    private String productName;

    private int ordersCount = 0;

    private float productsQt = 0;

    private BigDecimal sum = new BigDecimal(0);

    public ProductStatisticModel(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void addDeal(float quantity, BigDecimal sum) {
        ordersCount++;
        this.productsQt += quantity;
        this.sum = this.sum.add(sum);
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public float getProductsQt() {
        return productsQt;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
