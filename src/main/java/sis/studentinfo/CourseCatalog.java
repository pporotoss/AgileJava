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
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));){
            output.writeObject(sessions);
        }
    }

    public void load(String filename) throws IOException, ClassNotFoundException {
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
            sessions = (List<Session>) input.readObject();
        }
    }
}
