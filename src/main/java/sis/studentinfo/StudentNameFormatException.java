package sis.studentinfo;

public class StudentNameFormatException extends IllegalArgumentException {
    public StudentNameFormatException() {
    }
    public StudentNameFormatException(String message) {
        super(message);
    }
}
