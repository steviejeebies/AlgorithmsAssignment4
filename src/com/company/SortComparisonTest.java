package com.company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//-------------------------------------------------------------------------

/**
 * Test class for SortComparison.java
 *
 * @author
 * @version HT 2020
 */

@RunWith(JUnit4.class)
public class SortComparisonTest {
    //~ Constructor ........................................................
    @Test
    public void testConstructor() {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------

    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty() {
        double [] empty = {};
        double [] emptyCopy = {};
        double [] result;

        result = SortComparison.insertionSort(empty);
        Assert.assertArrayEquals(emptyCopy, result, 0);
        result = SortComparison.selectionSort(empty);
        Assert.assertArrayEquals(emptyCopy, result, 0);
        result = SortComparison.mergeSortIterative(empty);
        Assert.assertArrayEquals(emptyCopy, result, 0);
        result = SortComparison.mergeSortRecursive(empty);
        Assert.assertArrayEquals(emptyCopy, result, 0);
        result = SortComparison.quickSort(empty);
        Assert.assertArrayEquals(emptyCopy, result, 0);
    }

    @Test
    public void testArray1Element() {
        double [] numbers1 = {1};
        double [] numbers1Copy = {1};
        double [] result;

        result = SortComparison.insertionSort(numbers1);
        Assert.assertArrayEquals(numbers1Copy, result, 0);
        result = SortComparison.selectionSort(numbers1);
        Assert.assertArrayEquals(numbers1Copy, result, 0);
        result = SortComparison.mergeSortIterative(numbers1);
        Assert.assertArrayEquals(numbers1Copy, result, 0);
        result = SortComparison.mergeSortRecursive(numbers1);
        Assert.assertArrayEquals(numbers1Copy, result, 0);
        result = SortComparison.quickSort(numbers1);
        Assert.assertArrayEquals(numbers1Copy, result, 0);
    }

    @Test
    public void assertAllSortingAlgorithmResultsEqual() {

        double [] numbers1000 = getDoubleArrayFromTextFile("numbers1000.txt", 1000);
        double [] numbers1000Duplicates = getDoubleArrayFromTextFile("numbers1000Duplicates.txt", 1000);
        double [] numbersNearlyOrdered1000 = getDoubleArrayFromTextFile("numbersNearlyOrdered1000.txt", 1000);
        double [] numbersReverse1000 = getDoubleArrayFromTextFile("numbersReverse1000.txt", 1000);
        double [] numbersSorted1000 = getDoubleArrayFromTextFile("numbersSorted1000.txt", 1000);

        assertTrue(testAllAlgorithms(numbers1000));
        assertTrue(testAllAlgorithms(numbers1000Duplicates));
        assertTrue(testAllAlgorithms(numbersNearlyOrdered1000));
        assertTrue(testAllAlgorithms(numbersReverse1000));
        assertTrue(testAllAlgorithms(numbersSorted1000));
    }

    public static boolean testAllAlgorithms(double [] arrayToSort) {
        double [] originalCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        double [] insertionSortResult = SortComparison.insertionSort(originalCopy);
        double [] result;
        // For this, we have to assume insertion sort is correct. Since it should match all other
        // algorithms anyway, and if all tests are passed, then we can also assume insertion
        // sort is also correct

        result = SortComparison.selectionSort(originalCopy);
        if(!Arrays.equals(insertionSortResult, result)) return false;

        originalCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        result = SortComparison.mergeSortIterative(originalCopy);
        if(!Arrays.equals(insertionSortResult, result)) return false;

        originalCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        result = SortComparison.mergeSortRecursive(originalCopy);
        if(!Arrays.equals(insertionSortResult, result)) return false;

        originalCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        result = SortComparison.quickSort(originalCopy);
        if(!Arrays.equals(insertionSortResult, result)) return false;

        return true;
    }

    /**
     * Main Method.
     * Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     */
    public static void main(String[] args) {
        double [] numbers10 = getDoubleArrayFromTextFile("numbers10.txt", 10);
        double [] numbers100 = getDoubleArrayFromTextFile("numbers100.txt", 100);
        double [] numbers1000 = getDoubleArrayFromTextFile("numbers1000.txt", 1000);
        double [] numbers1000Duplicates = getDoubleArrayFromTextFile("numbers1000Duplicates.txt", 1000);
        double [] numbersNearlyOrdered1000 = getDoubleArrayFromTextFile("numbersNearlyOrdered1000.txt", 1000);
        double [] numbersReverse1000 = getDoubleArrayFromTextFile("numbersReverse1000.txt", 1000);
        double [] numbersSorted1000 = getDoubleArrayFromTextFile("numbersSorted1000.txt", 1000);

        long [] num10Durations = new long [5];
        long [] num100Durations = new long [5];
        long [] num1000Durations = new long [5];
        long [] num1000DuplicatesDurations = new long [5];
        long [] num1000NearlyOrderedDurations = new long [5];
        long [] num1000ReverseDurations = new long [5];
        long [] num1000SortedDurations = new long [5];

        for(int i = 0; i < 3; i++) {
            getDurationAllAlgorithms(numbers10, num10Durations);
            getDurationAllAlgorithms(numbers100, num100Durations);
            getDurationAllAlgorithms(numbers1000, num1000Durations);
            getDurationAllAlgorithms(numbers1000Duplicates, num1000DuplicatesDurations);
            getDurationAllAlgorithms(numbersNearlyOrdered1000, num1000NearlyOrderedDurations);
            getDurationAllAlgorithms(numbersReverse1000, num1000ReverseDurations);
            getDurationAllAlgorithms(numbersSorted1000, num1000SortedDurations);
        }

        for(int i = 0; i < 5; i++)
        {
            num10Durations[i] = num10Durations[i]/3;
            num100Durations[i] = num100Durations[i]/3;
            num1000Durations[i] = num1000Durations[i]/3;
            num1000DuplicatesDurations[i] = num1000DuplicatesDurations[i]/3;
            num1000NearlyOrderedDurations[i] = num1000NearlyOrderedDurations[i]/3;
            num1000ReverseDurations[i] = num1000ReverseDurations[i]/3;
            num1000SortedDurations[i] = num1000SortedDurations[i]/3;
        }

        System.out.println(PrintResults(num10Durations, "10"));
        System.out.println(PrintResults(num100Durations, "100"));
        System.out.println(PrintResults(num1000Durations, "1000"));
        System.out.println(PrintResults(num1000DuplicatesDurations, "1000Dup"));
        System.out.println(PrintResults(num1000NearlyOrderedDurations, "1000NO"));
        System.out.println(PrintResults(num1000ReverseDurations, "1000Rev"));
        System.out.println(PrintResults(num1000SortedDurations, "1000Sort"));

        // have to get copy arrays, as some of the algorithms are in-place:
    }

    public static void getDurationAllAlgorithms(double [] array, long [] durationArray)
    {
        double [] originalCopy = Arrays.copyOf(array, array.length);
        double [] result;
        long start;
        long end;

        SortComparison ourCompAlgorithms = new SortComparison();

        start = System.nanoTime();
        result = ourCompAlgorithms.insertionSort(originalCopy);
        end = System.nanoTime();
        durationArray[0] += end - start;
        originalCopy = Arrays.copyOf(array, array.length);
        start = System.nanoTime();
        result = ourCompAlgorithms.selectionSort(originalCopy);
        end = System.nanoTime();
        originalCopy = Arrays.copyOf(array, array.length);
        durationArray[1] += end - start;
        start = System.nanoTime();
        result = ourCompAlgorithms.quickSort(originalCopy);
        end = System.nanoTime();
        originalCopy = Arrays.copyOf(array, array.length);
        durationArray[2] += end - start;
        start = System.nanoTime();
        result = ourCompAlgorithms.mergeSortRecursive(originalCopy);
        end = System.nanoTime();
        durationArray[3] += end - start;
        originalCopy = Arrays.copyOf(array, array.length);
        start = System.nanoTime();
        result = ourCompAlgorithms.mergeSortIterative(originalCopy);
        end = System.nanoTime();
        durationArray[4] += end - start;
        originalCopy = Arrays.copyOf(array, array.length);




    }

    public static String PrintResults(long [] result, String arrayName)
    {
        return arrayName + ": INS[" + result[0] + "] SEL[" + result[1] + "] QCK[" + result[2] + "] MGR[" + result[3] + "] MGI[" + result[4] + ']';
    }

    public static double [] getDoubleArrayFromTextFile(String fileName, int arraySize)
    {
        double [] returnArray = new double[arraySize];
        int i = 0; // array index
        try {
            File file = new File(fileName);    //creates a new file instance
            FileReader ourFileReader = new FileReader(file);   //reads the file
            BufferedReader ourBufferedReader = new BufferedReader(ourFileReader);
            while (i < arraySize)
                returnArray[i++] = Double.parseDouble(ourBufferedReader.readLine());
            ourFileReader.close();    //closes the stream and release the resources
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return returnArray;
    }

}

