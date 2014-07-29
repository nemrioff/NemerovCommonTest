package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class StorageManager {

    Map<Material, Float> storageMaterials = Storage.getInstance().getMaterials();

    public void addMaterial(Material material, Float quantity) {
        if (quantity == null) return;
        Float value = storageMaterials.get(material);
        if (value == null) {
            storageMaterials.put(material, quantity);
        } else {
            storageMaterials.put(material, value + quantity);
        }
    }

    public void addMaterials(Map<Material, Float> materials) {
        for (Material material : materials.keySet()) {
            addMaterial(material, materials.get(material));
        }
    }

    public boolean checkEnoughMaterial(Material material, Float quantity) {
        if (quantity == null) return false;
        Float value = storageMaterials.get(material);
        return value != null && value >= quantity;
    }

    public boolean checkEnoughMaterials(Map<Material, Float> materials) {
        for (Material material : materials.keySet()) {
            if(!checkEnoughMaterial(material, materials.get(material))) {
                return false;
            }
        }
        return true;
    }

    public void removeMaterial(Material material, Float quantity) {
        if(!checkEnoughMaterial(material, quantity)) return;
        if (quantity == null) return;
        Float value = storageMaterials.get(material);
        storageMaterials.put(material, value - quantity);
    }

    public void removeMaterials(Map<Material, Float> materials) {
        checkEnoughMaterials(materials);
        for (Material material : materials.keySet()) {
            removeMaterial(material, materials.get(material));
        }
    }

}
