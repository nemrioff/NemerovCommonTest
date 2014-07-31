package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Product extends Thing {

    private Map<Thing, Double> proportions;

    @Override
    public double getPrice() {
        double result = 0f;
        for (Thing thing : proportions.keySet()) {
            if(proportions.get(thing) != null) {
                result += thing.getPrice() * proportions.get(thing);
            }
        }
        return result;
    }

    public Map<Thing, Double> getProportions() {
        return proportions;
    }

    public void setProportions(Map<Thing, Double> proportions) {
        this.proportions = proportions;
    }

}
