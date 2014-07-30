package ru.nemiroff;

import java.util.ArrayList;
import java.util.List;

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

}
