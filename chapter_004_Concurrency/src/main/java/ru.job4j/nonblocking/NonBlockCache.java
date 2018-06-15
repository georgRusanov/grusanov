package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockCache {
    private ConcurrentHashMap<Integer, Base> models = new ConcurrentHashMap<>();
    private int id = 0;

    void add(Base model) {
        try {
            models.putIfAbsent(id, model);
            model.setId(id++);
        } catch (IdException ie) {
            System.out.println(ie.getMessage());
        }
    }

    void updateName(Base model) throws OptimisticException {
        models.computeIfPresent(model.getId(), (Integer k, Base v) -> {
            if(v.getVersion() == model.getVersion()) {
                model.incrementVersion();
                return model;
            } else {
                throw new OptimisticException("Model already changed");
            }
        });
    }

    void delete(Base model) throws OptimisticException {
        if(models.containsKey(model.getId())) {
            models.remove(model.getId());
        }
    }

}