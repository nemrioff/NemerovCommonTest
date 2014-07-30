package ru.nemiroff;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Runner {

    public static Service service = new ServiceImpl();

    public static void main(String args[]) {
        try {
            RMIService service = new RMIServiceImpl();
            RMIService stub = (RMIService) UnicastRemoteObject.exportObject(service, 0);

            Registry registry = LocateRegistry.createRegistry(12345);
            registry.bind("RMIService", stub);

            System.out.println("RMIService started:");

        } catch (Exception e) {
            System.err.println("RMIService exception:");
            e.printStackTrace();
        }

//        service.sellMaterial("water", 10);
//        service.sellMaterial("beef", 10);
//        service.sellMaterial("potatoes", 10);
//        service.sellMaterial("beet", 10);
//        service.sellMaterial("carrots", 10);
//        service.sellMaterial("cabbage", 10);
//        service.sellMaterial("onion", 10);
//        service.sellMaterial("tomato-paste", 10);
//        service.sellMaterial("salt", 10);
//
//        System.out.println(service.buyProduct("borsch", 1));
//
//        Storage.getInstance().ptintMaterials();
//        System.out.println(Account.getInstance().getMoney());
    }
}
