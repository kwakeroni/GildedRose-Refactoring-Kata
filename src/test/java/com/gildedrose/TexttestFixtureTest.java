package com.gildedrose;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TexttestFixture")
class TexttestFixtureTest {

    @Test
    @DisplayName("Produces expected output")
    public void testSystemOutput() throws IOException {
        String output = SystemOutput.capture(() -> TexttestFixture.main(new String[]{"20"}));

        String expected = IOUtils.toString(TexttestFixtureTest.class.getResource("TexttestFixture-expected-output.txt"), StandardCharsets.UTF_8);
        assertThat(output).isEqualTo(expected);
    }
}
