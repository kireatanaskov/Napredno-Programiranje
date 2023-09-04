package phone_book;

public class Contact implements Comparable<Contact> {
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public int compareTo(Contact o) {
        int res = this.name.compareTo(o.name);
        if (res == 0) {
            return this.phone.compareTo(o.phone);
        } else {
            return res;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, phone);
    }
}
