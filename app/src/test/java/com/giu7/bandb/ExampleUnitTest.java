package com.giu7.bandb;

import com.giu7.bandb.models.Camera;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void CameraEquals(){
        Camera c1 = new Camera("c1", 2, true, false, null, 7);
        Camera c2 = new Camera("c1", 2, false, false, null, 7);

        assertFalse(c1.equals(c2));

        c2.setTv(true);

        assertTrue(c1.equals(c2));
    }
}