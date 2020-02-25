package com.gildedrose;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class SystemOutput {

    private SystemOutput() {

    }

    public static String capture(Runnable action) {
        PrintStream originalSystemOut = System.out;
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();

        try (PrintStream printStream = new PrintStream(outputBuffer)) {
            System.setOut(printStream);
            action.run();
            printStream.flush();
            return new String(outputBuffer.toByteArray());
        } finally {
            System.setOut(originalSystemOut);
        }
    }
}
