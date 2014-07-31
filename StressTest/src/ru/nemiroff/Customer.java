package ru.nemiroff;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Customer extends Actor {

    public Customer(int id, int availableDenials, float money) {
        super(id);
        this.availableDenials = availableDenials;
        this.money = money;
        customerCount++;
    }

    private int availableDenials;

    private float money;

    private static int customerCount = 0;

    private float amountOfMoneyAfterDeal(String product, float quantity) {
        float price = Config.getInstance().getProductsPrice().get(product);
        return money - price * quantity;
    }

    public CustomerStatisticModel call() throws Exception {
        CustomerStatisticModel model = new CustomerStatisticModel(id, money);
        while(availableDenials > 0) {
            try {
                Set<String> productsSet = Config.getInstance().getProductsPrice().keySet();
                String[] products = productsSet.toArray(new String[productsSet.size()]);
                String product = products[random.nextInt(products.length)];
                float quantity = random.nextInt(10) + 1;
                float newMoney = amountOfMoneyAfterDeal(product, quantity);
                if(newMoney <= 0) {
                    break;
                }
                boolean success = ServiceHelper.getService().buyProduct(product, quantity);
                if (success) {
                    model.addDeal(product, quantity, newMoney, money - newMoney);
                    ProductStatisticModelPool.getInstance().addDeal(product, quantity, new BigDecimal(money - newMoney));
                    money = newMoney;
                } else {
                    availableDenials--;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        customerCount--;
        if(customerCount == 0) {
            terminate = true;
        }
        System.out.println(id + " customer stopped");
        return model;
    }
}
