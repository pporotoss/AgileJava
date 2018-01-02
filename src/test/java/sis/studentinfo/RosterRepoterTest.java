package sis.studentinfo;

import org.junit.Before;
import org.junit.Test;
import sis.report.RosterRepoter;
import sis.report.Session;

import java.io.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static sis.report.ReportConstant.NEWLINE;

public class RosterRepoterTest {

    private Session session;

    @Before
    public void setUp() {
        session = CourseSession.create(new Course("ENGL", "101"), DateUtil.createDate(2003, 1 , 6));
        session.enroll(new Student("A"));
        session.enroll(new Student("B"));
    }

    @Test
    public void rosterReportTest() throws IOException {
        Writer writer = new StringWriter();

        new RosterRepoter(session).writeReport(writer);

        assertReportContents(writer.toString());
    }

    private void assertReportContents(String rosterReport) {
        assertEquals(
            String.format(RosterRepoter.ROSTER_REPORT_HEADER +
                    "A%n" +
                    "B%n" +
                    RosterRepoter.ROSTER_REPORT_FOOTER
                    , session.getNumberOfStudents())
                ,rosterReport);

//        System.out.println(rosterReport);
    }

    @Test
    public void testFiledReport() throws IOException {
        final String fileName = "testFileReport.txt";

        delete(fileName);
        new RosterRepoter(session).writeReport(fileName);

        StringBuffer buffer = new StringBuffer();
        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            while ((line = reader.readLine()) != null) {
                buffer.append(String.format(line + "%n"));

            }
        }
        finally {
            delete(fileName);
        }
        String result = buffer.toString();
//        System.out.println(result);
        assertReportContents(result);
    }

    private void delete(String fileName) {
        File file = new File(fileName);
        if(file.exists()) {
            assertTrue("unable to delete "+fileName, file.delete());
        }
    }
}
