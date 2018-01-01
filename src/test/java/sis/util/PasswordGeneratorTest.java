package sis.util;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PasswordGeneratorTest {
    
    @Test
    public void testGeneratePassword() {
        PasswordGenerator generator = new PasswordGenerator();
        generator.setRandom(new MockRandom('A'));
        assertEquals("ABCDEFGH", generator.generatePassword());
        
        generator.setRandom(new MockRandom('C'));
        assertEquals("CDEFGHIJ", generator.generatePassword());
    }
    
    private class MockRandom extends Random {
        private int i;
        public MockRandom(char startCharValue) {
            i = startCharValue - PasswordGenerator.LOW_END_PASSWORD_CAHR;
        }
    
        @Override
        protected int next(int bits) {
            return i++;
        }
    }
}
