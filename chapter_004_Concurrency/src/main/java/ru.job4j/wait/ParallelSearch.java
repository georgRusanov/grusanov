package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;
    volatile boolean finish = false;

    @GuardedBy("this")
    private final Queue<String> paths = new LinkedList<>();

    @GuardedBy("this")
    private final List<String> files = new ArrayList<>();


    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public void init() {
        Thread search = new Thread() {
            @Override
            public void run() {
                Path startPath = Paths.get(root);
                for (String ext : exts) {
                    FileFindExtVisitor mfv = new FileFindExtVisitor(ext);
                    try {
                        Files.walkFileTree(startPath, mfv);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    synchronized (paths) {
                        paths.addAll(mfv.getPaths());
                    }
                }
                finish = true;
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                synchronized (paths) {
                    while (!finish || paths.peek() != null) {
                        if (paths.peek() == null && !finish) {
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Path file = Paths.get(paths.poll());
                        try {
                            String content = new String(Files.readAllBytes(file));
                            if(!content.contains(text)) {
                                files.add(file.toString());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
    }

    synchronized List<String> result() {
        return this.files;
    }
}
