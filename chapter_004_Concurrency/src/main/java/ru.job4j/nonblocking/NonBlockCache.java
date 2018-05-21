package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockCache {
    private volatile int version;
    ConcurrentHashMap<String,Model> models = new ConcurrentHashMap<>();

    public NonBlockCache() {
        version = 1;
    }

    boolean rightVersion(int actVersion) throws OptimisticException {
        if (actVersion == version) {
            return true;
        } else {
            throw new OptimisticException("Данные изменены другим");
        }
    }

    void add(String key, Model model) throws OptimisticException {
        int currentVersion = version;
        if (rightVersion(currentVersion)) {
            models.put(key, model);
            version++;
        }
    }

    void updateName(String key, String newName) throws OptimisticException {
        int currentVersion = version;
        if (models.containsKey(key) && rightVersion(currentVersion)) {
            models.get(key).setName(newName);
            version++;
        }
    }

    void delete(String key) throws OptimisticException {
        int currentVersion = version;
        if (models.containsKey(key) && rightVersion(currentVersion)) {
            models.remove(key);
            version++;
        }
    }

}

class Model {

    private String name;

    Model(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
