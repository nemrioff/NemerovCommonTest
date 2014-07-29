package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class ProductManager {

    public float getCost(Product product) {
        Map<Material, Float> proportions = product.getProportions();
        float result = 0f;
        for (Material material : proportions.keySet()) {
            result += material.getPrice() * proportions.get(material);
        }
        return result;
    }

}
