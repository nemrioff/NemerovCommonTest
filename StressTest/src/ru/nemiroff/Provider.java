package ru.nemiroff;

import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Provider extends Actor {

    private int waitingInterval;

    public Provider(int id, int waitingInterval) {
        super(id);
        this.waitingInterval = waitingInterval;
    }

    public ProviderStatisticModel call() throws Exception {
        while (!terminate) {
            try {
                Set<String> materialsSet = Config.getInstance().getMaterialsPrice().keySet();
                String[] materials = materialsSet.toArray(new String[materialsSet.size()]);
                String material = materials[random.nextInt(materials.length)];
                float quantity = random.nextInt(100) + 1;
                ServiceHelper.getService().sellMaterial(material, quantity);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Thread.sleep(waitingInterval);
        }
        System.out.println(id + " provider stopped");
        return null;
    }
}
