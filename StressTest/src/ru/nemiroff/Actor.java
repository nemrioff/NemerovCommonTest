package ru.nemiroff;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by nemiroff on 30.07.2014.
 */
public abstract class Actor implements Callable<StatisticModel> {

    protected int id;

    protected static boolean terminate = false;

    protected static boolean loggingEnabled;

    protected Random random = new Random();

    protected Actor(int id) {
        this.id = id;
    }

    protected void log(String message) {
        if(loggingEnabled) {
            System.out.println(message);
        }
    }

}
