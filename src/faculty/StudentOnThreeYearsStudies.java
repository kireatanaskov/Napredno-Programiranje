package faculty;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class StudentOnThreeYearsStudies extends Student{
    public StudentOnThreeYearsStudies(String id) {
        super(id);
        IntStream.range(1, 7).forEach(i -> gradesByTerm.putIfAbsent(i, new ArrayList<Integer>()));
    }

    @Override
    boolean addGrade(int term, String courseName, int grade) throws OperationNotAllowedException {
        validate(term);
        gradesByTerm.get(term).add(grade);
        courses.add(courseName);
        return countOfCoursesPassed() == 18;
    }

    @Override
    public String getGraduationLog() {
        return super.getGraduationLog() + " in 3 years";
    }
}
