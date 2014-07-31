package ru.nemiroff;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Account {

    private final static int DEFAULT_START_MONEY = 10000;

    private /*volatile*/ double  money = DEFAULT_START_MONEY;

    private static Account instance = null;

    private Account(){}

    public static Account getInstance() {
        if(instance == null) {
            instance = new Account();
        }
        return instance;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
