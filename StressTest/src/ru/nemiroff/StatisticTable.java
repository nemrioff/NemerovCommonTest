package ru.nemiroff;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Created by nemiroff on 31.07.2014.
 */
public class StatisticTable {

    private List<Record> records;

    private Writer writer;

    public StatisticTable(List<Record> records, Writer writer) {
        this.records = records;
        this.writer = writer;
    }

    public void printTable() {
        try {
            if (records.size() != 0) {
                writer.write(records.get(0).getHeader());
            }
            for (Record record : records) {
                writer.write(record.getRecordPreview());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
