package com.charlest.openweathermap;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);


        Date date = new Date(1550560005);
        String time = new SimpleDateFormat("hh:mma", Locale.getDefault()).format(date);

        System.out.println(time + " YEAH");
    }
}