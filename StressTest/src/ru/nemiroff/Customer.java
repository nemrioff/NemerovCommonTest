package ru.nemiroff;

import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Customer extends Actor {

    private int availableDenials;

    private double money;

    private static int customerCount = 0;

    private static final int MAX_PRODUCTS_FOR_CUSTOMER = 10;

    public Customer(int id, int availableDenials, double money) {
        super(id);
        this.availableDenials = availableDenials;
        this.money = money;
        customerCount++;
    }

    private double amountOfMoneyAfterDeal(String product, int quantity) {
        double price = FirmConfig.getInstance().getProductsPrice().get(product);
        return money - price * quantity;
    }

    public CustomerStatisticModel call() throws Exception {
        log(id + " customer started");
        CustomerStatisticModel model = new CustomerStatisticModel(id, money);
        while(availableDenials > 0) {
            try {
                Set<String> productsSet = FirmConfig.getInstance().getProductsPrice().keySet();
                String[] products = productsSet.toArray(new String[productsSet.size()]);
                String product = products[random.nextInt(products.length)];
                int quantity = random.nextInt(MAX_PRODUCTS_FOR_CUSTOMER) + 1;
                double newMoney = amountOfMoneyAfterDeal(product, quantity);
                if(newMoney <= 0) {
                    break;
                }
                boolean success = ServiceHelper.getService().buyProduct(product, quantity);
                if (success) {
                    model.addDeal(newMoney, money - newMoney);
                    ProductStatisticModelPool.getInstance().addDeal(product, quantity, money - newMoney);
                    money = newMoney;
                } else {
                    availableDenials--;
                }
            } catch (RemoteException e) {
                // if there are network problems then just try again
                e.printStackTrace();
            }
        }
        customerCount--;
        if(customerCount == 0) {
            terminate = true;
        }
        log(id + " customer stopped");
        return model;
    }
}
