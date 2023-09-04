package oldest_person;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OldestPersonTest {
    public static List<Person> readData(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        return bufferedReader.lines()
                .map(l -> new Person(l))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\HP\\Desktop\\New folder\\finki\\III semestar\\Napredno\\src\\OldestPerson\\persons.txt");

        try {
            List<Person> persons = readData(new FileInputStream(file));

            if (persons.stream().max(Comparator.naturalOrder()).isPresent()) // proveruva dali ima nesto vo streamot, sprecuva null pointer exception
                System.out.println(persons.stream().max(Comparator.naturalOrder()).get());

            Collections.sort(persons);
            System.out.println(persons.get(persons.size() - 1));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
