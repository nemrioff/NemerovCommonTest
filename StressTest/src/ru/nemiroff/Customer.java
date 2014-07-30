package ru.nemiroff;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Customer extends Actor {

    public Customer(int id, int availableDenials, float money) {
        super(id);
        this.availableDenials = availableDenials;
        this.money = money;
    }

    private int availableDenials;

    private float money;

    private float amountOfMoneyAfterDeal(String product, float quantity) {
        float price = Config.getInstance().getProductsPrice().get(product);
        return money - price * quantity;
    }

    public CustomerStatisticModel call() throws Exception {
        CustomerStatisticModel model = new CustomerStatisticModel(id, money);
        while(availableDenials > 0) {
            try {
                String product = "borsch";
                float quantity = random.nextInt(1000) / 100f;
                float newMoney = amountOfMoneyAfterDeal(product, quantity);
                if(newMoney <= 0) {
                    break;
                }
                boolean success = ServiceHelper.getService().buyProduct(product, quantity);
                if (success) {
                    model.addDeal(product, quantity, newMoney, money - newMoney);
                    money = newMoney;
                } else {
                    availableDenials--;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return model;
    }
}
