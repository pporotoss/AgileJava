package sis.studentinfo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PerformanceTest {

    private static final double tolerance = 0.005;

    @Test
    public void testAverage() {
        Performance performance = new Performance();
        performance.setNumberOfTests(4);
        performance.set(0, 98);
        performance.set(1, 92);
        performance.set(2, 81);
        performance.set(3, 72);

        assertEquals(92, performance.get(1));

        assertEquals(85.75, performance.average(), tolerance);
    }

    @Test
    public void testArrayParam() {
        Performance performance = new Performance();
        performance.setScores(new int[] {75, 72, 90, 60});
        assertEquals(74.25, performance.average(), tolerance);

        performance.setScores(100, 90);
        assertEquals(95, performance.average(), tolerance);
    }

}
