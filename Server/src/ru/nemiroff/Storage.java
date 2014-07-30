package ru.nemiroff;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Storage {

    private Map<Thing, Float> materials = new HashMap<Thing, Float>();

    private static Storage instance = null;

    private Storage(){}

    public static Storage getInstance() {
        if(instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public Map<Thing, Float> getMaterials() {
        return materials;
    }

    public void setMaterials(Map<Thing, Float> materials) {
        this.materials = materials;
    }

    public void ptintMaterials() {
        for (Thing thing : materials.keySet()) {
            System.out.println(thing.getName() + " = " + materials.get(thing));
        }
    }
}
