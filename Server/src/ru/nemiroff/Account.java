package ru.nemiroff;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Account {

    private /*volatile*/ double  money;
    //private BigDecimal money;

//    private static Account instance = null;

    public Account(double money){
        this.money = money;
    }

//    public static Account getInstance() {
//        if(instance == null) {
//            instance = new Account();
//        }
//        return instance;
//    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
