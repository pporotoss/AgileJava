package sis.report;

import sis.studentinfo.Course;
import sis.studentinfo.CourseSession;

import java.util.Date;

public class SummerCourseSession extends Session {
    
    public static Session create(Course course, Date startDate) {
        return new SummerCourseSession(course, startDate);
    }
    
    private SummerCourseSession(Course course, Date startDate) {
        super(course, startDate);
    }
    
    @Override
    protected int getSessionLength() {
        return 8;
    }
}
