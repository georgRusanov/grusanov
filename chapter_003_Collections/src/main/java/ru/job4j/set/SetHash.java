package ru.job4j.set;

public class SetHash<E> {
    Object[] objects;

    public SetHash() {
        this.objects = new Object[10];
    }

    boolean add(E e) {
        boolean result = false;
        int key = hash(e);
        if (!contains(e)) {
            if (key > objects.length) {
                System.arraycopy(objects, 0, objects, 0, key * 3 / 2);
            }
            objects[key] = e;
            result = true;
        }
        return result;
    }

    boolean contains(E e) {
        boolean result = false;
        int key = hash(e);
        if (key < objects.length && objects[key] != null) {
            result = true;
        }
        return result;
    }

    boolean remove(E e) {
        boolean result = false;
        int key = hash(e);
        if (contains(e)) {
            System.arraycopy(objects, key + 1, objects, key, objects.length - key - 1);
            result = true;
        }
        return result;
    }

    int hash(E e) {
        return e.hashCode();
    }
}
