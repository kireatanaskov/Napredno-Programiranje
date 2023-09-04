package quiz_processor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class QuizProcessor {
    public static Map<String, Double> processAnswers(InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        return bufferedReader.lines()
                .map(line -> {
                    String[] parts = line.split(";");
                    String id = parts[0];
                    String[] studentAnswers = parts[1].split(", ");
                    String[] correctAnswers = parts[2].split(", ");
                    try {
                        return new Student(id, studentAnswers, correctAnswers);
                    } catch (QuizException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Student::getId,
                        TreeMap::new,
                        Collectors.summingDouble(Student::points)
                ));
    }
}
