package com.smh.sequential;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void getInfo() throws InterruptedException {
        ConnectionInfo.getInfo();
        Thread.currentThread().sleep(1000);
        assertEquals(3,ConnectionInfo.info.size());
    }

    @Test
    public void writeListNames(){



    }

}