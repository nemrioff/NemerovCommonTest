package ru.nemiroff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class ServiceImpl implements Service {

    private StorageManager storageManager = new StorageManager();
    private AccountManager accountManager = new AccountManager();
    private FirmConfig firmConfig = FirmConfigFactory.getConfigFactory();
    private ThingCollection productCollection = firmConfig.getProductCollection();
    private ThingCollection materialCollection = firmConfig.getMaterialCollection();

    public boolean buyProduct(String productName, int quantity) throws Exception {
        Thing product = productCollection.getThingByName(productName);
        if(!storageManager.removeMaterials(product.getProportions(), quantity)) {
            return false;
        }
        accountManager.addMoney(product.getPrice() * quantity);
        return true;
    }

    public boolean sellMaterial(String materialName, int quantity) throws Exception {
        Thing material = materialCollection.getThingByName(materialName);
        if(!accountManager.subMoney(material.getPrice() * quantity)) {
            return false;
        }
        storageManager.addMaterial(material, quantity);
        return true;
    }

    public Map<String, Double> getProductsPrice() throws Exception {
        List<Thing> products = productCollection.getThings();
        Map<String, Double> result = new HashMap<String, Double>();
        for (Thing product : products) {
            result.put(product.getName(), product.getPrice());
        }
        return result;
    }

    public Map<String, Double> getMaterialsPrice() throws Exception {
        List<Thing> materials = materialCollection.getThings();
        Map<String, Double> result = new HashMap<String, Double>();
        for (Thing material : materials) {
            result.put(material.getName(), material.getPrice());
        }
        return result;
    }

    public Map<String, Double> getStorage() throws Exception {
        Map<Thing, Double> storage = storageManager.getAllMaterials();
        Map<String, Double> result = new HashMap<String, Double>();
        for (Thing material : storage.keySet()) {
            result.put(material.getName(), storage.get(material));
        }
        return result;
    }

    public double getAmountOfFirmMoney() throws Exception {
        return accountManager.getAmountOfMoney();
    }

}
