package ru.job4j.search;

import java.util.HashMap;
import java.util.List;

public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> users = new HashMap<>();

        for (User user : list) {
            users.put(user.getId(), user);
        }
        return users;
    }
}
