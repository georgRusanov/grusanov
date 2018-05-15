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
    private volatile boolean finish = false;

    @GuardedBy("itself")
    private final Queue<String> paths = new LinkedList<>();

    @GuardedBy("itself")
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
                        paths.notifyAll();
                    }
                }
                finish = true;
            }
        };
        Thread read = new Thread() {
            @Override
            public void run() {
                Path file = null;
                do {
                    synchronized (paths) {
                        while (paths.isEmpty()) {
                            try {
                                paths.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        file = Paths.get(paths.poll());
                    }
                    if (file != null) {
                        try {
                            String content = new String(Files.readAllBytes(file));
                            if (content.contains(text)) {
                                synchronized (files) {
                                    files.add(file.toString());
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } while (!finish || file != null);
            }
        };
    }

    List<String> result() {
        synchronized (files) {
            return this.files;
        }
    }
}
