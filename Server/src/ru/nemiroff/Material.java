package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Material extends Thing {

    private double price;

    public double getPrice() {
        return price;
    }

    @Override
    public Map<Thing, Double> getProportions() {
        return null;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
