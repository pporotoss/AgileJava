package sis.report;

import org.junit.Before;
import org.junit.Test;
import sis.studentinfo.Course;
import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;
import sis.studentinfo.StudentNameFormatException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static sis.studentinfo.DateUtil.createDate;

public abstract class SessionTest {
    
    private Session session;
    private Date startDate;
    public static final int CREDITS = 3;
    
    @Before
    public void setUp() {
        startDate = createDate(2003, 1, 6);
        session = createSession(new Course("ENGL", "101"), startDate);
        session.setNumberOfCredits(CREDITS);
    }
    
    protected abstract Session createSession(Course course, Date startDate);
    
    @Test
    public void testCreate() {
        assertEquals("ENGL", session.getDepartment());
        assertEquals("101", session.getNumber());
        assertEquals(0, session.getNumberOfStudents());
        assertEquals(startDate, session.getStartDate());
    }
    
    @Test
    public void testEnrollStudents() {
        Student student1 = new Student("Cain DiVoe");
        session.enroll(student1);
        assertEquals(CREDITS, student1.getCredits());
        assertEquals(1, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));
        
        
        Student student2 = new Student("Coralee DeVaughn");
        session.enroll(student2);
        assertEquals(CREDITS, student2.getCredits());
        assertEquals(2, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));
        assertEquals(student2, session.get(1));
    }
    
    @Test
    public void testComparable() {
        final Date date = new Date();
        Session sessionA = CourseSession.create(new Course("CMSC", "101"), date);
        Session sessionB = CourseSession.create(new Course("ENGL", "101"), date);
        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);
        
        Session sessionC = CourseSession.create(new Course("CMSC", "101"), date);
        assertEquals(0, sessionA.compareTo(sessionC));
        
        Session sessionD = CourseSession.create(new Course("CMSC", "210"), date);
        assertTrue(sessionC.compareTo(sessionD) < 0);
        assertTrue(sessionD.compareTo(sessionC) > 0);
    }
    
    @Test
    public void testSessionLength() {
        Session session = createSession(new Course("", ""), new Date());
        assertTrue(session.getSessionLength() > 0);
    }
    
    @Test
    public void testAverageGpaForPartTimeStudents() {
        session.enroll(createFullTimeStudent());

        Student partTimer1 = new Student("1");
        partTimer1.addGrade(Student.Grade.A);
        session.enroll(partTimer1);

        session.enroll(createFullTimeStudent());

        Student partTimer2 =new Student("2");
        partTimer2.addGrade(Student.Grade.B);
        session.enroll(partTimer2);

        assertEquals(3.5, session.averageGpaForPartTimeStudents(), 0.05);
    }

    private Student createFullTimeStudent() {
        Student student = new Student("a");
        student.addCredits(Student.CREDITS_REQUIRED_FOR_FULL_TIME);
        return student;
    }

    @Test
    public void testIterate() {
        enrollStudents(session);

        List<Student> results = new ArrayList<>();
        for(Student student : session) {
            results.add(student);
        }
    }

    private void enrollStudents(Session session) {
        session.enroll(new Student("1"));
        session.enroll(new Student("2"));
        session.enroll(new Student("3"));
    }

    @Test
    public void testCharges() {
        Student student = new Student("a");
        student.addCharge(500);
        student.addCharge(200);
        student.addCharge(399);
        assertEquals(1099, student.getTotalCharges());
    }

    @Test
    public void testSessionUrl() throws SessionException {
        final String url = "http://course.langrsoft.com/cmsc300";
        session.setUrl(url);
        assertEquals(url, session.getUrl().toString());
    }
    
    @Test
    public void testInvalidSessionUrl() {
        final String url = "httpsp://course.langrsoft.com/cmsc300";
        try {
            session.setUrl(url);
            fail("expected exection due to invalid protocol in URL");
        } catch (SessionException expectedException) {
            Throwable cause = expectedException.getCause();
            assertEquals(MalformedURLException.class, cause.getClass());
        }
    }
    
    @Test
    public void testBadlyFormattedName() {
        try {
            new Student("a b c d");
            fail("expected exception from 4-part name");
        }
        catch (StudentNameFormatException excpectedException) {
            assertEquals("Student name 'a b c d' contains more than 3 parts", excpectedException.getMessage());
        }
    }
}
