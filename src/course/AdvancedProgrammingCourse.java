package course;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdvancedProgrammingCourse {
    private final Map<String, Student> students;

    public AdvancedProgrammingCourse() {
        this.students = new HashMap<String, Student>();
    }

    public void addStudent(Student s) {
        this.students.putIfAbsent(s.getId(), s);
    }

    public void updateStudent(String idNumber, String activity, int points){
        try {
            this.students.get(idNumber).updateStudent(activity, points);
        } catch (Exception e) {
            // DO NOTHING, just continue
        }
    }

    public List<Student> getFirstNStudents(int n) {
        return this.students.values()
                .stream()
                .sorted(Comparator.comparing(Student::getTotalPoints).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public Map<Integer, Integer> getGradeDistribution() {
        Map<Integer, Integer> result = students.values().stream()
                .map(Student::grade)
                .collect(Collectors.groupingBy(
                        grade -> grade,
                        TreeMap::new,
                        Collectors.summingInt(i -> 1)
                ));

        IntStream.range(5, 11).forEach(i -> result.putIfAbsent(i, 0));
        return result;
    }

    public void printStatistics() {
        DoubleSummaryStatistics dss = students.values().stream()
                .filter(student -> student.getTotalPoints() >= 50)
                .mapToDouble(Student::getTotalPoints)
                .summaryStatistics();

        System.out.printf("Count: %d Min: %.2f Average: %.2f Max: %.2f",
                dss.getCount(),
                dss.getMin(),
                dss.getAverage(),
                dss.getMax());
    }
}
