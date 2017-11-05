package sis.report;

import org.junit.Test;
import sis.studentinfo.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testFullTime() {
        Student student = new Student("a");
        assertFalse(student.isFullTime());
    }

    @Test
    public void testCredits() {
        Student student = new Student("a");
        assertEquals(0, student.getCredits());
        student.addCredits(3);
        assertEquals(3, student.getCredits());
        student.addCredits(4);
        assertEquals(7, student.getCredits());
    }

    @Test
    public void testStudentStatus() {
        Student student = new Student("a");
        assertEquals(0, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(3);
        assertEquals(3, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(4);
        assertEquals(7, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(5);
        assertEquals(student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
        assertTrue(student.isFullTime());
    }

    @Test
    public void testInState() {
        Student student = new Student("a");
        assertFalse(student.isInstate());

        student.setState(Student.IN_STATE);
        assertTrue(student.isInstate());

        student.setState("MD");
        assertFalse(student.isInstate());
    }
}