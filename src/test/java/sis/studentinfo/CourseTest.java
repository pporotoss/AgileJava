package sis.studentinfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourseTest {

    @Test
    public void testCreate() {
        Course course = new Course("CMSC", "120");
        assertEquals("CMSC", course.getDepartment());
        assertEquals("120", course.getNumber());
    }
}
