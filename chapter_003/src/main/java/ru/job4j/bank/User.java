package ru.job4j.bank;

public class User {
    private String name, passport;

    public  User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getPassport() {
        return passport;
    }

    public boolean eqauls(User user) {
        boolean answer = false;
        if (this == user || this.name.equals(user.name) && this.passport.equals(user.passport)) {
            answer = true;
        }
        return answer;
    }

    @Override
    public int hashCode() {
        return this.passport.hashCode();
    }
}
