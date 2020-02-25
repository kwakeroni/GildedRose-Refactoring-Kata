package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Gilded Rose App")
class GildedRoseTest {

    @Nested
    @DisplayName("Update Quality")
    class UpdateQualityTest {

        @Test
        @DisplayName("Degrades standard item with decrements of 1")
        void testStandardItemDecrement() {
            Item[] items = new Item[]{new Item("foo", 0, 0)};
            GildedRose app = new GildedRose(items);
            app.updateQuality();

            assertThat(app.items[0].name).isEqualTo("foo");
            assertThat(app.items[0].sellIn).isEqualTo(-1);
            assertThat(app.items[0].quality).isEqualTo(0);
        }
    }
}
