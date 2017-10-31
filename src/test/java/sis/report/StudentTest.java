package sis.report;

import org.junit.Test;
import sis.studentinfo.Student;

import static org.junit.Assert.assertEquals;

public class StudentTest {
    
    @Test
    public void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        assertEquals(firstStudentName, firstStudent.getName());
        
        final String secondStudentName = "Joe Blow";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getName());
    }
}
