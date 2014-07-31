package ru.nemiroff;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Created by nemiroff on 30.07.2014.
 */
public interface RMIService extends Remote {

    /*  Main methods for buy and sell things  */

    boolean buyProduct(String product, int quantity) throws RemoteException;

    boolean sellMaterial(String material, int quantity) throws RemoteException;

    /*  Utility methods for client work */

    Map<String, Double> getProductsPrice() throws RemoteException;

    Map<String, Double> getMaterialsPrice() throws RemoteException;

    /*  Statistic methods */

    Map<String, Double> getStorage() throws RemoteException;

    double getAmountOfFirmMoney() throws RemoteException;

}
