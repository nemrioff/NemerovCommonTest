package ru.nemiroff;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Account {

    private BigDecimal money = new BigDecimal(10000, MathContext.DECIMAL32);

    private static Account instance = null;

    private Account(){}

    public static Account getInstance() {
        if(instance == null) {
            instance = new Account();
        }
        return instance;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
