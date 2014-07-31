package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class StorageManager {

    public void addMaterial(Thing material, float quantity) {
        Map<Thing, Float> storageMaterials = Storage.getInstance().getMaterials();
        Float value = storageMaterials.get(material);
        if (value == null) {
            storageMaterials.put(material, quantity);
        } else {
            storageMaterials.put(material, value + quantity);
        }
    }

    public boolean checkEnoughMaterial(Thing material, float portion, float quantity) {
        Float value = Storage.getInstance().getMaterials().get(material);
        return value != null && value >= (portion * quantity);
    }

    public boolean checkEnoughMaterials(Map<Thing, Float> materials, float quantity) {
        for (Thing material : materials.keySet()) {
            if(!checkEnoughMaterial(material, materials.get(material), quantity)) {
                return false;
            }
        }
        return true;
    }

    public void removeMaterial(Thing material, float portion, float quantity) {
        Map<Thing, Float> storageMaterials = Storage.getInstance().getMaterials();
        Float value = storageMaterials.get(material);
        storageMaterials.put(material, value - portion * quantity);
    }

    public void removeMaterials(Map<Thing, Float> materials, float quantity) {
        for (Thing material : materials.keySet()) {
            removeMaterial(material, materials.get(material), quantity);
        }
    }

    public Map<Thing, Float> getAllMaterials() {
        return Storage.getInstance().getMaterials();
    }
}
