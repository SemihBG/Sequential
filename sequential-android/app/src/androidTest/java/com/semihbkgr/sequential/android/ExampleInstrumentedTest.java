package com.semihbkgr.sequential.android;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.semihbkgr.sequential.android.entity.Information.InformationDownload;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.smh.sequential", appContext.getPackageName());
    }

    @Test
    public void writeListNames() throws InterruptedException, IOException, ClassNotFoundException {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        ConnectionInfo.getInfo();

        Thread.currentThread().sleep(1000);

        InformationDownload.saveInfo(appContext);

        InformationDownload.takeInfo(appContext);

        System.out.println(ConnectionInfo.info.size());

    }


}
