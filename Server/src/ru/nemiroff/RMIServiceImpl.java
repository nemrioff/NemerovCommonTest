package ru.nemiroff;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class RMIServiceImpl implements RMIService {

    Service service = new ServiceImpl();

    protected RMIServiceImpl() throws RemoteException {
    }

    public boolean buyProduct(String product, float quantity) throws RemoteException {
        return service.buyProduct(product, quantity);
    }

    public boolean sellMaterial(String material, float quantity) throws RemoteException {
        return service.sellMaterial(material, quantity);
    }

    public Map<String, Float> getProductsPrice() throws RemoteException {
        return service.getProductsPrice();
    }

    public Map<String, Float> getMaterialsPrice() throws RemoteException {
        return service.getMaterialsPrice();
    }

    public BigDecimal getAmountOfFirmMoney() throws RemoteException {
        return service.getAmountOfFirmMoney();
    }

}
