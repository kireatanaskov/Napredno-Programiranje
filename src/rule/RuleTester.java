package rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RuleTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        if (testCase == 1) { //Test for String,Integer
            List<Rule<String, Integer>> rules = new ArrayList<>();

            //TODO: Add a rule where if the string contains the string "NP", the result would be index of the first occurrence of the string "NP"
            rules.add(new Rule<>(
                    str -> str.contains("NP"),
                    str -> str.indexOf("NP")
            ));

            // TODO: Add a rule where if the string starts with the string "NP", the result would be length of the string
            rules.add(new Rule<>(
                   str -> str.startsWith("NP"),
                   String::length
            ));

            List<String> inputs = new ArrayList<>();
            while (sc.hasNext()) {
                inputs.add(sc.nextLine());
            }

            RuleProcessor.process(inputs, rules);

        } else { //Test for Student, Double
            List<Rule<Student, Double>> rules = new ArrayList<>();

            //TODO Add a rule where if the student has at least 3 grades, the result would be the max grade of the student
            rules.add(new Rule<>(
                    std -> std.grades.size() >= 3,
                    std -> (double) std.grades.stream().mapToInt(grade -> grade).max().getAsInt()
            ));

            //TODO Add a rule where if the student has an ID that starts with 20, the result would be the average grade of the student
            //If the student doesn't have any grades, the average is 5.0
            rules.add(new Rule<>(
                    student -> student.id.startsWith("20"),
                    student -> student.grades.stream().mapToInt(grade -> grade).average().orElse(5.0)
            ));

            List<Student> students = new ArrayList<>();
            while (sc.hasNext()){
                students.add(Student.create(sc.nextLine()));
            }

            RuleProcessor.process(students, rules);
        }
    }
}
