package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Material extends Thing {

    private float price;

    public float getPrice() {
        return price;
    }

    @Override
    public Map<Thing, Float> getProportions() {
        return null;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
