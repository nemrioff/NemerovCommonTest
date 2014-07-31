package ru.nemiroff;

import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public interface Service {

    /*  Main methods for buy and sell things  */

    boolean buyProduct(String product, int quantity) throws Exception;

    boolean sellMaterial(String material, int quantity) throws Exception;

    /*  Utility methods for client work */

    Map<String, Double> getProductsPrice() throws Exception;

    Map<String, Double> getMaterialsPrice() throws Exception;

    /*  Statistic methods */

    Map<String, Double> getStorage() throws Exception;

    double getAmountOfFirmMoney() throws Exception;

}
