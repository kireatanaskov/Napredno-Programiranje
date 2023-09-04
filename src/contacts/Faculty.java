package contacts;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Faculty {
    private String name;
    private Student[] students;

    public Faculty(String name, Student[] students) {
        this.name = name;
        this.students = Arrays.copyOf(students, students.length);
    }

    public int countStudentsFromCity(String cityName) {
        return (int) Arrays.stream(students)
                .filter(student -> student.getCity().equals(cityName))
                .count();
    }

    public Student getStudent(long index) {
        return Arrays.stream(students)
                .filter(student -> student.getIndex() == index)
                .findFirst()
                .orElse(null);
    }

    public double getAverageNumberOfContacts() {
        return Arrays.stream(students)
                .mapToInt(Student::getContactsSize)
                .average()
                .orElse(0.0);
    }

    public Student getStudentWithMostContacts() {
        return Arrays.stream(students)
                .sorted(Comparator.comparing(Student::getContactsSize).reversed()
                        .thenComparing(Comparator.comparing(Student::getIndex).reversed()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");
        joiner.add(Student.keyValue("fakultet", name));
        String studentsString = Arrays.stream(students)
                .map(Student::toString)
                .collect(Collectors.joining(", ", "[", "]"));
        joiner.add(Student.keyValueNoQuotes("studenti", studentsString));
        return joiner.toString();
    }
}
