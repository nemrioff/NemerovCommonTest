package ru.nemiroff;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class OrdersByCustomerRecord extends Record {

    private int id;

    private double ordersSum;

    private double money;

    public String getHeader() {
        return getRecord("Id", "Orders sum", "Balance", "Orders Sum + Balance");
    }

    public String getRecordPreview() {
        return getRecord(id, ordersSum, money, ordersSum + money);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrdersSum(double ordersSum) {
        this.ordersSum = ordersSum;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
