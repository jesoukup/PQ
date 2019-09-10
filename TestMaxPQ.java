//
// Title: P1 PQ
// Author: Joseph Soukup
// Email: jsoukup2@wisc.edu
// Due: 2/5/2018
// Known bugs: Unable to add more items than the inital array size, remove can be buggy
//

import java.util.Arrays;

/**
 * This class tests MaxPQ to make sure it is working properly.
 */
public class TestMaxPQ {
    private static MaxPQ<Integer> testPQ = new MaxPQ<Integer>(); // This creates the MaxPQ object to be used to test

    /**
     * This main method calls on the other methods to test MaxPQ.
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(test1_isEmpty());
        System.out.println(test2_getMax_Exception());
        System.out.println(test3_removeMax_Exception());
        
        testPQ.insert(5);
        System.out.println(test4_insert());
        
        testPQ.insert(2);
        testPQ.insert(3);
        testPQ.insert(4);
        testPQ.insert(1);
        //System.out.println(testPQ.print());

        System.out.println(test5_getMax());
        System.out.println(test6_removeMax());
        System.out.println(test7_dups_allowed());
        System.out.println(test8_big_data());
    }
    
    /**
     * This method tests to see if isEmpty() is working correctly.
     * 
     * @return a string of the test results
     */
    public static String test1_isEmpty() {
        if (testPQ.isEmpty())
            return "test1_isEmpty passed: isEmpty() returned true when the PQ was just constructed.";
        else
            return "test1_isEmpty failed: isEmpty() returned false when the PQ was just constructed.";
    }
    
    /**
     * This method tests to see if getMax() is throwing its exception when it should be.
     * 
     * @return a string of the test results
     */
    public static String test2_getMax_Exception() {
        try {
            testPQ.getMax();
        } catch (EmptyQueueException e) {
            return "test2_getMax_Exception passed: getMax() threw an exception when the PQ was empty.";
        }
        return "test2_getMax_Exception failed: getMax() should have thrown an exception.";
    }
    
    /**
     * This method tests to see if removeMax is throwing its exception when it should be.
     * 
     * @return a string of the test results
     */
    public static String test3_removeMax_Exception() {
        try {
            testPQ.removeMax();
        } catch (EmptyQueueException e) {
            return "test3_removeMax_Exception passed: removeMax() threw an exception when the PQ was empty.";
        }
        return "test3_removeMax_Exception failed: removeMax() should have thrown an exception.";
    }
    
    /**
     * This method tests to see if the insert method is working correctly.
     * 
     * @return a string of the test results
     */
    public static String test4_insert() {
        if (testPQ.isEmpty())
            return "test4_insert failed: isEmpty() returned true when an item was added to PQ.";
        else
            return "test4_insert passed: isEmpty() returned false when an item was added to PQ.";
    }
    
    /**
     * This method test to see if getMax() is returning the right value.
     * 
     * @return a string of the test results
     */
    public static String test5_getMax() {
        int max = 5;
        if (testPQ.getMax() == max) 
            return "test5_getMax() passed: getMax() returned highest priority item.";
        else
            return "test5_getMax() failed: getMax() did not return highest priority item.";
    }
    
    /**
     * This method tests to see if removeMax() is returning and removing the right value.
     * 
     * @return a string of the test results
     */
    public static String test6_removeMax() {
        int expected = 5;
        int nextExpected = 4;
        int x = testPQ.removeMax();
        int y = testPQ.getMax();
        if (x == expected && y == nextExpected) 
            return "test6_removeMax() passed: removeMax() returned and removed highest priority item.\n" + 
                            "Expected after removeMax() and getMax(): " + expected + ", " + nextExpected + "\n" +
                            "Actual: " + x + ", " + y;
        else
            return "test6_removeMax() failed: removeMax() did not return and remove highest prioirty item.";
    }
    
    /**
     * This method checks to see if duplicates are working correctly.
     * 
     * @return a string of the test results
     */
    public static String test7_dups_allowed() {
        try {
            testPQ.insert(5);
            testPQ.insert(5);
            testPQ.getMax();
            int max = testPQ.getMax();
            if (max == 5) 
                return "test7_dups_allowed passed: no error occurred.";
        } catch (Exception e) {
            return "test7_dups_allowed failed: exception was thrown.";
        } 
        return "test7_dups_allowed failed: duplicate did not add to heap correctly.";
    }
    
    /**
     * This method checks to see if the queue is able to take many items properly.
     * 
     * @return a string of the test results
     */
    public static String test8_big_data() {
        int expected = 22;
        int nextExpected = 21;
        try {
            testPQ.insert(12);
            testPQ.insert(17);
            testPQ.insert(21);
            testPQ.insert(7);
            testPQ.insert(19);
            testPQ.insert(11);
            testPQ.insert(6);
            testPQ.insert(8);
            testPQ.insert(10);
            testPQ.insert(14);
            testPQ.insert(15);
            testPQ.insert(13);
            testPQ.insert(16);
            testPQ.insert(18);
            testPQ.insert(20);
            testPQ.insert(22);
            
            int max = testPQ.getMax();
            int nextMax = testPQ.removeMax();
            if (max == expected && nextMax == nextExpected)
                return "test8_big_data() passed: queue is able to take many inserts correctly.";
        } catch (Exception e) {
            return "test8_big_data() failed: exception was thrown.";
        }
        return "test8_big_data() failed: queue is unable to take inserts properly.";
    }
}
