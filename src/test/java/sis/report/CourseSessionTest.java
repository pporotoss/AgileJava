package sis.report;

import org.junit.Before;
import org.junit.Test;
import sis.studentinfo.Course;
import sis.studentinfo.CourseSession;
import sis.studentinfo.DateUtil;
import sis.studentinfo.Student;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CourseSessionTest extends SessionTest {
    
    @Test
    public void testCourseDates() {
        Date startDate = DateUtil.createDate(2003, 1, 6);
        Session session = createSession(createCourse(), startDate);
        Date sixteenWeeksOut = new DateUtil().createDate(2003, 4, 25);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }

    @Test
    public void testCount() {
        CourseSession.resetCount();
        createSession(createCourse(), new Date());
        assertEquals(1, CourseSession.getCount());
        createSession(createCourse(), new Date());
        assertEquals(2, CourseSession.getCount());
    }
    
    private Course createCourse() {
        return new Course("ENGL", "101");
    }
    
    @Override
    protected Session createSession(Course course, Date startDate) {
        return CourseSession.create(course, startDate);
    }
}
