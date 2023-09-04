package lab_exercises;

import java.util.List;

public class Student {
    private String index;
    private List<Integer> points;

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }

    public double getAveragePoints() {
        int totalPoints = 0;
        for (Integer point : points) {
            totalPoints += point;
        }

        return totalPoints / 10.0;
    }

    public boolean hasSignature() {
        return this.points.size() >= 8;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f",
                this.index,
                this.hasSignature() ? "YES" : "NO",
                this.getAveragePoints());
    }
}
