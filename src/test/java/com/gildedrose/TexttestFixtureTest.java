package com.gildedrose;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TexttestFixture")
class TexttestFixtureTest {

    @Test
    @DisplayName("Produces expected output")
    public void testSystemOutput() throws IOException {
        String output = captureSystemOutput(() -> TexttestFixture.main(new String[0]));

        String expected = IOUtils.toString(TexttestFixtureTest.class.getResource("TexttestFixture-expected-output.txt"), StandardCharsets.UTF_8);
        assertThat(output).isEqualTo(expected);
    }

    private String captureSystemOutput(Runnable action) {
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
