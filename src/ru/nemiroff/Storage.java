package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class Storage {

    private Map<Material, Float> materials;

    private static Storage instance = null;

    private Storage(){}

    public static Storage getInstance() {
        if(instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public Map<Material, Float> getMaterials() {
        return materials;
    }

    public void setMaterials(Map<Material, Float> materials) {
        this.materials = materials;
    }
}
