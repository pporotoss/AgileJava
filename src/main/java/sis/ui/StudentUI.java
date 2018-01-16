package sis.ui;

import sis.studentinfo.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentUI {
    public static String MENU = "(A)dd or (Q)uit?";
    public static String ADD_OPTION = "A";
    public static String QUIT_OPTION = "Q";
    public static String NAME_PROMPT = "Name : ";
    public static String ADDED_MESSAGE = "Added";

    private BufferedReader reader;
    private BufferedWriter writer;
    private List<Student> students = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new StudentUI().run();
    }

    public StudentUI() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public StudentUI(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() throws IOException {
        String line;
        do {
            write(MENU);
            line = reader.readLine();
            if(line.equals(ADD_OPTION)) {
                addStudent();
            }
        } while (!line.equals(QUIT_OPTION));
    }

    private void write(String line) throws IOException {
        writer.write(line);
        writer.flush();
    }

    private void addStudent() throws IOException {
        write(NAME_PROMPT);
        String name = reader.readLine();

        students.add(new Student(name));
        writeln(ADDED_MESSAGE);
    }

    private void writeln(String line) throws IOException {
        write(line);
        writer.newLine();
        writer.flush();
    }

    public List<Student> getAddedStudents() {
        return this.students;
    }
}
