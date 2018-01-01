package sis.report;

import org.junit.Before;
import org.junit.Test;
import sis.studentinfo.Student;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReportCardTest {
    private ReportCard card;
    
    @Before
    public void setUp() {
        this.card = new ReportCard();
    }
    
    
    @Test
    public void testMessage() {
        assertEquals(ReportCard.A_MESSAGE, card.getMessage(Student.Grade.A));
        assertEquals(ReportCard.B_MESSAGE, card.getMessage(Student.Grade.B));
        assertEquals(ReportCard.C_MESSAGE, card.getMessage(Student.Grade.C));
        assertEquals(ReportCard.D_MESSAGE, card.getMessage(Student.Grade.D));
        assertEquals(ReportCard.F_MESSAGE, card.getMessage(Student.Grade.F));
    }
    
    @Test
    public void testKeys() {
        Set<Student.Grade> expectedGrades = new HashSet<>();
        expectedGrades.add(Student.Grade.A);
        expectedGrades.add(Student.Grade.B);
        expectedGrades.add(Student.Grade.C);
        expectedGrades.add(Student.Grade.D);
        expectedGrades.add(Student.Grade.F);
        
        Set<Student.Grade> grades = new HashSet<>();
        for (Student.Grade grade : card.getMessages().keySet()) {
            grades.add(grade);
        }
       
        assertEquals(expectedGrades, grades);
    }
    
    @Test
    public void testValues() {
        List<String> expectedMessages = new ArrayList<>();
        expectedMessages.add(ReportCard.A_MESSAGE);
        expectedMessages.add(ReportCard.B_MESSAGE);
        expectedMessages.add(ReportCard.C_MESSAGE);
        expectedMessages.add(ReportCard.D_MESSAGE);
        expectedMessages.add(ReportCard.F_MESSAGE);
    
        Collection<String> messages = card.getMessages().values();
        for(String message : messages) {
            assertTrue(expectedMessages.contains(message));
        }
        
        assertEquals(expectedMessages.size(), messages.size());
    }
    
}
