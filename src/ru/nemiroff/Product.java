package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Product {

    private String name;

    private Map<Material, Float> proportions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Material, Float> getProportions() {
        return proportions;
    }

    public void setProportions(Map<Material, Float> proportions) {
        this.proportions = proportions;
    }
}
