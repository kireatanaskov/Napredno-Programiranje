package contacts;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Student {
    private Contact[] contacts;
    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private long index;

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        this.contacts = new Contact[0];
    }

    private void addContact(Contact contact) {
        this.contacts = Arrays.copyOf(this.contacts, this.contacts.length + 1);
        this.contacts[this.contacts.length - 1] = contact;
    }

    private Contact[] getFiltered(String type) {
        return Arrays.stream(contacts)
                .filter(contact -> contact.getType().equals(type))
                .toArray(Contact[]::new);
    }

    public void addEmailContact(String date, String email) {
        addContact(new EmailContact(date, email));
    }

    public void addPhoneContact(String date, String phone) {
        addContact(new PhoneContact(date, phone));
    }

    public Contact[] getEmailContacts() {
        return getFiltered("Email");
    }

    public Contact[] getPhoneContacts() {
        return getFiltered("Phone");
    }

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public long getIndex() {
        return index;
    }

    public Contact getLatestContact() {
        return Arrays.stream(contacts)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");
        joiner.add(keyValue("ime", firstName));
        joiner.add(keyValue("prezime", lastName));
        joiner.add(keyValueNoQuotes("vozrast", String.valueOf(age)));
        joiner.add(keyValue("grad", city));
        joiner.add(keyValueNoQuotes("indeks", String.valueOf(index)));
        String contactsString = Arrays.stream(getPhoneContacts())
                .map(Contact::quoted)
                .collect(Collectors.joining(", ", "[", "]"));
        joiner.add(keyValueNoQuotes("telefonskiKontakti", contactsString));
        contactsString = Arrays.stream(getEmailContacts())
                .map(Contact::quoted)
                .collect(Collectors.joining(", ", "[", "]"));
        joiner.add(keyValueNoQuotes("emailKontakti", contactsString));
        return joiner.toString();
    }

    static String keyValue(String key, String value) {
        return String.format("\"%s\":\"%s\"", key, value);
    }

    static String keyValueNoQuotes(String key, String value) {
        return String.format("\"%s\":%s", key, value);
    }

    public int getContactsSize() {
        return contacts.length;
    }
}
