package bank;

public abstract class Account {

    private String accountOwner;
    private int id;
    private static int idSeed = 1;
    private double currentAmount;
    private AccountType accountType;

    public Account(String accountOwner, double currentAmount) {
        this.accountOwner = accountOwner;
        this.currentAmount = currentAmount;
        this.id = idSeed++;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void addAmount(double amount) {
        this.currentAmount += amount;
    }

    public void removeAmount(double amount) throws CanNotWithdrawMoneyException {
        if (currentAmount < amount)
            throw new CanNotWithdrawMoneyException(currentAmount, amount);
        currentAmount -= amount;
    }

    public abstract AccountType getAccountType();

    @Override
    public String toString() {
        return String.format("%d: %.2f", id, currentAmount);
    }
}
