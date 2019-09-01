package ru.job4j.io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class ConfigTest {
    private File file;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void createTempFolder() throws IOException {
        file = temporaryFolder.newFile("configTestFile.txt");
    }

    @Test
    public void positiveTest() {
        saveToFile("#a=b\n\r\n\rc=d");
        Config config = new Config(file.getPath());
        config.load();
        assertThat(config.value("c"), is("d"));
        assertThat(config.value("a"), isEmptyOrNullString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeTest() {
        saveToFile("#a=b\n\r\n\rc=d=");
        Config config = new Config(file.getPath());
        config.load();
    }

    private void saveToFile(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
