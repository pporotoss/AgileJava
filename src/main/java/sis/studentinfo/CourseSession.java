package sis.studentinfo;

import sis.report.Session;

import java.util.*;

public class CourseSession extends Session {

//    public static final String NEWLINE = System.getProperty("line.seperator");
//    public static final String ROSTER_REPORT_HEADER = "sis.studentinfo.Student"+ NEWLINE + "-" + NEWLINE;
//    public static final String ROSTER_REPORT_FOOTER = NEWLINE + "# students = ";
    private static int count;

    protected CourseSession(Course course, Date startDate) {
        super(course, startDate);
        CourseSession.incrementCount();
    }

    private static void incrementCount() {
        ++count;
    }
    
    public static void resetCount() {
        count = 0;
    }
    
    public static int getCount() {
        return count;
    }
    
    @Override
    protected int getSessionLength() {
        return 16;
    }
    
    public static Session create(Course course, Date startDate) {
        return new CourseSession(course, startDate);
    }
    
}
