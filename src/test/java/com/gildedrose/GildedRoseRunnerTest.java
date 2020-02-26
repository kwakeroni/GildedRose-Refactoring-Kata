package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.assertj.core.api.Assertions.*;

public class GildedRoseRunnerTest {

    @Test
    @DisplayName("Runs GildedRose with command line arguments")
    public void testRunner() throws IOException {
        Path testFile = Files.createTempFile("GildedRoseRunnerTest-input", ".csv");
        Files.copy(GildedRoseRunnerTest.class.getResourceAsStream("GildedRoseRunnerTest-input.csv"), testFile, StandardCopyOption.REPLACE_EXISTING);

        String output = SystemOutput.capture(() -> GildedRoseRunner.main(testFile.toString(), "2"));
        assertThat(output.split("\\v+")).containsExactly(
                "name,sellIn,quality",
                "+5 Dexterity Vest,8,18",
                "Aged Brie,0,2",
                "Elixir of the Mongoose,3,5",
                "\"Sulfuras, Hand of Ragnaros\",0,80",
                "\"Sulfuras, Hand of Ragnaros\",-1,80",
                "Backstage passes to a TAFKAL80ETC concert,13,22",
                "Backstage passes to a TAFKAL80ETC concert,8,50",
                "Backstage passes to a TAFKAL80ETC concert,3,50",
                "foo,-2,0",
                "Conjured Mana Cake,1,2"
        );
    }

}
