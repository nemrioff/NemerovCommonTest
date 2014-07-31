package ru.nemiroff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by nemiroff on 30.07.2014.
 */
public class Runner {

    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("StressTest/params.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Config.getInstance();
        ProductStatisticModelPool.getInstance();

        int customerQt = Integer.parseInt(properties.getProperty("customer.quantity"));
        float customerMoney = Float.parseFloat(properties.getProperty("customer.money"));
        int customerMaxDenials = Integer.parseInt(properties.getProperty("customer.maxDenials"));
        int providerQt = Integer.parseInt(properties.getProperty("provider.quantity"));
        int providerInterval = Integer.parseInt(properties.getProperty("provider.interval.ms"));

        ExecutorService service = Executors.newFixedThreadPool(providerQt + customerQt);
        List<Future<StatisticModel>> providerResults = new ArrayList<Future<StatisticModel>>();
        for (int i = 0; i < providerQt; i++) {
            providerResults.add(service.submit(new Provider(i, providerInterval)));
        }
        List<Future<StatisticModel>> results = new ArrayList<Future<StatisticModel>>();
        List<Customer> customers = CustomerFactory.newCustomers(customerQt, customerMaxDenials, customerMoney);
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

        for (Future<StatisticModel> providerResult : providerResults) {
            try {
                providerResult.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            Writer ordersByCustomer = new FileWriter("ordersByCustomer.csv");
            List<Record> records = ModelsToRecords.getOrdersByCustomerRecords(model);
            new StatisticTable(records, ordersByCustomer).printTable();
            ordersByCustomer.close();

            List<ProductStatisticModel> productModel = ProductStatisticModelPool.getInstance().getModel();
            Writer ordersByProducts = new FileWriter("ordersByProducts.csv");
            records = ModelsToRecords.getOrdersByProductsRecords(productModel);
            new StatisticTable(records, ordersByProducts).printTable();
            ordersByProducts.close();

            Map<String, Float> storage = ServiceHelper.getService().getStorage();
            Writer storageWriter = new FileWriter("storage.csv");
            records = ModelsToRecords.getStorageRecords(storage);
            new StatisticTable(records, storageWriter).printTable();
            storageWriter.close();

            System.out.println(ServiceHelper.getService().getAmountOfFirmMoney());

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.exit(0);

    }

}
