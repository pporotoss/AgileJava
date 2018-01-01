package sis.studentinfo;

import org.junit.Test;
import sis.report.RosterRepoter;
import sis.report.Session;
import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;

import static org.junit.Assert.assertEquals;
import static sis.report.ReportConstant.NEWLINE;

public class RosterRepoterTest {

    @Test
    public void rosterReportTest() {
        Session session = CourseSession.create(new Course("ENGL", "101"), DateUtil.createDate(2003, 1 , 6));

        session.enroll(new Student("A"));
        session.enroll(new Student("B"));

        String rosterReport = new RosterRepoter(session).getReport();

        System.out.println(rosterReport);

        assertEquals(
            RosterRepoter.ROSTER_REPORT_HEADER +
            "A" + NEWLINE +
            "B" + NEWLINE +
            RosterRepoter.ROSTER_REPORT_FOOTER + "2" +
            NEWLINE, rosterReport);

    }
}
