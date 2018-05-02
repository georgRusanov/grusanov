package ru.job4j.sort;

import java.util.*;

public class SortUser {

    public Set<User> sort(List<User> list) {
        Set<User> users = new TreeSet<>();
        for (User user : list) {
            users.add(user);
        }
        return users;
    }

    public List<User> sortNameLength(List<User> list) {
        Collections.sort(list, new SortNameLength());
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, new SortByAllFields());
        return list;
    }
}

class SortNameLength implements Comparator<User> {

    @Override
    public int compare(User obj1, User obj2) {
        return Integer.compare(obj1.getName().length(), obj2.getName().length());
    }
}

class SortByAllFields implements Comparator<User> {

    @Override
    public int compare(User obj1, User obj2) {
        int nameEquals = obj1.getName().compareTo(obj2.getName());
        return nameEquals != 0 ? nameEquals : obj1.compareTo(obj2);
    }
}