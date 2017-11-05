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
        report.add(CourseSession.create("CZEC", "220", date));
        report.add(CourseSession.create("ITAL", "330", date));

        assertEquals(
                "CZEC 200" + NEW_LINE
                + "CZEC 220" + NEW_LINE
                + "ENGL 101" + NEW_LINE
                + "ITAL 330" + NEW_LINE
                + "ITAL 400" + NEW_LINE,
                report.text()
        );
    }

}
