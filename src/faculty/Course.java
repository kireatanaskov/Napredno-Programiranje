package faculty;

import java.util.IntSummaryStatistics;

public class Course {
    private String courseName;
    private IntSummaryStatistics statistics;

    public Course(String courseName) {
        this.courseName = courseName;
        this.statistics = new IntSummaryStatistics();
    }

    public void addGrade(int grade) {
        statistics.accept(grade);
    }

    @Override
    public String toString() {
        return String.format("%s %d %.2f", courseName, statistics.getCount(), statistics.getAverage());
    }

    public int getStudentsCount() {
        return (int) statistics.getCount();
    }

    public double getCourseAverageGrade() {
        return statistics.getAverage();
    }

    public String getCourseName() {
        return courseName;
    }
}
