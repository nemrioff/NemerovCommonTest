package ru.nemiroff;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class AccountManager {

    private Account account;

    public AccountManager(double money) {
        account = new Account(money);
    }

    public synchronized void addMoney(double money) {
        account.setMoney(account.getMoney() + money);
    }

    private boolean checkEnoughMoney(double money) {
        return account.getMoney() >= money;
    }

    public synchronized boolean subMoney(double money) {
        if(!checkEnoughMoney(money)) return false;
        account.setMoney(account.getMoney() - money);
        return true;
    }

    public double getAmountOfMoney() {
        return account.getMoney();
    }

}
