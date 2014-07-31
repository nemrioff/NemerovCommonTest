package ru.nemiroff;

import java.rmi.RemoteException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Provider extends Actor {

    private int waitingInterval;

    private static final int MAX_MATERIALS_FOR_PROVIDER = 100;

    public Provider(int id, int waitingInterval) {
        super(id);
        this.waitingInterval = waitingInterval;
    }

    public ProviderStatisticModel call() throws Exception {
        log(id + " provider started");
        while (!terminate) {
            try {
                Set<String> materialsSet = FirmConfig.getInstance().getMaterialsPrice().keySet();
                String[] materials = materialsSet.toArray(new String[materialsSet.size()]);
                String material = materials[random.nextInt(materials.length)];
                int quantity = random.nextInt(MAX_MATERIALS_FOR_PROVIDER) + 1;
                ServiceHelper.getService().sellMaterial(material, quantity);
            } catch (RemoteException e) {
                // if there are network problems then just try again
                e.printStackTrace();
            }
            TimeUnit.MILLISECONDS.sleep(waitingInterval);
        }
        log(id + " provider stopped");
        return null;
    }
}
