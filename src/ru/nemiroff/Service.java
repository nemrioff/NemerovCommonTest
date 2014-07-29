package ru.nemiroff;

/**
 * Created by nemiroff on 29.07.2014.
 */
public interface Service {

    buyProduct(Product product, Float quantity);

    sellMaterial(Material material, Float quantity);

}
