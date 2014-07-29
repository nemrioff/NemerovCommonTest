package ru.nemiroff;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class AccountManager {

    private Account account = Account.getInstance();

    public void addMoney(float money) {
        account.setMoney(account.getMoney() + money);
    }

    public boolean checkEnoughMoney(float money) {
        return account.getMoney() >= money;
    }

    public void subMoney(float money) {
        if(!checkEnoughMoney(money)) return;
        account.setMoney(account.getMoney() - money);
    }

}
