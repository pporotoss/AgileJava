package sis.studentinfo;

import sis.db.DataFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentDirectory {

    private static final String DIR_BASENAME = "studentDir";
    private DataFile db;

    private Map<String, Student> students = new HashMap<>();

    public StudentDirectory() throws IOException {
        db = DataFile.open(DIR_BASENAME);
    }

    public void add(Student student) throws IOException {
        db.add(student.getId(), student);
    }

    public Student findById(String id) throws IOException {
        return (Student) db.findBy(id);
    }

    public void close() throws IOException {
        db.close();
    }

    public void remove() {
        db.deleteFiles();
    }
}
