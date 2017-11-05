package sis.studentinfo;

public class HonorGradingStrategy implements GradingStrategy{
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = basicGradePointsFor(grade);
        if(points == 0) return 0;
        return points += 1;
    }
}
