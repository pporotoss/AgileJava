package sis.report;

import org.junit.Test;
import sis.studentinfo.DateUtil;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SummerCourseSessionTest extends SessionTest {
    
    @Test
    public void testEndDate() {
        Date starDate = DateUtil.createDate(2003, 6, 9);
        Session session = createSession("ENGL", "200", starDate);
        Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeeksOut, session.getEndDate());
    }
    
    @Override
    protected Session createSession(String department, String number, Date startDate) {
        return SummerCourseSession.create(department, number, startDate);
    }
}
