package quiz;

import java.util.ArrayList;
import java.util.List;

public abstract class Question {
    String text;
    double points;

    public Question(String text, double points) {
        this.text = text;
        this.points = points;
    }

    public static Question createQuestion(String questionData) throws InvalidOperationException {
        String[] parts = questionData.split(";");
        String type = parts[0];
        String text = parts[1];
        double points = Double.parseDouble(parts[2]);

        List<String> answers = new ArrayList<String>();
        answers.add("A");
        answers.add("B");
        answers.add("C");
        answers.add("D");
        answers.add("E");

        if (type.equals("TF")) {
            String answer = parts[3];
            return new TFQuestion(text, points, answer);
        } else {
            String answer = parts[3];
            if (!answers.contains(answer))
                throw new InvalidOperationException(answer + " is not allowed option for this question");
            return new MCQuestion(text, points, answer);
        }
    }

    public abstract double getPoints();

    public abstract double pointsAfterAnswering(String answer);
}
