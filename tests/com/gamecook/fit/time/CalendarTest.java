package com.gamecook.fit.time;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.Assert.*;

import static junit.framework.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 13, 2010
 * Time: 9:52:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalendarTest{

     @Test
     public void simpleAdd() {

         assertTrue(true);
     }

    @Test
    public void testCreateCalendar()
    {
        Calendar cal = new Calendar(10);
        assertEquals(cal.getDays(), 10);
    }

    @Test
    public void testHasNextDayPass()
    {
        Calendar cal = new Calendar(10);
        cal.nextDay();
        assertTrue(cal.hasNextDay());
    }

    @Test
    public void testHasNextDayFail()
    {
        Calendar cal = new Calendar(1);
        cal.nextDay();
        assertFalse(cal.hasNextDay());
    }

    @Test
    public void testNextDay()
    {
        Calendar cal = new Calendar(10);
        cal.nextDay();
        assertEquals(cal.getDays(), 9);
    }

    @Test
    public void testTotalDays()
    {
        Calendar cal = new Calendar(10);
        cal.nextDay();
        assertEquals(cal.getTotalDays(), 10);
    }

    @Test
    public void testNextDayBy5()
    {
        Calendar cal = new Calendar(10);
        cal.nextDay();
        cal.nextDay();
        cal.nextDay();
        cal.nextDay();
        cal.nextDay();
        assertEquals(cal.getDays(), 5);
    }

}
