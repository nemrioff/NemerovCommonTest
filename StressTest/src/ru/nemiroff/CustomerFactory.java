package ru.nemiroff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class CustomerFactory {

    public static List<Customer> newCustomers(int quantity, int availableDenials, float money) {
        List<Customer> result = new ArrayList<Customer>(quantity);
        for (int i = 0; i < quantity; i++) {
            result.add(new Customer(i, availableDenials, money));
        }
        return result;
    }

}
