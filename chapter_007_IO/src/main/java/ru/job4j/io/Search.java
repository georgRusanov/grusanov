package ru.job4j.io;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        if (exts.isEmpty()) {
            throw new IllegalArgumentException("Передан пустой список расширений");
        }
        List<File> files = new LinkedList<>();
        Queue<File> filesAndDirs = new LinkedList<>(Arrays.asList(new File(parent).listFiles()));
        while (!filesAndDirs.isEmpty()) {
            File file = filesAndDirs.poll();
            File[] as = file.listFiles();
            if (file.isFile()) {
                if (isFileHaveNecessaryExtension(file, exts)) {
                    files.add(file);
                }
            } else {
                filesAndDirs.addAll(Arrays.asList(file.listFiles()));
            }
        }
        return files;
    }

    private boolean isFileHaveNecessaryExtension(File file, List<String> exts) {
        for (String ext : exts) {
            if (file.getName().endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }
}
