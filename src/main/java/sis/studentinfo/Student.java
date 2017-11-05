package sis.studentinfo;

import java.util.ArrayList;
import java.util.List;

public class Student {

    public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final String IN_STATE = "CO";
    
    private String name;
    private int credits;
    private String state;
    private List<Grade> grades = new ArrayList<>();
    private GradingStrategy gradingStrategy = new BasicGradingStrategy();

    public Student(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    public int getCredits() {
        return credits;
    }

    public void addCredits(int credits) {
        this.credits += credits;
    }

    public boolean isInstate() {
        return IN_STATE.equals(state);
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public double getGpa() {
        double total = 0;
        if(grades.isEmpty()) return total;
        
        for(Grade grade : grades) {
            total += gradingStrategy.getGradePointsFor(grade);
        }
        
        return total / grades.size();
    }
    
    public void addGrade(Grade grade) {
        grades.add(grade);
    }
    
    public void setGradingStrategy(GradingStrategy gradingStrategy) {
        this.gradingStrategy = gradingStrategy;
    }
    
    public enum Grade{
        A(4), B(3), C(2), D(1), F(0);
        
        private int points;
        
        private Grade(int points) {
            this.points = points;
        }
        
        public int getPoints() {
            return this.points;
        }
    }
}
