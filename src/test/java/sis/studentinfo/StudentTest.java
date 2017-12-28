package sis.studentinfo;

import org.junit.Test;

import java.util.logging.Handler;

import static org.junit.Assert.*;

public class StudentTest {
    private static final double GRADE_TOLERANCE = 0.05;
    
    @Test
    public void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        assertEquals(firstStudentName, firstStudent.getName());
        assertEquals("Jane", firstStudent.getFirstName());
        assertEquals("Doe", firstStudent.getLastName());
        assertEquals("", firstStudent.getMiddleName());

        
        final String secondStudentName = "Blow";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getName());
        assertEquals("", secondStudent.getFirstName());
        assertEquals("Blow", secondStudent.getLastName());
        assertEquals("", secondStudent.getMiddleName());

        final String thirdStudentName = "Raymond Douglas Davies";
        Student thirdStudent = new Student(thirdStudentName);
        assertEquals(thirdStudentName, thirdStudent.getName());
        assertEquals("Raymond", thirdStudent.getFirstName());
        assertEquals("Douglas", thirdStudent.getMiddleName());
        assertEquals("Davies", thirdStudent.getLastName());


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
        assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
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
    
    @Test
    public void testCalculateGpa() {
        Student student = new Student("a");
        assertGpa(student, 0);
        student.addGrade(Student.Grade.A);
        assertGpa(student, 4);
        student.addGrade(Student.Grade.B);
        assertGpa(student, 3.5);
        student.addGrade(Student.Grade.C);
        assertGpa(student, 3);
        student.addGrade(Student.Grade.D);
        assertGpa(student, 2.5);
        student.addGrade(Student.Grade.F);
        assertGpa(student, 2);
    }
    
    private void assertGpa(Student student, double expectedGpa) {
        assertEquals(expectedGpa, student.getGpa(), GRADE_TOLERANCE);
    }
    
    @Test
    public void testBadlyFormattedName() {
        Handler handler = new TestHandler();
        Student.logger.addHandler(handler);

        final String studentName = "a b c d";
        try {
            new Student(studentName);
            fail("expected exception from 4-part name");
        }
        catch (StudentNameFormatException expectedException) {
            String message = String.format(Student.TOO_MANY_NAME_PARTS_MSG, studentName, Student.MAX_NAME_PARTS);
            assertEquals(message, expectedException.getMessage());
            assertEquals(message, ((TestHandler)handler).getMessage());
            assertTrue(wasLogged(message, (TestHandler) handler));
        }
    }

    private boolean wasLogged(String message, TestHandler handler) {
        // 테스트 안까먹기 위해 false 반환
        return message.equals(handler.getMessage());
    }
}
