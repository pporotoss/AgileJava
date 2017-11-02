package sis.studentinfo;

public class Student {

    public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final String IN_STATE = "CO";

    private String name;
    private int credits;
    private String state;

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
}
