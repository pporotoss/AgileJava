package sis.studentinfo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentDirectoryTest {
    private StudentDirectory dir;

    @Before
    public void setUp() {
        dir = new StudentDirectory();
    }

    @Test
    public void testStoreAndRetrieve() {
        final int numberofStudents = 10;

        for (int i = 0; i < numberofStudents; i++) {
            addStudent(dir, i);
        }

        for (int i = 0; i < numberofStudents; i++) {
            verifyStudentLookup(dir, i);
        }
    }

    private void addStudent(StudentDirectory directory, int i) {
        String id = ""+i;
        Student student = new Student(id);
        student.setId(id);
        student.addCredits(i);
        directory.add(student);
    }

    private void verifyStudentLookup(StudentDirectory directory, int i) {
        String id = "" + i;
        Student student = directory.findById(id);
        assertEquals(id, student.getLastName());
        assertEquals(id, student.getId());
        assertEquals(i, student.getCredits());
    }
}
