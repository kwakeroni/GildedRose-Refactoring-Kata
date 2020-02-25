# Gilded Rose Refactoring Kata

## Building
To run the result of this Kata, it must be built using Maven (version 3.3.9 or newer):
```bash
mvn package
```

This will produce a runnable jar called `gilded-rose.jar` in the target folder.

## Running
The jar can be run from the command line and requires two arguments:
* input CSV File, containing items with SellIn and Quality value
* a number of days that have elapsed (integer value)

Example:
```bash
java -jar ./target/gilded-rose.jar ./src/test/resources/com/gildedrose/GildedRoseRunnerTest-input.csv 18
```

The application will output the state of the items after the elapsed number of days to the standard output in CSV format.

## Input CSV File
Given the wide variety in CSV formats and options, the following points should be taken into account for best results:
* The first line must contain the headers: `name,sellIn,quality`
* Values must be separated by commas: `Aged Brie,2,0`
* Values containing commas must be quoted using double quotes: `"Sulfuras, Hand of Ragnaros", 0, 80`

Example:
```csv
name,sellIn,quality
+5 Dexterity Vest,10,20
Aged Brie,2,0
Elixir of the Mongoose,5,7
"Sulfuras,Hand of Ragnaros",0,80
"Sulfuras,Hand of Ragnaros",-1,80
Backstage passes to a TAFKAL80ETC concert,15,20
Backstage passes to a TAFKAL80ETC concert,10,49
Backstage passes to a TAFKAL80ETC concert,5,49
foo,0,0
Conjured Mana Cake,3,6
```