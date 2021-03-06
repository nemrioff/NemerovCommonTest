package ru.nemiroff;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Storage {

    private Map<Thing, Double> materials = new HashMap<Thing, Double>();

    private static Storage instance = null;

    private Storage(){}

    public static Storage getInstance() {
        if(instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public Map<Thing, Double> getMaterials() {
        return materials;
    }

    public void setMaterials(Map<Thing, Double> materials) {
        this.materials = materials;
    }

}
