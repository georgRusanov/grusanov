package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchTest {
    List<String> exts = new ArrayList<>();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder(new File(System.getProperty("java.io.tmpdir")));

    @Test
    public void emptyRootFolderReturnEmptyList() {
        Search search = new Search();
        exts.add("a");
        List<File> files = search.files(temporaryFolder.getRoot().getPath(), exts);
        assertTrue(files.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyExtensionListThrowException() {
        Search search = new Search();
        List<File> files = search.files(temporaryFolder.getRoot().getPath(), exts);
    }

    @Test
    public void positiveTest() throws IOException {
        Search search = new Search();
        exts.add("a");
        exts.add("b");
        temporaryFolder.newFolder("a");
        File folderB = temporaryFolder.newFolder("b");
        File fileB = new File(folderB, "v.b");
        fileB.createNewFile();
        File fileV = new File(folderB, "b.v");
        fileV.createNewFile();
        File fileA = new File(temporaryFolder.getRoot(), "s.a");
        fileA.createNewFile();
        List<File> files = search.files(temporaryFolder.getRoot().getPath(), exts);
        List<File> expected = new LinkedList<>();
        expected.add(fileA);
        expected.add(fileB);
        assertEquals(expected, files);
    }
}
