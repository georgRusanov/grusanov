package ru.job4j.bank;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountsBaseTest {

    @Test
    public void createUser() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        assertThat(true, is(bank.getBase().containsKey(user)));
    }

    @Test
    public void deleteUserThanFalse() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        bank.deleteUser(user);
        assertThat(false, is(bank.getBase().containsKey(user)));
    }

    @Test
    public void whenAddAccountThanTrue() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        Account account = new Account(100.5, "account");
        bank.addAccountToUser("passport", account);
        assertThat(true, is(bank.getBase().get(user).contains(account)));
    }

    @Test
    public void whenAddExistUserThanTrue() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        Account account = new Account(100.5, "account");
        bank.addAccountToUser("passport", account);
        bank.addUser(user);
        assertThat(true, is(bank.getBase().get(user).contains(account)));
    }

    @Test
    public void whenDeleteAccountThanFalse() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        Account account = new Account(100.5, "account");
        bank.addAccountToUser("passport", account);
        bank.deleteAccountFromUser("passport", account);
        assertThat(false, is(bank.getBase().get(user).contains(account)));
    }

    @Test
    public void receiveAllUserAccounts() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        Account account = new Account(100.5, "account");
        bank.addAccountToUser("passport", account);
        Account account1 = new Account(100.3, "account1");
        bank.addAccountToUser("passport", account1);
        List<Account> result = bank.getUserAccounts("passport");
        List<Account> expected = Arrays.asList(account, account1);
        assertThat(expected, is(result));
    }

    @Test
    public void whenSourceAccountDontExistThanFalse() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        Account account = new Account(100.5, "account");
        bank.addAccountToUser("passport", account);
        assertThat(false,
                is(bank.transferMoney("passport", "account1", "passport", "account", 50.0)));
    }

    @Test
    public void whenDestAccountDontExistThanFalse() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        Account account = new Account(100.5, "account");
        bank.addAccountToUser("passport", account);
        assertThat(false,
                is(bank.transferMoney("passport", "account", "passport", "account1", 50.0)));
    }

    @Test
    public void whenNotEnougthMoneyThanFalse() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        Account account = new Account(100.5, "account");
        bank.addAccountToUser("passport", account);
        Account account1 = new Account(30.0, "account1");
        bank.addAccountToUser("passport", account1);
        assertThat(false,
                is(bank.transferMoney("passport", "account", "passport", "account1", 150.0)));
    }

    @Test
    public void whenEnougthMoneyThanTrue() {
        AccountsBase bank = new AccountsBase();
        User user = new User("name", "passport");
        bank.addUser(user);
        Account account = new Account(100.5, "account");
        bank.addAccountToUser("passport", account);
        Account account1 = new Account(30.0, "account1");
        bank.addAccountToUser("passport", account1);
        bank.transferMoney("passport", "account", "passport", "account1", 50.0);
        List<Account> result = bank.getUserAccounts("passport");
        assertThat(true,
                is(result.get(result.indexOf(account)).getValue() == 50.5
                        && result.get(result.indexOf(account1)).getValue() == 80));
    }

}