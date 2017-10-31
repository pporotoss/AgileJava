package sis.studentinfo;

import org.junit.Test;
import sis.report.RosterRepoter;
import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;

import static org.junit.Assert.assertEquals;

public class RosterRepoterTest {

    @Test
    public void rosterReportTest() {
        CourseSession session = new CourseSession("ENGL", "101", new DateUtil().createDate(2003, 1 , 6));

        session.enroll(new Student("A"));
        session.enroll(new Student("B"));

        String rosterReport = new RosterRepoter(session).getReport();

        System.out.println(rosterReport);

        assertEquals(
            RosterRepoter.ROSTER_REPORT_HEADER +
            "A" + RosterRepoter.NEWLINE +
            "B" + RosterRepoter.NEWLINE +
            RosterRepoter.ROSTER_REPORT_FOOTER + "2" +
            RosterRepoter.NEWLINE, rosterReport);

    }
}
