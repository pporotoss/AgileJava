package sis.report;

import sis.studentinfo.Course;
import sis.studentinfo.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public abstract class Session implements Comparable<Session>, Iterable<Student>, Serializable{
    public static final long serialVersionUID = 1L;
    private static int count;
    private Course course;
    private transient List<Student> students = new ArrayList<>();
    private Date startDate;
    private int numberOfCredits;
    private URL url;
    private String name;

    protected Session (Course course, Date startDate) {
        this.course = course;
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
        return course.getDepartment();
    }
    
    public String getNumber() {
        return course.getNumber();
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

    public void setUrl(String url) throws SessionException {
        try {
            this.url = new URL(url);
        }
        catch (MalformedURLException e) {
            log(e);
            throw new SessionException(e);
        }
        
    }
    
    private void log(Exception e) {
    
    }
    
    public URL getUrl() {
        return url;
    }

    public int getNumberOfCredits() {
        return this.numberOfCredits;
    }

    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        output.writeInt(students.size());
        for(Student student : students) {
            output.writeObject(student.getLastName());
        }
    }

    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        students = new ArrayList<>();
        int size = input.readInt();
        for(int i = 0; i < size; i++) {
            String lastName = (String) input.readObject();
            students.add(Student.findByLastName(lastName));
        }
    }
}
