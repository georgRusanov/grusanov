package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<V>{
    Object[] objects;
    int position = 0;

    public SimpleHashMap() {
        this.objects = new Object[10];
    }

    boolean insert(K key, V value){
        int keyHash = hash(key);
        if (keyHash > objects.length) {
            objects = Arrays.copyOf(objects, keyHash * 3 / 2);
        }
        objects[keyHash] = value;
        return true;
    }

    V get(K key) {
        V result = null;
        int keyHash = hash(key);
        if (keyHash < objects.length) {
            result = (V) objects[keyHash];
        }
        return result;
    }
    boolean delete(K key) {
        int keyHash = hash(key);
        boolean result = false;
        if (keyHash < objects.length) {
            objects[keyHash] = null;
            result = true;
        }
        return result;
    }

    int hash(K key) {
        return key.hashCode();
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            @Override
            public boolean hasNext() {
                boolean answer = false;
                for (int i = position; i < objects.length; i++) {
                    if (objects[i] != null) {
                        position = i;
                        answer = true;
                        break;
                    }
                }
                return answer;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Больше нет элементов");
                }
                return (V) objects[position++];
            }
        };
    }
}
