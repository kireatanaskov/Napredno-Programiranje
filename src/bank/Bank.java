package bank;

import java.util.Arrays;

public class Bank {
    private Account[] accounts;
    private int totalAccounts;
    private int maxAccounts;

    public Bank(int maxAccounts) {
        this.maxAccounts = maxAccounts;
        this.accounts = new Account[maxAccounts];
        this.totalAccounts = 0;
    }

    public void addAccount(Account account) {
        if (totalAccounts == maxAccounts) {
            accounts = Arrays.copyOf(accounts, maxAccounts * 2);
            maxAccounts *= 2;
        }
        accounts[totalAccounts++] = account;
    }

    public double totalAssets() {
        double total = 0;
        for (Account acc : accounts) {
            total += acc.getCurrentAmount();
        }
        return total;
    }

    public void addInterestToAllAccounts() {
        for (Account acc : accounts) {
            if (acc.getAccountType().equals(AccountType.INTEREST)) {
                InterestBearingAccount interestBearingAccount = (InterestBearingAccount) acc;
                interestBearingAccount.addInterest();
            }
        }
    }
}
