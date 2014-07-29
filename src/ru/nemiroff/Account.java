package ru.nemiroff;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Account {

    private float money;

    private static Account instance = null;

    private Account(){}

    public static Account getInstance() {
        if(instance == null) {
            instance = new Account();
        }
        return instance;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
