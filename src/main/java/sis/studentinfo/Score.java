package sis.studentinfo;

public class Score {

    public int score(String score) {
        return Integer.parseInt(score);
    }

    public boolean isValid(String score) {
        try{
            Integer.parseInt(score);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}
