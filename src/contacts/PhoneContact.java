package contacts;

public class PhoneContact extends Contact{
    private String phone;

    public PhoneContact(String date, String phone) {
        super(date);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String getType() {
        return "Phone";
    }

    public Operator getOperator() {
        String prefix = phone.substring(0,3);
        switch (prefix) {
            case "070":
            case "071":
            case "072":
                return Operator.TMOBILE;
            case "075":
            case "076":
                return Operator.ONE;
            default:
                return Operator.VIP;
        }
    }

    @Override
    public String quoted() {
        return String.format("\"%s\"", phone);
    }
}
