package ru.nemiroff;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class AccountManager {

    private Account account = Account.getInstance();

    public void addMoney(float money) {
        account.setMoney(account.getMoney().add(new BigDecimal(money, MathContext.DECIMAL32)));
    }

    public boolean checkEnoughMoney(float money) {
        return account.getMoney().floatValue() >= money;
    }

    public void subMoney(float money) {
        if(!checkEnoughMoney(money)) return;
        account.setMoney(account.getMoney().subtract(new BigDecimal(money, MathContext.DECIMAL32)));
    }

}
