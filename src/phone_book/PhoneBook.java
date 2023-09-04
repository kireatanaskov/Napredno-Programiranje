package phone_book;

import java.util.*;

public class PhoneBook {
    private Set<String> allPhoneNumbers;
    private Map<String, Set<Contact>> contactsBySubstring;
    private Map<String, Set<Contact>> contactsByName;

    public PhoneBook() {
        allPhoneNumbers = new HashSet<>();
        contactsBySubstring = new HashMap<String, Set<Contact>>();
        contactsByName = new HashMap<String, Set<Contact>>();
    }

    private List<String> getSubstring (String phone) {
        List<String> result = new ArrayList<String>();
        for (int len = 3; len <= phone.length(); len++) {
            for (int i = 0; i <= phone.length() - len; i++) {
                result.add(phone.substring(i, i+len));
            }
        }

        return result;
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        if (allPhoneNumbers.contains(number)) {
            throw new DuplicateNumberException(number);
        } else {
            allPhoneNumbers.add(number);
            Contact c = new Contact(name, number);
            List<String> subnumbers = getSubstring(number);
            for (String subnumber : subnumbers) {
                contactsBySubstring.putIfAbsent(subnumber, new TreeSet<Contact>());
                contactsBySubstring.get(subnumber).add(c);
            }

            contactsByName.putIfAbsent(name, new TreeSet<Contact>());
            contactsByName.get(name).add(c);
        }
    }

    public void contactsByNumber(String number) {
        Set<Contact> contacts = contactsBySubstring.get(number);
        if (contacts == null) {
            System.out.println("NOT FOUND");
            return;
        }
        contacts.forEach(System.out::println);
    }

    public void contactsByName(String name) {
        Set<Contact> contacts = contactsByName.get(name);
        if (contacts == null) {
            System.out.println("NOT FOUND");
            return;
        }
        contacts.forEach(System.out::println);
    }
}
