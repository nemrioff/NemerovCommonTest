package ru.nemiroff;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class StorageRecord extends Record{

    private String materialName;

    private float quantity;

    @Override
    public String getHeader() {
        return getRecord("Material name", "Materials quantity", "Sum");
    }

    @Override
    public String getRecordPreview() {
        float price = Config.getInstance().getMaterialsPrice().get(materialName);
        return getRecord(materialName, quantity, price * quantity);
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
