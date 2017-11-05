package sis.studentinfo;

public class HonorGradingStrategy extends BasicGradingStrategy{
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = super.getGradePointsFor(grade);
        if(points == 0) return 0;
        return points += 1;
    }
}
