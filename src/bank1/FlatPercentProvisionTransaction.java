package bank1;

public class FlatPercentProvisionTransaction extends Transaction{
    private int percent;

    public FlatPercentProvisionTransaction(long fromId, long toId, String amount, int percent) {
        super(fromId, toId, amount, "FlatPercent");
        this.percent = percent;
    }


    @Override
    public String getProvision() {
        long amount = Bank.toNumber(this.getAmount());
        amount = amount / 100;
        long provision = percent * amount;
        return Bank.toString(provision);
    }

    public int getPercent() {
        return percent;
    }
}
