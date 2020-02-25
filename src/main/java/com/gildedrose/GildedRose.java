package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (hasImprovingQuality(item)) {
                improveQuality(item);
            } else {
                degradeQuality(item);
            }

            if (!isLegendaryItem(item)) {
                item.sellIn = item.sellIn - 1;
            }

            if (isPastSellByDate(item)) {
                if (hasImprovingQuality(item)) {
                    improveQualityAfterSellByDate(item);
                } else {
                    degradeQuality(item);
                }
            }
        }
    }

    private boolean hasImprovingQuality(Item item) {
        return item.name.equals("Aged Brie")
               || item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isLegendaryItem(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isPastSellByDate(Item item) {
        return item.sellIn < 0;
    }

    private void degradeQuality(Item item) {
        if (item.quality > 0) {
            if (!isLegendaryItem(item)) {
                item.quality = item.quality - 1;
            }
        }
    }

    private static void improveQuality(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (item.sellIn < 11) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }

                if (item.sellIn < 6) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private static void improveQualityAfterSellByDate(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1;
            }
        }
    }
}