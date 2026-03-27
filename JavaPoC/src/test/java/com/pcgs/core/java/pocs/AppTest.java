package com.pcgs.core.java.pocs;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.lang.reflect.Field;

import com.pcgs.core.java.pocs.threads.ClassLockingPoC;

/**
 * Unit test for ClassLockingPoC and simple App.
 */
public class AppTest
    extends TestCase
{
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    // --- Helper methods to access the private static 'count' field on ClassLockingPoC ---
    private void setCount(int value) throws Exception {
        Field f = ClassLockingPoC.class.getDeclaredField("count");
        f.setAccessible(true);
        f.setInt(null, value);
    }

    private int getCount() throws Exception {
        Field f = ClassLockingPoC.class.getDeclaredField("count");
        f.setAccessible(true);
        return f.getInt(null);
    }

    public void testApp()
    {
        assertTrue( true );
    }

    // New tests for ClassLockingPoC

    public void testIncrementOnce() throws Exception {
        setCount(0);
        ClassLockingPoC.increment();
        assertEquals(1, getCount());
    }

    public void testIncrementTwiceSequential() throws Exception {
        setCount(0);
        ClassLockingPoC.increment();
        ClassLockingPoC.increment();
        assertEquals(2, getCount());
    }

    public void testIncrementFromTwoThreads() throws Exception {
        setCount(0);
        Thread t1 = new Thread(ClassLockingPoC::increment);
        Thread t2 = new Thread(ClassLockingPoC::increment);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertEquals(2, getCount());
    }

    public void testConcurrentManyIncrements() throws Exception {
        setCount(0);
        final int threads = 50;
        Thread[] arr = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            arr[i] = new Thread(ClassLockingPoC::increment);
        }
        for (Thread t : arr) t.start();
        for (Thread t : arr) t.join();
        assertEquals(threads, getCount());
    }

    public void testResetAndIncrement() throws Exception {
        // Ensure we can reset the static count between tests and that increment works from arbitrary start
        setCount(10);
        ClassLockingPoC.increment();
        assertEquals(11, getCount());
        // reset back to 0 to avoid affecting other tests
        setCount(0);
    }
}
