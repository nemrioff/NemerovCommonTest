package ru.nemiroff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class ProductStatisticModelPool {

    private static ProductStatisticModelPool instance = null;

    public static ProductStatisticModelPool getInstance() {
        if (instance == null) {
            instance = new ProductStatisticModelPool();
        }
        return instance;
    }

    private List<ProductStatisticModel> models = new ArrayList<ProductStatisticModel>();

    public void addDeal(String product, float quantity, BigDecimal sum) {
        for (ProductStatisticModel model : models) {
            if(model.getProductName().equals(product)) {
                model.addDeal(quantity, sum);
                return;
            }
        }
        ProductStatisticModel model = new ProductStatisticModel(product);
        model.addDeal(quantity, sum);
        models.add(model);
    }


    public List<ProductStatisticModel> getModel() {
        return models;
    }
}
