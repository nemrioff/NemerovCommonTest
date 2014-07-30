package ru.nemiroff;

/**
 * Created by nemiroff on 31.07.2014.
 */
public abstract class Record {

    protected String delimiter = "\t|\t";

    public abstract String getHeader();

    public abstract String getRecordPreview();

}
