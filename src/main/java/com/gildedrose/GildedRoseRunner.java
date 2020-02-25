package com.gildedrose;

import com.gildedrose.io.CsvFormat;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GildedRoseRunner {

    private final Path inputFile;
    private final int numberOfDays;

    public static void main(String... args) {
        GildedRoseRunner runner;
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("Expected 2 arguments but got " + args.length);
            }
            runner = new GildedRoseRunner(Paths.get(args[0]), Integer.parseInt(args[1]));
        } catch (Exception exc) {
            System.err.println(exc.toString());
            printUsage();
            return;
        }

        try {
            runner.run();
        } catch (Exception exc) {
            System.err.println(exc.toString());
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java -jar gilded-rose.jar <CSV File> <Number of Days>");
        System.out.println("Example: java -jar gilded-rose.jar /home/theprancingpony/items.csv 18");
        System.out.println();
    }

    public GildedRoseRunner(Path inputFile, int numberOfDays) {
        this.inputFile = inputFile;
        this.numberOfDays = numberOfDays;
    }

    public void run() throws IOException {
        CsvFormat format = new CsvFormat();
        Item[] items = format.parseItems(Files.newBufferedReader(inputFile));
        GildedRose gildedRose = new GildedRose(items);

        for (int i = 0; i < numberOfDays; i++) {
            gildedRose.updateQuality();
        }

        format.writeItems(items, new PrintWriter(System.out));
    }
}
