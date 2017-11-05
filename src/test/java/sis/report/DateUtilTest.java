package sis.report;

import org.junit.Test;
import sis.studentinfo.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    @Test
    public void createDateTest() {
        Date date = DateUtil.createDate(2000, 1, 1);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        assertEquals(2000, calendar.get(Calendar.YEAR));
        assertEquals(Calendar.JANUARY, calendar.get(Calendar.MONTH));
        assertEquals(1, calendar.get(Calendar.DAY_OF_MONTH));
    }
}