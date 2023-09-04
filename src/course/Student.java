package course;

public class Student {
    private String id;
    private String name;
    private int pointsMidterm1;
    private int pointsMidterm2;
    private int pointsLaboratory;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.pointsMidterm1 = 0;
        this.pointsMidterm2 = 0;
        this.pointsLaboratory = 0;
    }

    public String getId() {
        return id;
    }

    public void updateStudent(String activity, int points) throws Exception {
        switch (activity) {
            case "midterm1":
                if (points > 100)
                    throw new Exception();
                this.pointsMidterm1 = points;
                break;
            case "midterm2":
                if (points > 100)
                    throw new Exception();
                this.pointsMidterm2 = points;
                break;
            default:
                if (points > 10)
                    throw new Exception();
                this.pointsLaboratory = points;
                break;
        }
    }

    public double getTotalPoints() {
        return pointsMidterm1 * 0.45 + pointsMidterm2 * 0.45 + pointsLaboratory;
    }

    public int grade() {
        int g = (int) getTotalPoints() / 10 + 1;
        if (g > 10)
            g = 10;
        if (g < 5)
            g = 5;
        return g;
    }

    @Override
    public String toString() {
        return String.format("ID: %s Name: %s First midterm: %d Second midterm %d Labs: %d Summary points: %.2f Grade: %d",
                this.id,
                this.name,
                this.pointsMidterm1,
                this.pointsMidterm2,
                this.pointsLaboratory,
                this.getTotalPoints(),
                this.grade());
    }
}
