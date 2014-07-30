package ru.nemiroff;

import java.rmi.RemoteException;
import java.util.Map;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class Config {

    private static Config instance = null;

    private Map<String, Float> materialsPrice = null;
    private Map<String, Float> productsPrice = null;

    private Config(){
        try {
            materialsPrice = ServiceHelper.getService().getMaterialsPrice();
            productsPrice = ServiceHelper.getService().getProductsPrice();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static Config getInstance() {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public Map<String, Float> getMaterialsPrice() {
        return materialsPrice;
    }

    public Map<String, Float> getProductsPrice() {
        return productsPrice;
    }
}
