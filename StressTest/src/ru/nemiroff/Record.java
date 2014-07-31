package ru.nemiroff;

import java.util.List;

/**
 * Created by nemiroff on 31.07.2014.
 */
public abstract class Record {

    protected String delimiter = ";";

    public String getRecord(Object ... records) {
        String result = "";
        for (int i = 0; i < records.length; i++) {
            result += records[i].toString().replace(".",",");
            if(i < records.length - 1) {
                result += delimiter;
            }
        }
        return result + "\n";
    }

    public abstract String getHeader();

    public abstract String getRecordPreview();

}
