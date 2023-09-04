package quiz;

public class TFQuestion extends Question{
    String answer;

    public TFQuestion(String text, double points, String answer) {
        super(text, points);
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format("True/False Question: %s Points: %.0f Answer: %s", text, points, answer);
    }

    @Override
    public double getPoints() {
        return this.points;
    }

    @Override
    public double pointsAfterAnswering(String answer) {
        if (this.answer.equals(answer)) {
            return points;
        } else return 0;
    }
}
