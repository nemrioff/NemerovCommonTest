package ru.nemiroff;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Runner {

    public static void main(String[] args) {

        Config.getInstance();

        ExecutorService service = Executors.newFixedThreadPool(600);
        for (int i = 0; i < 300; i++) {
            service.submit(new Provider(i));
        }
        List<Future<StatisticModel>> results = new ArrayList<Future<StatisticModel>>();
        List<Customer> customers = CustomerFactory.newCustomers(300, 5, 10000);
        for (Customer customer : customers) {
            results.add(service.submit(customer));
        }
        List<CustomerStatisticModel> model = new ArrayList<CustomerStatisticModel>();
        for (Future<StatisticModel> fs : results) {
            try {
                model.add((CustomerStatisticModel) fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        Writer writer = new PrintWriter(System.out);

        List<Record> records = ModelsToRecords.getOrdersByCustomerRecords(model);

        System.out.println("dsfgdg");

        new StatisticTable(records, writer).printTable();
    }

}
