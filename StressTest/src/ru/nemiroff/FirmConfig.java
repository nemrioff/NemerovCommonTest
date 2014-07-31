package ru.nemiroff;

import java.rmi.RemoteException;
import java.util.Map;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class FirmConfig {

    private static FirmConfig instance = null;

    private Map<String, Double> materialsPrice = null;
    private Map<String, Double> productsPrice = null;

    private FirmConfig(){
        try {
            materialsPrice = ServiceHelper.getService().getMaterialsPrice();
            productsPrice = ServiceHelper.getService().getProductsPrice();
        } catch (RemoteException e) {
            System.err.println("Error retrieving configuration");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static FirmConfig getInstance() {
        if(instance == null) {
            instance = new FirmConfig();
        }
        return instance;
    }

    public Map<String, Double> getMaterialsPrice() {
        return materialsPrice;
    }

    public Map<String, Double> getProductsPrice() {
        return productsPrice;
    }
}
