package sis.studentinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student {
    final static Logger logger = Logger.getLogger(Student.class.getName());

    public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final String IN_STATE = "CO";
    static final int MAX_NAME_PARTS = 3;
    static final String TOO_MANY_NAME_PARTS_MSG = "Student name '%s' contains more than %d parts";
    
    private String name;
    private int credits;
    private String state;
    private List<Grade> grades = new ArrayList<>();
    private GradingStrategy gradingStrategy = new BasicGradingStrategy();
    private String firstName;
    private String middleName;
    private String lastName;
    private List<Integer> charges = new ArrayList<>();
    private String id;

    public Student(String fullName) {
        this.name = fullName;
        List<String> nameParts = split(fullName);
        if(nameParts.size() > MAX_NAME_PARTS) {
            String message = String.format(TOO_MANY_NAME_PARTS_MSG, fullName, MAX_NAME_PARTS);
            logger.info(message);
            throw new StudentNameFormatException(message);
        }
        setName(nameParts);
    }

    private void log(String message) {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info(message);
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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public enum Grade {
        A(4), B(3), C(2), D(1), F(0);
        
        private int points;
        
        private Grade(int points) {
            this.points = points;
        }
        
        public int getPoints() {
            return this.points;
        }
    }
    
    public enum Flag {
        ON_CAMPUS(1), TAX_EXEMPT(2), MINOR(4), TROUBLEMAKER(8);
        
        private int mask;
        
        private Flag(int mask) {
            this.mask = mask;
        }
    }
    
    private int settings = 0x0;
    
    public void set(Flag... flags) {
        for(Flag flag : flags) {
            settings |= flag.mask;
        }
    }
    
    public void unset(Flag... flags) {
        for (Flag flag : flags) {
            settings &= ~flag.mask;
        }
    }
    
    public boolean isOn(Flag flag) {
        return (settings & flag.mask) == flag.mask;
    }
    
    public boolean isOff(Flag flag) {
        return !isOn(flag);
    }

    public	static	Student	findByLastName(String	lastName)	{
        return	new	Student(lastName);
    }
}
