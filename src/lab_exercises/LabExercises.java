package lab_exercises;

import java.util.*;
import java.util.stream.Collectors;

public class LabExercises {
    private List<Student> students;

    public LabExercises() {
        this.students = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void printByAveragePoints(boolean ascending, int n) {
        Comparator<Student> studentComparator = Comparator.comparingDouble(Student::getAveragePoints)
                .thenComparing(Student::getIndex);

        if (!ascending)
            studentComparator = studentComparator.reversed();

        students.stream()
                .sorted(studentComparator)
                .limit(n)
                .forEach(System.out::println);
    }

    public List<Student> failedStudents() {
        return this.students.stream()
                .filter(student -> !student.hasSignature())
                .sorted(Comparator.comparing(Student::getIndex)
                        .thenComparing(Student::getAveragePoints))
                .collect(Collectors.toList());
    }

    private static int getYear(String index) {
        return 20 - Integer.parseInt(index.substring(0, 2));
    }

    public Map<Integer, Double> getStatisticsByYear() {
        Map<Integer, Double> averageMap = new TreeMap<Integer, Double>();
        Map<Integer, Integer> countingMap = new TreeMap<Integer, Integer>();

        this.students.stream()
                .filter(Student::hasSignature)
                .forEach(student -> {
                    int year = getYear(student.getIndex());
                    averageMap.putIfAbsent(year, 0.0);
                    countingMap.putIfAbsent(year, 0);

                    countingMap.computeIfPresent(year, (k, v) -> {
                        v++;
                        return v;
                    });

                    averageMap.computeIfPresent(year, (k, v) -> {
                        v += student.getAveragePoints();
                        return v;
                    });
                });

        for (Integer year : averageMap.keySet()) {
            averageMap.computeIfPresent(year, (k, v) -> {
                v /= countingMap.get(year);
                return v;
            });
        }

        return averageMap;
    }
}
