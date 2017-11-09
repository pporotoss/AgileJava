package sis.studentinfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student {

    public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final String IN_STATE = "CO";
    
    private String name;
    private int credits;
    private String state;
    private List<Grade> grades = new ArrayList<>();
    private GradingStrategy gradingStrategy = new BasicGradingStrategy();
    private String firstName;
    private String middleName;
    private String lastName;
    private List<Integer> charges = new ArrayList<>();

    public Student(String fullName) {
        this.name = fullName;
        List<String> nameParts = split(fullName);
        setName(nameParts);
    }

    private List<String> split(String name) {
       return Stream.of(name.split(" ")).collect(Collectors.toList());
    }

    private void setName(List<String> nameParts) {
        this.lastName = removeLast(nameParts);
        String name = removeLast(nameParts);

        if(nameParts.isEmpty()) {
            this.firstName = name;
            this.middleName = "";
        }
        else {
            this.middleName = name;
            this.firstName = removeLast(nameParts);
        }
    }

    private String removeLast(List<String> nameParts) {
        if(nameParts.isEmpty()) return "";
        return nameParts.remove(nameParts.size() - 1);
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

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addCharge(int charge) {
        charges.add(charge);
    }

    public int getTotalCharges() {
        int total = 0;
        for(int charge : charges) {
            total += charge;
        }
        return total;
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
