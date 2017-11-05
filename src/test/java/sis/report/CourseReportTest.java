package sis.report;

import org.junit.Test;
import sis.studentinfo.CourseSession;

import java.util.Date;

import static com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text.NEW_LINE;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class CourseReportTest {

    @Test
    public void testReport() {
        final  Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(CourseSession.create("ENGL", "101", date));
        report.add(CourseSession.create("CZEC", "200", date));
        report.add(CourseSession.create("ITAL", "400", date));

        assertEquals(
                "CZEC 200" + NEW_LINE
                + "ENGL 101" + NEW_LINE
                + "ITAL 400" + NEW_LINE,
                report.text()
        );
    }

    @Test
    public void testComparable() {
        final Date date = new Date();
        CourseSession sessionA = CourseSession.create("CMSC", "101", date);
        CourseSession sessionB = CourseSession.create("ENGL", "101", date);
        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);

        CourseSession sessionC = CourseSession.create("CMSC", "101", date);
        assertEquals(0, sessionA.compareTo(sessionC));
    }
}
