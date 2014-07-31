package ru.nemiroff;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class ModelsToRecords {

    public static List<Record> getOrdersByCustomerRecords(List<CustomerStatisticModel> models) {
        List<Record> result = new ArrayList<Record>();
        for (CustomerStatisticModel model : models) {
            OrdersByCustomerRecord record = new OrdersByCustomerRecord();
            record.setId(model.getCustomerId());
            record.setOrdersSum(model.getSpentMoney());
            record.setMoney(model.getMoney());
            result.add(record);
        }
        return result;
    }

    public static List<Record> getOrdersByProductsRecords(List<ProductStatisticModel> models) {
        List<Record> result = new ArrayList<Record>();
        for (ProductStatisticModel model : models) {
            OrdersByProductRecord record = new OrdersByProductRecord();
            record.setProductName(model.getProductName());
            record.setProductQt(model.getProductsQt());
            record.setOrdersCount(model.getOrdersCount());
            record.setSum(model.getSum());
            result.add(record);
        }
        return result;
    }

    public static List<Record> getStorageRecords(Map<String, Float> models) {
        List<Record> result = new ArrayList<Record>();
        for (String material : models.keySet()) {
            StorageRecord record = new StorageRecord();
            record.setMaterialName(material);
            record.setQuantity(models.get(material));
            result.add(record);
        }
        return result;
    }
}
