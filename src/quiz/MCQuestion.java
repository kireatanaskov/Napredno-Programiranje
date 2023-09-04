package quiz;

public class MCQuestion extends Question{
    private String answer;

    public MCQuestion(String text, double points, String answer) {
        super(text, points);
        this.answer = answer;
    }

    @Override
    public String toString() {
        return String.format("Multiple Choice Question: %s Points %.0f Answer: %s", text, points, answer);
    }

    @Override
    public double getPoints() {
        return this.points;
    }

    @Override
    public double pointsAfterAnswering(String answer) {
        double percent = (points / 100) * 20;

        if (this.answer.equals(answer)) {
            return this.points;
        } else {
            return -percent;
        }
    }
}
