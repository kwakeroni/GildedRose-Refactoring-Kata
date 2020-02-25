package com.gildedrose.io;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvFormatTest {

    @Test
    @DisplayName("Outputs items in CSV format")
    public void testCsvOutput() throws Exception {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("foo", 0, 0),
                new Item("Conjured Mana Cake", 3, 6)};

        StringWriter writer = new StringWriter();
        new CsvFormat().writeItems(items, writer);

        String output = writer.toString();
        assertThat(output.split("\\v+")).containsExactly(
                "name,sellIn,quality",
                "+5 Dexterity Vest,10,20",
                "Aged Brie,2,0",
                "Elixir of the Mongoose,5,7",
                "\"Sulfuras, Hand of Ragnaros\",0,80",
                "\"Sulfuras, Hand of Ragnaros\",-1,80",
                "Backstage passes to a TAFKAL80ETC concert,15,20",
                "Backstage passes to a TAFKAL80ETC concert,10,49",
                "Backstage passes to a TAFKAL80ETC concert,5,49",
                "foo,0,0",
                "Conjured Mana Cake,3,6"
        );
    }
}
