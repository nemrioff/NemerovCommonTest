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

//TODO: ugly class. Need to refactor
public class Runner {

    public static void main(String[] args) {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("StressTest/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String rmiHost = properties.getProperty("rmi.host");
        int rmiPort = Integer.parseInt(properties.getProperty("rmi.port"));
        String serviceName = properties.getProperty("rmi.service.name");
        boolean loggingEnabled = properties.getProperty("logToConsoleInActor").equals("true");

        ServiceHelper.startService(rmiHost, rmiPort, serviceName);

        FirmConfig.getInstance();
        ProductStatisticModelPool.getInstance();

        Actor.loggingEnabled = loggingEnabled;

        try {
            List<CustomerStatisticModel> model = doThreadsWork(properties);
            Writer ordersByCustomer = new FileWriter("ordersByCustomer.csv");
            List<Record> records = ModelsToRecords.getOrdersByCustomerRecords(model);
            new StatisticTable(records, ordersByCustomer).printTable();
            ordersByCustomer.close();

            List<ProductStatisticModel> productModel = ProductStatisticModelPool.getInstance().getModel();
            Writer ordersByProducts = new FileWriter("ordersByProducts.csv");
            records = ModelsToRecords.getOrdersByProductsRecords(productModel);
            new StatisticTable(records, ordersByProducts).printTable();
            ordersByProducts.close();

            Map<String, Double> storage = ServiceHelper.getService().getStorage();
            Writer storageWriter = new FileWriter("storage.csv");
            records = ModelsToRecords.getStorageRecords(storage);
            new StatisticTable(records, storageWriter).printTable();
            storageWriter.close();

            final double money = ServiceHelper.getService().getAmountOfFirmMoney();
            Writer moneyWriter = new FileWriter("money.csv");
            Record moneyRecord = new Record() {
                @Override
                public String getHeader() {
                    return getRecord("Firm money");
                }
                @Override
                public String getRecordPreview() {
                    return getRecord(money);
                }
            };
            List<Record> recordList = new ArrayList<Record>(1);
            recordList.add(moneyRecord);
            new StatisticTable(recordList, moneyWriter).printTable();
            moneyWriter.close();

            System.out.println("Statistic files are in the current folder");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }

    private static List<CustomerStatisticModel> doThreadsWork(Properties properties) {
        int customerQt = Integer.parseInt(properties.getProperty("customer.quantity"));
        double customerMoney = Double.parseDouble(properties.getProperty("customer.money"));
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
        return model;
    }

}
