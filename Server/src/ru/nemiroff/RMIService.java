package ru.nemiroff;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by nemiroff on 30.07.2014.
 */
public interface RMIService extends Remote {

    /*  Main methods for buy and sell things  */

    boolean buyProduct(String product, float quantity) throws RemoteException;

    boolean sellMaterial(String material, float quantity) throws RemoteException;

    /*  Utility methods for client work */

    Map<String, Float> getProductsPrice() throws RemoteException;

    Map<String, Float> getMaterialsPrice() throws RemoteException;

    /*  Statistic methods */

    Map<String, Float> getStorage() throws RemoteException;;

    BigDecimal getAmountOfFirmMoney() throws RemoteException;

}
