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


        @Nested
        @DisplayName("Degrades normal items")
        class NormalItemTest {
            @Test
            @DisplayName("(by 1)")
            void testNormalItemDecrement() {
                Item[] items = new Item[]{new Item("foo", 4, 8)};
                GildedRose app = new GildedRose(items);
                app.updateQuality();

                assertThat(app.items[0].name).isEqualTo("foo");
                assertThat(app.items[0].sellIn).isEqualTo(3);
                assertThat(app.items[0].quality).isEqualTo(7);
            }

            @Test
            @DisplayName("Once the sell by date has passed, Quality degrades twice as fast (by 2)")
            void testDoubleDecrementAfterSellByDate() {
                Item[] items = new Item[]{new Item("foo", 0, 8)};
                GildedRose app = new GildedRose(items);
                app.updateQuality();

                assertThat(app.items[0].name).isEqualTo("foo");
                assertThat(app.items[0].sellIn).isEqualTo(-1);
                assertThat(app.items[0].quality).isEqualTo(6);
            }

            @Test
            @DisplayName("The Quality of an item is never negative")
            void testQualityAtLeastZero() {
                Item[] items = new Item[]{new Item("foo", 0, 0)};
                GildedRose app = new GildedRose(items);
                app.updateQuality();

                assertThat(app.items[0].name).isEqualTo("foo");
                assertThat(app.items[0].sellIn).isEqualTo(-1);
                assertThat(app.items[0].quality).isEqualTo(0);
            }
        }

        @Nested
        @DisplayName("Degrades conjured items twice as fast as normal items")
        class ConjuredItemTest {
            @Test
            @DisplayName("(by 2)")
            void testConjuredItemDecrement() {
                Item[] items = new Item[]{new Item("Conjured Balanced Budget", 4, 8)};
                GildedRose app = new GildedRose(items);
                app.updateQuality();

                assertThat(app.items[0].name).isEqualTo("Conjured Balanced Budget");
                assertThat(app.items[0].sellIn).isEqualTo(3);
                assertThat(app.items[0].quality).isEqualTo(6);
            }

            @Test
            @DisplayName("Once the sell by date has passed, Quality degrades twice as fast (by 4)")
            void testDoubleDecrementAfterSellByDate() {
                Item[] items = new Item[]{new Item("Conjured Balanced Budget", 0, 8)};
                GildedRose app = new GildedRose(items);
                app.updateQuality();

                assertThat(app.items[0].name).isEqualTo("Conjured Balanced Budget");
                assertThat(app.items[0].sellIn).isEqualTo(-1);
                assertThat(app.items[0].quality).isEqualTo(4);
            }

            @Test
            @DisplayName("The Quality of an item is never negative")
            void testQualityAtLeastZero() {
                Item[] items = new Item[]{new Item("Conjured Balanced Budget", 4, 1)};
                GildedRose app = new GildedRose(items);
                app.updateQuality();

                assertThat(app.items[0].name).isEqualTo("Conjured Balanced Budget");
                assertThat(app.items[0].sellIn).isEqualTo(3);
                assertThat(app.items[0].quality).isEqualTo(0);
            }
        }

    }
}
