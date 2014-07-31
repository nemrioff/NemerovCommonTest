package ru.nemiroff;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class ServiceHelper {

    private static RMIService service = null;

    public static void startService(String host, int port, String serviceName) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            service = (RMIService) registry.lookup(serviceName);
        } catch (Exception e) {
            System.out.println("Error occured: " + e.getMessage());
            System.exit(1);
        }
    }

    public static RMIService getService() {
        return service;
    }

}
