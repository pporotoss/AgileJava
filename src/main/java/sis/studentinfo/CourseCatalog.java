package sis.studentinfo;

import sis.report.Session;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseCatalog {
    private List<Session> sessions = new ArrayList<>();

    public void add(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return this.sessions;
    }

    public void clearAll() {
        this.sessions.clear();
    }

    public void store(String filename) throws IOException{
        try(DataOutputStream output = new DataOutputStream(new FileOutputStream(filename));){
            output.writeInt(sessions.size());
            for(Session session : sessions) {
                output.writeLong(session.getStartDate().getTime());
                output.writeInt(session.getNumberOfCredits());
                output.writeUTF(session.getDepartment());
                output.writeUTF(session.getNumber());
            }
        }
    }

    public void load(String filename) throws IOException {
        try(DataInputStream input = new DataInputStream(new FileInputStream(filename))) {
            int numberOfSessions = input.readInt();
            for(int i = 0; i < numberOfSessions; i++) {
                Date startDate = new Date(input.readLong());
                int credits = input.readInt();
                String department = input.readUTF();
                String number = input.readUTF();
                Course course = new Course(department, number);
                Session session = CourseSession.create(course, startDate);
                sessions.add(session);
            }
        }
    }
}
