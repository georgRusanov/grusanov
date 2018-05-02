package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final List<User> storage = new ArrayList<>();

    synchronized boolean add(User user) {
        boolean result = false;
        int index = getIndex(user);
        if (-1 == index) {
            storage.add(user);
            result = true;
        }
        return result;
    }

    synchronized boolean update(User user) {
        boolean result = false;
        int index = getIndex(user);
        if (-1 < index) {
            storage.set(index, user);
            result = true;
        }
        return result;
    }

    synchronized boolean delete(User user) {
        return storage.remove(user);
    }

    synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User from = findById(fromId);
        User to = findById(toId);
        if (from != null && to != null && from.getAmount() > amount) {
            from.setAmount(from.getAmount() - amount);
            to.setAmount(to.getAmount() + amount);
            result = true;
        }
        return result;
    }

    private User findById(int id) {
        User result = null;
        for (User user : storage) {
            if (user.getId() == id) {
                result = user;
                break;
            }
        }
        return result;
    }

    private int getIndex(User user) {
        int result = -1;
        int id = user.getId();
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId() == id) {
                result = i;
                break;
            }
        }
        return result;
    }
}

class User {
    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
