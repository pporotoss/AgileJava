package sis.studentinfo;

public class BasicGradingStrategy implements GradingStrategy{
    
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        return grade.getPoints();
    }
    
}
