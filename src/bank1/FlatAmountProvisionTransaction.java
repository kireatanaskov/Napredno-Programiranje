package bank1;

public class FlatAmountProvisionTransaction extends Transaction{
    private String flatAmount;
    public FlatAmountProvisionTransaction(long fromId, long toId, String amount, String flatAmount) {
        super(fromId, toId, amount, "FlatAmount");
        this.flatAmount = flatAmount;
    }

    @Override
    public String getProvision() {
        return flatAmount;
    }

    public String getFlatAmount() {
        return flatAmount;
    }
}
