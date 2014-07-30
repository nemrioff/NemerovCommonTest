package ru.nemiroff;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by nemiroff on 29.07.2014.
 */
public interface Service {

    /*  Main methods for buy and sell things  */

    boolean buyProduct(String product, float quantity);

    boolean sellMaterial(String material, float quantity);

    /*  Utility methods for client work */

    Map<String, Float> getProductsPrice();

    Map<String, Float> getMaterialsPrice();

    /*  Statistic methods */

    BigDecimal getAmountOfFirmMoney();

}
