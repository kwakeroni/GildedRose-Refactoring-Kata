package com.gildedrose.io;

import com.gildedrose.Item;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.IOException;
import java.io.Writer;

public class CsvFormat {
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT
            .withIgnoreSurroundingSpaces(true)
            .withQuoteMode(QuoteMode.MINIMAL);

    public void writeItems(Item[] items, Writer destination) throws IOException {
        CSVPrinter printer = new CSVPrinter(destination, FORMAT);
        printer.printRecord(columnNames());
        for (Item item : items) {
            printer.printRecord(toRecord(item));
        }
        printer.flush();
    }

    private Object[] columnNames() {
        return new Object[]{"name", "sellIn", "quality"};
    }

    private Object[] toRecord(Item item) {
        return new Object[]{item.name, item.sellIn, item.quality};
    }
}
