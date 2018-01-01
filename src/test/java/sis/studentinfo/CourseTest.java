package sis.studentinfo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CourseTest {

    @Test
    public void testCreate() {
        Course course = new Course("CMSC", "120");
        assertEquals("CMSC", course.getDepartment());
        assertEquals("120", course.getNumber());
    }
    
    @Test
    public void testEquality() {
        Course courseA = new Course("NURS", "201");
        Course courseAPrime = new Course("NURS", "201");
        assertEquals(courseA, courseAPrime);
        
        Course courseB = new Course("ARTH", "330");
        assertFalse(courseA.equals(courseB));
        
        // reflexivity
        assertEquals(courseA, courseAPrime);
        
        // transitivity
        Course courseAPrime2 = new Course("NURS", "201");
        assertEquals(courseAPrime, courseAPrime2);
        assertEquals(courseA, courseAPrime2);
        
        // symmetry
        assertEquals(courseAPrime, courseA);
        
        // consistency
        assertEquals(courseA, courseAPrime);
        
        // comparison to null
        assertFalse(courseA.equals(null));
        
        // apples & oranges
        assertFalse(courseA.equals("CMSC-120"));
    }
    
    @Test
    public void testHashCode() {
        Course courseA = new Course("NURS", "201");
        Course courseAPrime = new Course("NURS", "201");
    
        Map<Course, String> map = new HashMap<>();
        map.put(courseA, "");
        assertTrue(map.containsKey(courseAPrime));
        
        assertEquals(courseA.hashCode(), courseAPrime.hashCode());
        
        // consistency
        assertEquals(courseA.hashCode(), courseA.hashCode());
        
    }
}
