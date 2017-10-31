package sis.studentinfo;

import java.util.*;

public class CourseSession {

    public static final String NEWLINE = System.getProperty("line.seperator");
    public static final String ROSTER_REPORT_HEADER = "sis.studentinfo.Student"+ NEWLINE + "-" + NEWLINE;
    public static final String ROSTER_REPORT_FOOTER = NEWLINE + "# students = ";

    private String department;
    private String number;
    private List<Student> students = new ArrayList<>();
    private Date startDate;


    public CourseSession(String department, String number) {
        this.department = department;
        this.number = number;
    }
    
    public CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public String getNumber() {
        return number;
    }
    
    public int getNumberOfStudents() {
        return students.size();
    }
    
    public void enroll(Student student) {
        students.add(student);
    }
    
    public Student get(int index) {
        return students.get(index);
    }
    
    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        final int numberOfDays = 16 * 7 - 3;  // weeks * days per week - 3 days
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }
    
    public Date getStartDate() {
        return startDate;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}
