package ru.nemiroff;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class OrdersByProductRecord extends Record{

    private String productName;

    private int ordersCount;

    private int productQt;

    private double sum;

    public String getHeader() {
        return getRecord("Name", "Orders", "Product quantity", "Orders Sum", "Product price", "Sum - (Product quantity * Product price)" );
    }

    public String getRecordPreview() {
        double price = FirmConfig.getInstance().getProductsPrice().get(productName);
        return getRecord(productName, ordersCount, productQt, sum, price, sum - price * productQt);
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public void setProductQt(int productQt) {
        this.productQt = productQt;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}
