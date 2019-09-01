package ru.job4j.io;


import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Analizy {
    private final List<String> badStatus = Arrays.asList("400", "500");

    public void unavailable(String source, String target) {
        Boolean isPeriod = false;
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(source)));
            PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String[] lines = reader.lines().toArray(String[]::new);
            for (int i = 0; i < lines.length; i++) {
                String[] words = lines[i].split(" ");

                if (i == lines.length - 1) {
                    if (isPeriod) {
                        out.print(words[1] + ";");
                    }
                    return;
                }

                if (!isPeriod) {
                    if (badStatus.contains(words[0])) {
                        isPeriod = true;
                        out.print(words[1] + ";");
                    }
                    continue;
                }

                if (!badStatus.contains(words[0])) {
                    isPeriod = false;
                    out.println(words[1] + ";");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
