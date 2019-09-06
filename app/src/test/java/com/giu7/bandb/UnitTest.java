package com.giu7.bandb;

import android.util.Log;

import com.giu7.bandb.models.Camera;
import com.giu7.bandb.utils.LocalDateTimeTypeConverters;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class UnitTest {

    @Test
    public void CameraEquals(){
        Camera c1 = new Camera("c1", 2, true, false, null, 7);
        Camera c2 = new Camera("c1", 2, false, false, null, 7);

        assertFalse(c1.equals(c2));

        c2.setTv(true);

        assertTrue(c1.equals(c2));
    }

    @Test
    public void DateTime2String(){
        LocalDateTime oggi = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);

        String oggiString = LocalDateTimeTypeConverters.toString(oggi);

        assertEquals(oggiString, "2019-09-03T10:00");
    }

    @Test
    public void String2DateTime(){
        String oggiString = "2019-09-03T10:00";
        LocalDateTime oggi = LocalDateTimeTypeConverters.toDate(oggiString);

        LocalDateTime oggiCheck = LocalDateTime.of(2019, Month.SEPTEMBER, 3, 10, 0);

        assertEquals(oggi, oggiCheck);
    }

}