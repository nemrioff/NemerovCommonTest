package ru.nemiroff;

/**
 * Created by nemiroff on 29.07.2014.
 */
public interface Service {

    boolean buyProduct(String product, float quantity);

    boolean sellMaterial(String material, float quantity);

}
