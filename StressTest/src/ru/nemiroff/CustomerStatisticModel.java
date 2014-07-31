package ru.nemiroff;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class CustomerStatisticModel extends StatisticModel {

    private int customerId;

    private double money;

    private double spentMoney = 0;

    public CustomerStatisticModel(int customerId, double money) {
        this.customerId = customerId;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public synchronized void addDeal(double newMoney, double spentMoney) {
        money = newMoney;
        this.spentMoney += spentMoney;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }
}
