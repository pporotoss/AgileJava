package sis.report;

import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;

import static sis.report.ReportConstant.NEWLINE;

public class RosterRepoter {

    public static final String ROSTER_REPORT_HEADER = "sis.studentinfo.Student"+ NEWLINE + "-" + NEWLINE;
    public static final String ROSTER_REPORT_FOOTER = NEWLINE + "# students = ";

    private Session session;

    public RosterRepoter(Session session) {
        this.session = session;
    }


    public String getReport() {
        StringBuilder buffer = new StringBuilder();

        writeHeader(buffer, ROSTER_REPORT_HEADER);
        writeBody(buffer);
        writeFooter(buffer);

        return buffer.toString();
    }

    private void writeFooter(StringBuilder buffer) {
        buffer.append(ROSTER_REPORT_FOOTER + session.getAllStudents().size() + NEWLINE);
    }

    private void writeBody(StringBuilder buffer) {
        for(Student student : session.getAllStudents()) {
            buffer.append(student.getName());
            buffer.append(NEWLINE);
        }
    }

    private void writeHeader(StringBuilder buffer, String rosterReportHeader) {
        buffer.append(rosterReportHeader);
    }


}
