package sis.report;

import sis.studentinfo.CourseSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text.NEW_LINE;

public class CourseReport {
    private List<CourseSession> sessions;

    public CourseReport() {
        this.sessions = new ArrayList<>();
    }

    public void add(CourseSession session) {
        sessions.add(session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder builder = new StringBuilder();
        for (CourseSession session : sessions) {
            builder.append(
              session.getDepartment() + " "
              + session.getNumber() + NEW_LINE
            );
        }

        return builder.toString();
    }
}
