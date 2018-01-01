package sis.util;

import java.util.Random;

public class PasswordGenerator {

    private String password;
    private static final int PASSWORD_LENGTH = 8;
    private Random random = new Random();
    
    static final char LOW_END_PASSWORD_CAHR = 48;
    static final char HIGH_END_PASWORD_CHAR = 122;
    
    void setRandom(Random random) {
        this.random = random;
    }
    
    public String generatePassword() {
        StringBuilder builder = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            builder.append(getRandomChar());
        }
        return builder.toString();
    }
    
    private char getRandomChar() {
        final char max = HIGH_END_PASWORD_CHAR - LOW_END_PASSWORD_CAHR;
        return (char) (random.nextInt(max) + LOW_END_PASSWORD_CAHR);
    }
    
    public String getPassword() {
        return password;
    }
    
}
