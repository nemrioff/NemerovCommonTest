package ru.nemiroff;

import java.io.FileInputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Runner {

    public static void main(String args[]) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("Server/config.properties"));
            RMIService service;
            if(args.length > 0) {
                 service = new RMIServiceImpl(Double.parseDouble(args[0]));
            } else {
                System.out.println("You should specify the initial capital in the first argument");
                return;
            }

            RMIService stub = (RMIService) UnicastRemoteObject.exportObject(service, 0);

            Registry registry = LocateRegistry.createRegistry(Integer.parseInt(properties.getProperty("rmi.port")));
            registry.bind(properties.getProperty("rmi.service.name"), stub);

            System.out.println("RMI Server started:");

        } catch (Exception e) {
            System.err.println("RMI Server not started:");
            e.printStackTrace();
        }

    }
}
