package sis.studentinfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StudentDirectoryTest {
    private StudentDirectory dir;

    @Before
    public void setUp() throws IOException {
        dir = new StudentDirectory();
    }

    @After
    public void tearDown() throws IOException {
        dir.close();
        dir.remove();
    }

    @Test
    public void testStoreAndRetrieve() throws IOException {
        final int numberofStudents = 10;

        for (int i = 0; i < numberofStudents; i++) {
            addStudent(dir, i);
        }

        for (int i = 0; i < numberofStudents; i++) {
            verifyStudentLookup(dir, i);
        }
    }

    @Test
    public void randomAccessTest() throws IOException {
        final int numberOfStudents = 10;
        for(int i = 0; i < numberOfStudents; i++) {
            addStudent(dir, i);
        }
        dir.close();

        dir = new StudentDirectory();
        for(int i = 0; i < numberOfStudents; i++) {
            verifyStudentLookup(dir, i);
        }
    }

    private void addStudent(StudentDirectory directory, int i) throws IOException {
        String id = ""+i;
        Student student = new Student(id);
        student.setId(id);
        student.addCredits(i);
        directory.add(student);
    }

    private void verifyStudentLookup(StudentDirectory directory, int i) throws IOException {
        String id = "" + i;
        Student student = directory.findById(id);
        assertEquals(id, student.getLastName());
        assertEquals(id, student.getId());
        assertEquals(i, student.getCredits());
    }
}
