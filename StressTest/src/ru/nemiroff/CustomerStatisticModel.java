package ru.nemiroff;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class CustomerStatisticModel extends StatisticModel {

    private int customerId;

//    private Map<String, Float> products = new HashMap<String, Float>();

    private float money;

    private BigDecimal spentMoney = new BigDecimal(0);

//    private int ordersCount = 0;


    public CustomerStatisticModel(int customerId, float money) {
        this.customerId = customerId;
        this.money = money;
    }

    public float getMoney() {
        return money;
    }

//    public Map<String, Float> getProducts() {
//        return products;
//    }

    public void addDeal(String product, float quantity, float newMoney, float spentMoney) {
//        if(products.containsKey(product)) {
//            products.put(product, products.get(product) + quantity);
//        } else {
//            products.put(product, quantity);
//        }
        money = newMoney;
//        ordersCount++;
        this.spentMoney = this.spentMoney.add(new BigDecimal(spentMoney));
    }

    public int getCustomerId() {
        return customerId;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}
