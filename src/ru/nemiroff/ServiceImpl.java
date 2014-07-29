package ru.nemiroff;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class ServiceImpl implements Service {

    private StorageManager storageManager = new StorageManager();
    private AccountManager accountManager = new AccountManager();
    private ThingsManager productsManager = DocumentToEntitiesMapper.getInstance().getProductsManager();
    private ThingsManager materialsManager = DocumentToEntitiesMapper.getInstance().getMaterialsManager();

    public boolean buyProduct(String productName, float quantity) {
        Thing product = productsManager.getThingByName(productName);
        if(!storageManager.checkEnoughMaterials(product.getProportions(), quantity)) {
            return false;
        }
        storageManager.removeMaterials(product.getProportions(), quantity);
        accountManager.addMoney(product.getPrice());
        return true;
    }

    public boolean sellMaterial(String materialName, float quantity) {
        Thing material = materialsManager.getThingByName(materialName);
        if(!accountManager.checkEnoughMoney(material.getPrice())) {
            return false;
        }
        accountManager.subMoney(material.getPrice());
        storageManager.addMaterial(material, quantity);
        return true;
    }

}
