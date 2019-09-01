package ru.job4j.io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    private File fileIn,
        fileOut;
    private Analizy analizy;
    private String[] testData = {
            "200 10:56:01",
            "500 10:57:01",
            "400 10:58:01",
            "200 10:59:01",
            "500 11:01:02",
            "200 11:02:02"
    };

    private String[] expectedResult = {
            "10:57:01;10:59:01;",
            "11:01:02;11:02:02;"
    };

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Before
    public void createTempFolder() throws IOException {
        fileIn = temporaryFolder.newFile("analizyTestFile.txt");
        fileOut = temporaryFolder.newFile("unavailable.csv");
        analizy = new Analizy();
    }

    @Test
    public void test() {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(fileIn))) {
            Arrays.stream(testData).forEach(line -> out.println(line));
        } catch (Exception e) {
            e.printStackTrace();
        }

        analizy.unavailable(fileIn.getPath(), fileOut.getPath());

        try(BufferedReader reader = new BufferedReader(new FileReader(fileOut.getPath()))) {
            String[] lines = reader.lines().toArray(String[]::new);
            assertThat(lines.length, is(2));
            assertArrayEquals(expectedResult, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}