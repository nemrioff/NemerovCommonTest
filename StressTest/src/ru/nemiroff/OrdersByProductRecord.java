package ru.nemiroff;

import java.math.BigDecimal;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class OrdersByProductRecord extends Record{

    private String productName;

    private int ordersCount;

    private float productQt;

    private BigDecimal sum;

    public String getHeader() {
        return getRecord("Name", "Orders", "Product quantity", "Orders Sum", "Product price", "Sum - (Product quantity * Product price)" );
    }

    public String getRecordPreview() {
        float price = Config.getInstance().getProductsPrice().get(productName);
        return getRecord(productName, ordersCount, productQt, sum, price, sum.subtract(new BigDecimal(price * productQt)));
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public void setProductQt(float productQt) {
        this.productQt = productQt;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

}
