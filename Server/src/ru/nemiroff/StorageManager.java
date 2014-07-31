package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class StorageManager {

    public synchronized void addMaterial(Thing material, int quantity) {
        Map<Thing, Double> storageMaterials = Storage.getInstance().getMaterials();
        Double value = storageMaterials.get(material);
        if (value == null) {
            storageMaterials.put(material, (double) quantity);
        } else {
            storageMaterials.put(material, value + quantity);
        }
    }

    public boolean checkEnoughMaterial(Thing material, double portion, int quantity) {
        Double value = Storage.getInstance().getMaterials().get(material);
        return value != null && value >= (portion * quantity);
    }

    private boolean checkEnoughMaterials(Map<Thing, Double> materials, int quantity) {
        for (Thing material : materials.keySet()) {
            if(!checkEnoughMaterial(material, materials.get(material), quantity)) {
                return false;
            }
        }
        return true;
    }

    public synchronized void removeMaterial(Thing material, double portion, int quantity) {
        Map<Thing, Double> storageMaterials = Storage.getInstance().getMaterials();
        Double value = storageMaterials.get(material);
        storageMaterials.put(material, value - portion * quantity);
    }

    public synchronized boolean removeMaterials(Map<Thing, Double> materials, int quantity) {
        if(!checkEnoughMaterials(materials, quantity)) {
            return false;
        }
        for (Thing material : materials.keySet()) {
            removeMaterial(material, materials.get(material), quantity);
        }
        return true;
    }

    public Map<Thing, Double> getAllMaterials() {
        return Storage.getInstance().getMaterials();
    }
}
