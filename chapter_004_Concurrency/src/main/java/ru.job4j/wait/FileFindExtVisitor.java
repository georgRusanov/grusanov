package ru.job4j.wait;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFindExtVisitor extends SimpleFileVisitor<Path> {
    private String ext;
    private List<String> paths = new ArrayList<>();

    public FileFindExtVisitor(String ext) {
        this.ext = ext;
    }

    public List<String> getPaths() {
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        if (path.toString().endsWith(ext)) {
            paths.add(path.toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
