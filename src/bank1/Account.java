package bank1;

import java.util.Objects;
import java.util.Random;

public class Account {
    private String name;
    private long id;
    private String balance;

    public Account(String name, String balance) {
        this.name = name;
        this.id = new Random().nextLong();
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nBalance: %s\n", name, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(name, account.name) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, balance);
    }
}
