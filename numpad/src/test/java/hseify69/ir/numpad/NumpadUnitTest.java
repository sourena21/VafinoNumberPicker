package hseify69.ir.numpad;

import org.junit.Test;

import hseify69.ir.numpad.helpers.Utils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NumpadUnitTest {
    @Test
    public void testMaxDay() {
        assertEquals(29, Utils.getDayRange(1398, 12));
        assertEquals(31, Utils.getDayRange(1398, 1));
        assertEquals(30, Utils.getDayRange(1398, 7));
        assertEquals(31, Utils.getDayRange(1398, 5));
        assertEquals(31, Utils.getDayRange(1398, 6));
        assertEquals(30, Utils.getDayRange(1399, 11));
        assertEquals(30, Utils.getDayRange(1399, 12));
    }
}