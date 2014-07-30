package ru.nemiroff;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class ServiceImpl implements Service {

    private StorageManager storageManager = new StorageManager();
    private AccountManager accountManager = new AccountManager();
    private ThingsManager productsManager = DocumentToEntitiesMapper.getInstance().getProductsManager();
    private ThingsManager materialsManager = DocumentToEntitiesMapper.getInstance().getMaterialsManager();

    public boolean buyProduct(String productName, float quantity) {
        Thing product = productsManager.getThingByName(productName);
        if(!storageManager.checkEnoughMaterials(product.getProportions(), quantity)) {
            return false;
        }
        storageManager.removeMaterials(product.getProportions(), quantity);
        accountManager.addMoney(product.getPrice() * quantity);
        return true;
    }

    public boolean sellMaterial(String materialName, float quantity) {
        Thing material = materialsManager.getThingByName(materialName);
        if(!accountManager.checkEnoughMoney(material.getPrice() * quantity)) {
            return false;
        }
        accountManager.subMoney(material.getPrice() * quantity);
        storageManager.addMaterial(material, quantity);
        return true;
    }

    public Map<String, Float> getProductsPrice() {
        List<Thing> products = productsManager.getThings();
        Map<String, Float> result = new HashMap<String, Float>();
        for (Thing product : products) {
            result.put(product.getName(), product.getPrice());
        }
        return result;
    }

    public Map<String, Float> getMaterialsPrice(){
        List<Thing> materials = materialsManager.getThings();
        Map<String, Float> result = new HashMap<String, Float>();
        for (Thing material : materials) {
            result.put(material.getName(), material.getPrice());
        }
        return result;
    }

    public BigDecimal getAmountOfFirmMoney() {
        return accountManager.getAmountOfMoney();
    }

}
