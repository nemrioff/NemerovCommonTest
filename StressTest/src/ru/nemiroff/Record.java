package ru.nemiroff;

/**
 * Created by nemiroff on 31.07.2014.
 */
public abstract class Record {

    protected String delimiter = ";";
    protected String decimalPoint = ",";

    public String getRecord(Object ... records) {
        String result = "";
        for (int i = 0; i < records.length; i++) {
            if(!decimalPoint.equals(".")) {
                result += records[i].toString().replace(".", decimalPoint);
            } else {
                result += records[i];
            }
            if(i < records.length - 1) {
                result += delimiter;
            }
        }
        return result + "\n";
    }

    public abstract String getHeader();

    public abstract String getRecordPreview();

}
