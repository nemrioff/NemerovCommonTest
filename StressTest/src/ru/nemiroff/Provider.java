package ru.nemiroff;

import java.rmi.RemoteException;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Provider extends Actor {

    public Provider(int id) {
        super(id);
    }

    public ProviderStatisticModel call() throws Exception {
        while (true) {
            try {
                String material = "water";
                float quantity = random.nextInt(10000) / 100f;
                ServiceHelper.getService().sellMaterial(material, quantity);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
