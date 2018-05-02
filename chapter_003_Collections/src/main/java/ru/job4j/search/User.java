package ru.job4j.search;

public class User {
    private Integer id;
    private String name, city;
    public User(Integer id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }
}
