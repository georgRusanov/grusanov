package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsBase {
    private Map<User, List<Account>> base = new HashMap<>();

    public void addUser(User user) {
        base.putIfAbsent(user, new ArrayList<Account>());
    }

    public void deleteUser(User user) {
        if (!base.containsKey(user)) {
            System.out.println("Нет такого пользователя");
        } else {
            base.remove(user);
        }
    }

    public void addAccountToUser(String passport, Account account) {
        base.get(getUser(passport)).add(account);
    }

    public void deleteAccountFromUser(String passport, Account account) {
        base.get(getUser(passport)).remove(account);
    }

    public List<Account> getUserAccounts(String passport) {
        return base.get(getUser(passport));
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String destRequisite, double amount) {
        boolean result = false;
        User src = getUser(srcPassport);
        User dest = getUser(destPassport);
        Account srcAcc = null;
        Account destAcc = null;
        for (Account acc : base.get(src)) {
            if (acc.getRequisites().equals(srcRequisite)) {
                srcAcc = acc;
                result = true;
                break;
            }
        }
        if (result) {
            result = false;
            for (Account acc : base.get(dest)) {
                if (acc.getRequisites().equals(destRequisite)) {
                    destAcc = acc;
                    result = true;
                    break;
                }
            }
        }
        if (result) {
            if (srcAcc.getValue() > amount) {
                srcAcc.setValue(srcAcc.getValue() - amount);
                destAcc.setValue(destAcc.getValue() + amount);
            }
        } else {
            result = false;
        }
        return result;
    }

    public User getUser(String passport) {
        User result = null;
        for (User user : base.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
                break;
            }
        }
        return result;
    }
}

