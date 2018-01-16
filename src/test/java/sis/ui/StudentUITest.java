package sis.ui;

import org.junit.Test;
import sis.studentinfo.Student;

import java.io.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StudentUITest {
    private static final String name = "Leo Xerces Schmoo";

    @Test
    public void testCreateStudent() throws IOException {
        StringBuffer expectedOutput = new StringBuffer();
        StringBuffer input = new StringBuffer();
        setup(expectedOutput, input);
        byte[] buffer = input.toString().getBytes();

        InputStream inputStream = new ByteArrayInputStream(buffer);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        OutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        InputStream consoleIn = System.in;
        PrintStream consoleOut = System.out;

        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));
        try {
//            StudentUI ui = new StudentUI(reader, writer);
            StudentUI ui = new StudentUI();
            ui.run();

            assertEquals(expectedOutput.toString(), outputStream.toString());

            assertStudents(ui.getAddedStudents());
        }
        finally {
            System.setIn(consoleIn);
            System.setOut(consoleOut);
        }
    }

    private String line(String input){
        return String.format("%s%n", input);
    }

    private void setup(StringBuffer expectedOutput, StringBuffer input) {
        expectedOutput.append(StudentUI.MENU);
        input.append(line(StudentUI.ADD_OPTION));
        expectedOutput.append(StudentUI.NAME_PROMPT);
        input.append(line(name));
        expectedOutput.append(line(StudentUI.ADDED_MESSAGE));
        expectedOutput.append(StudentUI.MENU);
        input.append(line(StudentUI.QUIT_OPTION));
    }

    private void assertStudents(List<Student> students) {
        assertEquals(1, students.size());
        Student student = students.get(0);
        assertEquals(name, student.getName());
    }
}
