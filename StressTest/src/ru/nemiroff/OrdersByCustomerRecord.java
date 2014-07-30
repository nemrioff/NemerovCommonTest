package ru.nemiroff;

import java.math.BigDecimal;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class OrdersByCustomerRecord extends Record {

    private int id;

    private BigDecimal ordersSum;

    private float money;

    public String getHeader() {
        return "Идентификатор" + delimiter + "Сумма заказов" + delimiter + "Остаток средств";
    }

    public String getRecordPreview() {
        return id + delimiter + ordersSum + delimiter + money;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrdersSum(BigDecimal ordersSum) {
        this.ordersSum = ordersSum;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
