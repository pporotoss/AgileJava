package sis.studentinfo;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ScoreTest {

    @Test
    public void testCaptureScore() {
        Score score = new Score();
        assertEquals(75, score.score("75"));
    }

    @Test(expected = NumberFormatException.class)
    public void testBadScoreEntered() {
        Score score = new Score();
        score.score("abc");
    }

    @Test
    public void testIsValid() {
        Score score = new Score();
        assertTrue(score.isValid("75"));
        assertFalse(score.isValid("abc"));
    }
}
