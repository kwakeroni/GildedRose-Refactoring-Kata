package com.gildedrose.io;

import com.gildedrose.Item;
import org.apache.commons.csv.*;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CsvFormat {

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SELL_IN = "sellIn";
    public static final String COLUMN_QUALITY = "quality";

    private static final CSVFormat BASE_FORMAT = CSVFormat.DEFAULT
            .withIgnoreSurroundingSpaces(true)
            .withQuoteMode(QuoteMode.MINIMAL);

    private static final CSVFormat INPUT_FORMAT = BASE_FORMAT.withFirstRecordAsHeader();
    private static final CSVFormat OUTPUT_FORMAT = BASE_FORMAT.withHeader(COLUMN_NAME, COLUMN_SELL_IN, COLUMN_QUALITY);

    public Item[] parseItems(Reader reader) throws IOException {
        CSVParser parser = new CSVParser(reader, INPUT_FORMAT);
        return parser.getRecords()
                .stream()
                .map(this::toItem)
                .toArray(Item[]::new);
    }

    private Item toItem(CSVRecord record) {
        return new Item(
                record.get(COLUMN_NAME),
                Integer.parseInt(record.get(COLUMN_SELL_IN)),
                Integer.parseInt(record.get(COLUMN_QUALITY))
        );
    }

    public void writeItems(Item[] items, Writer destination) throws IOException {
        CSVPrinter printer = new CSVPrinter(destination, OUTPUT_FORMAT);
        for (Item item : items) {
            printer.printRecord(toRecord(item));
        }
        printer.flush();
    }

    private Object[] toRecord(Item item) {
        return new Object[]{item.name, item.sellIn, item.quality};
    }
}
