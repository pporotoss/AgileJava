package sis.studentinfo;

public class RegularGradingStrategy implements GradingStrategy {
    
    @Override
    public int getGradePointsFor(Student.Grade grade) {
       return basicGradePointsFor(grade);
    }
}
