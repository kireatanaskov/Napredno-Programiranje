package student_records;

import java.util.ArrayList;
import java.util.List;

public class Record implements Comparable<Record>{
    private String code;
    private String type;
    private List<Integer> grades;
    float sum;

    public Record(String code, String type) {
        this.code = code;
        this.type = type;
        this.grades = new ArrayList<Integer>();
        this.sum = 0;
    }

    public void addGrade(int grade) {
        sum += grade;
        this.grades.add(grade);
    }

    @Override
    public int compareTo(Record o) {
        int avg = Float.compare(sum / grades.size(), o.sum / o.grades.size());
        if (avg == 0) {
            return code.compareTo(o.code);
        }
        return -avg;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", code, Math.round(sum * 100. / grades.size()) / 100.);
    }
}
