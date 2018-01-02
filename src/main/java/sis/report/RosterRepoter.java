package sis.report;

import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static sis.report.ReportConstant.NEWLINE;

public class RosterRepoter {

    public static final String ROSTER_REPORT_HEADER = "Student%n-%n";
    public static final String ROSTER_REPORT_FOOTER = "%n# students = %d%n";

    private Session session;
    private Writer writer;

    public RosterRepoter(Session session) {
        this.session = session;
    }

    public void writeReport(Writer writer) throws IOException {
        this.writer = writer;
        writeHeader();
        writeBody();
        writeFooter();
    }

    private void writeHeader() throws IOException {
        writer.write(String.format(ROSTER_REPORT_HEADER));
    }

    private void writeBody() throws IOException {
        for (Student student : session.getAllStudents()) {
            writer.write(String.format(student.getName() + "%n"));
        }
    }

    private void writeFooter() throws IOException {
        writer.write(String.format(ROSTER_REPORT_FOOTER , session.getAllStudents().size()));
    }

    public void writeReport(String fileName) throws IOException {
        try(Writer bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            writeReport(bufferedWriter);
        }
    }
}
