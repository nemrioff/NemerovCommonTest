package ru.nemiroff;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class ServiceHelper {

    private static RMIService service = null;

    public static RMIService getService() {
        if(service == null) {
            try {
                Registry registry = LocateRegistry.getRegistry(null, 12345);
                service = (RMIService) registry.lookup("RMIService");
            } catch (Exception e) {
                System.out.println("Error occured: " + e.getMessage());
                System.exit(1);
            }
        }
        return service;

    }

}
