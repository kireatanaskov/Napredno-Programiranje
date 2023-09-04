package quiz;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Quiz {
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<Question>();
    }

    public void addQuestion(String questionData) throws InvalidOperationException {
        this.questions.add(Question.createQuestion(questionData));
    }

    public void printQuiz(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        this.questions.stream()
                .sorted(Comparator.comparing(Question::getPoints).reversed())
                .forEach(pw::println);

        pw.flush();
    }

    public void answerQuiz(List<String> answers, OutputStream os) throws InvalidOperationException {
        if (answers.size() != questions.size())
            throw new InvalidOperationException("Answers and questions must be of same length!");

        PrintWriter pw = new PrintWriter(os);

        double total = 0;

        for (int i = 0; i < questions.size(); i++) {
            pw.printf("%d. %.2f\n", i + 1, questions.get(i).pointsAfterAnswering(answers.get(i)));
            total += questions.get(i).pointsAfterAnswering(answers.get(i));
        }

        pw.printf("Total points: %.2f", total);

        pw.flush();
    }
}
