package ru.job4j.nonblocking;

class Base {
    private int version = 0;
    private Object value = new Object();
    private int id = -1;
    private int counter = 1;


    public void setId(int id) {
        if (counter == 1) {
            this.id = id;
            counter--;
        } else {
            throw new IdException("id already exists");
        }

    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void incrementVersion() {
        version++;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}