package sis.report;

import sis.studentinfo.Student;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public abstract class Session implements Comparable<Session>, Iterable<Student>{
    private static int count;
    private String department;
    private String number;
    private List<Student> students = new ArrayList<>();
    private Date startDate;
    private int numberOfCredits;
    private URL url;

    protected Session (String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getStartDate());
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        final int numberOfDays = getSessionLength() * daysInWeek - daysFromFridayToMonday;  // weeks * days per week - 3 days
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    protected abstract int getSessionLength();
    
    public List<Student> getAllStudents() {
        return students;
    }
    
    public static void resetCount() {
        count = 0;
    }
    
    public static int getCount() {
        return count;
    }
    
    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
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
        student.addCredits(numberOfCredits);
        students.add(student);
    }
    
    public Student get(int index) {
        return students.get(index);
    }
    
    @Override
    public int compareTo(Session session) {
        int compare = this.getDepartment().compareTo(session.getDepartment());
        if(compare == 0) {
            compare = this.getNumber().compareTo(session.getNumber());
        }
        return compare;
    }

    public double averageGpaForPartTimeStudents() {
        double total = 0;
        int count = 0;

        for (Student student : students) {
            if(student.isFullTime()) {
                continue;
            }
            total += student.getGpa();
            count++;
        }
        if(count == 0) return 0;
        return total / count;
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }

    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public URL getUrl() {
        return url;
    }
}
