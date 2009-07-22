package com.commonsware.cwac.merge;

import android.test.ActivityInstrumentationTestCase;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.commonsware.cwac.merge.MergeAdapterDemoTest \
 * com.commonsware.cwac.merge.tests/android.test.InstrumentationTestRunner
 */
public class MergeAdapterDemoTest extends ActivityInstrumentationTestCase<MergeAdapterDemo> {

    public MergeAdapterDemoTest() {
        super("com.commonsware.cwac.merge", MergeAdapterDemo.class);
    }

}
