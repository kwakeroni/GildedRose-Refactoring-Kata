package com.gildedrose.io;

import com.gildedrose.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvFormatTest {

    @Test
    @DisplayName("Imports items in CSV format")
    public void testCsvImport() throws Exception {
        String input = "name, sellIn, quality\n" +
                       "+5 Dexterity Vest, 10, 20\n" +
                       "Aged Brie, 2, 0\n" +
                       "Elixir of the Mongoose, 5, 7\n" +
                       "\"Sulfuras, Hand of Ragnaros\", 0, 80\n" +
                       "\"Sulfuras, Hand of Ragnaros\", -1, 80\n" +
                       "Backstage passes to a TAFKAL80ETC concert, 15, 20\n" +
                       "Backstage passes to a TAFKAL80ETC concert, 10, 49\n" +
                       "Backstage passes to a TAFKAL80ETC concert, 5, 49\n" +
                       "foo, 0, 0\n" +
                       "Conjured Mana Cake, 3, 6";

        Item[] items = new CsvFormat().parseItems(new StringReader(input));
        assertThat(items)
                .usingFieldByFieldElementComparator()
                .containsExactly(
                        new Item("+5 Dexterity Vest", 10, 20), //
                        new Item("Aged Brie", 2, 0), //
                        new Item("Elixir of the Mongoose", 5, 7), //
                        new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                        new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                        new Item("foo", 0, 0),
                        new Item("Conjured Mana Cake", 3, 6));
    }


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
        new CsvFormat().writeItems(items, new PrintWriter(System.out));
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
