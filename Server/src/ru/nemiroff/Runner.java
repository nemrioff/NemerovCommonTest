package ru.nemiroff;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Runner {

    public static Service service = new ServiceImpl();

    public static void main(String args[]) {
        service.sellMaterial("water", 56);
        service.sellMaterial("water", 0.000001f);
        service.sellMaterial("water", 1000);
        service.sellMaterial("beef", 10);

        Storage.getInstance().ptintMaterials();
        System.out.println(Account.getInstance().getMoney());
    }
}
