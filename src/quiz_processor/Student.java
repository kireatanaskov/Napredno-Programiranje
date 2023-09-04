package quiz_processor;

import java.util.Arrays;

public class Student {
    private String id;
    private String[] answers;
    private String[] correctAnswers;

    public Student(String id, String[] answers, String[] correctAnswers) throws QuizException {
        this.id = id;
        if (answers.length != correctAnswers.length)
            throw new QuizException();
        this.answers = Arrays.copyOf(answers, answers.length);
        this.correctAnswers = Arrays.copyOf(correctAnswers, correctAnswers.length);
    }

    public String getId() {
        return id;
    }

    public double points() {
        double total = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals(correctAnswers[i]))
                total += 1;
            else
                total -= 0.25;
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("%s -> %.2f", id, points());
    }
}
