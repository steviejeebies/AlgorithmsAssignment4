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
    @Test
    public void testConstructor() {
        new SortComparison();
    }

    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty() {
        double[] empty = {};
        double[] emptyCopy = {};
        double[] result;

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
        double[] numbers1 = {1};
        double[] numbers1Copy = {1};
        double[] result;

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

        double[] numbers1000 = getDoubleArrayFromTextFile("numbers1000.txt", 1000);
        double[] numbers1000Duplicates = getDoubleArrayFromTextFile("numbers1000Duplicates.txt", 1000);
        double[] numbersNearlyOrdered1000 = getDoubleArrayFromTextFile("numbersNearlyOrdered1000.txt", 1000);
        double[] numbersReverse1000 = getDoubleArrayFromTextFile("numbersReverse1000.txt", 1000);
        double[] numbersSorted1000 = getDoubleArrayFromTextFile("numbersSorted1000.txt", 1000);

        assertTrue(testAllAlgorithms(numbers1000));
        assertTrue(testAllAlgorithms(numbers1000Duplicates));
        assertTrue(testAllAlgorithms(numbersNearlyOrdered1000));
        assertTrue(testAllAlgorithms(numbersReverse1000));
        assertTrue(testAllAlgorithms(numbersSorted1000));
    }

    public static boolean testAllAlgorithms(double[] arrayToSort) {
        double[] originalCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        double[] insertionSortResult = SortComparison.insertionSort(originalCopy);
        double[] result;
        // For this, we have to assume insertion sort is correct. Since it should match all other
        // algorithms anyway, and if all tests are passed, then we can also assume insertion
        // sort is also correct

        result = SortComparison.selectionSort(originalCopy);
        if (!Arrays.equals(insertionSortResult, result)) return false;

        originalCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        result = SortComparison.mergeSortIterative(originalCopy);
        if (!Arrays.equals(insertionSortResult, result)) return false;

        originalCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        result = SortComparison.mergeSortRecursive(originalCopy);
        if (!Arrays.equals(insertionSortResult, result)) return false;

        originalCopy = Arrays.copyOf(arrayToSort, arrayToSort.length);
        result = SortComparison.quickSort(originalCopy);
        if (!Arrays.equals(insertionSortResult, result)) return false;

        return true;
    }


    /**
     * Main Method.
     * Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     */
    public static void main(String[] args) {
        /*
                EXPERIMENT RESULTS
                            INS  |  SEL	  | QUICK | MRGR  |  MRGI
                __________________________________________________
                10	    |   3133	2566	2000	4533	20400
                100	    |   40700	69066	34000	48400	78100
                1000    |	2137033	3077533	779766	423266	212300
                1000Dup |	4382400	2272333	113766	111966	141166
                1000NO  |	26200	416033	200133	64266	102800
                1000Rev |	254666	611466	1634666	62000	146700
                1000Sort|	2200	410100	1349533	30233	181233

         *Results are in NanoTime

           Answers:
           A: The order of the input for the different 1000-size arrays affects each of the algorithms differently,
              Insertion sort is almost immediate when iterating through a sorted array, as there is no need to push any
              elements back. It is exponentially worse for an array with duplicate entries, as it will repeatedly have
              to push a value over array elements with equal values. Insertion sort benefits from the nearly ordered
              array, as there are very few elements it has to push back. Selection sort also performs poorly for
              duplicate entries, but the other three order types are mostly consistent. In all three of these cases,
              we will have to iterate through each element of the array, find the minimum value, and exchange
              it, and this isn't impacted much by the order. My implementation of quick pivot uses 2 pivots, and the
              disadvantages of Quicksort are most clear with the Reverse order and already Sorted arrays. In either
              case, the pivots will not be able to move the other values anywhere useful enough to break down the
              sorting (i.e. for reverse, the less-than and greater-than pivots will switch, but nothing else will,
              until the next recursion, in which the same thing happens for lessThan+1 and greaterThan-1). 2-Pivot
              quick sort performed best with Duplicate entries, as this meant all the duplicates would have been
              grouped together relatively early. MergeSort recursive performed very well for Nearly-Ordered, Reverse
              and Sorted arrays, and better than quick-sort for both the 1000 Random and 1000 Duplicate arrays, but
              Merge Sort iterative performed better for 1000 random entries.

           B: The algorithm with the biggest difference between best and worst case for input size 1000 was
              insertionSort. For insertion sort, an already sorted array only has to be iterated through once, and
              each iteration of this loop checks if value n is greater than n - 1. As this is always guaranteed to be
              true then iterating through the array will be very quick. Where insertionSort fails is when there are
              many duplicates. Each time a value is pushed back in the array, it must also be pushed back through
              every value that is equal to it, too. Due to the fact that there are so many duplicates in this array,
              this means that a lot of time is wasted.

           C: The algorithm with the worst scalability is Selection sort. An input of size 10^3 is 1,999.35 times slow that
              the same algorithm with a input size of 10^1.
              The algorithm with the best scalability is Iterative Merge Sort. While in the case of input size 10, Iterative
              Merge Sort was by far the worst, we do have the option to cut-off to other algorithms for smaller N values.
              However, the difference between the smallest N value duration and largest N value duration for Iterative
              Merge Sort is the lowest, and it also produced the best result out of all the algorithms at 1000 N input size.

           D: When I was initially implementing merge sort, I tried to modify the recursive merge() function so that we
              wouldn't have to copy in the elements from the original array into the temp array each time (i.e., through
              continuously switching the temp and the original array when calling merge()). However, I was
              unable to implement this with the iterative version of merge sort, and opted to keep the same merge() method
              for both. In respect to the results, Merge Sort Recursive typically produces faster completion, but Merge Sort
              iterative is more consistent in how long it takes to sort the array.

            E: For each of the 7 Input files, the fastest were:
                10	:	Quick (cut off to insertion sort)
                100	:	Quick
                1000	:	MrgIterative
                1000Dup	:	MrgRecursive
                1000NO	:	Insertion
                1000Rev	:	MrgRecursive
                1000Sort	:	Insertion
         */

        double[] numbers10 = getDoubleArrayFromTextFile("numbers10.txt", 10);
        double[] numbers100 = getDoubleArrayFromTextFile("numbers100.txt", 100);
        double[] numbers1000 = getDoubleArrayFromTextFile("numbers1000.txt", 1000);
        double[] numbers1000Duplicates = getDoubleArrayFromTextFile("numbers1000Duplicates.txt", 1000);
        double[] numbersNearlyOrdered1000 = getDoubleArrayFromTextFile("numbersNearlyOrdered1000.txt", 1000);
        double[] numbersReverse1000 = getDoubleArrayFromTextFile("numbersReverse1000.txt", 1000);
        double[] numbersSorted1000 = getDoubleArrayFromTextFile("numbersSorted1000.txt", 1000);

        long[] num10Durations = new long[5];
        long[] num100Durations = new long[5];
        long[] num1000Durations = new long[5];
        long[] num1000DuplicatesDurations = new long[5];
        long[] num1000NearlyOrderedDurations = new long[5];
        long[] num1000ReverseDurations = new long[5];
        long[] num1000SortedDurations = new long[5];

        for (int i = 0; i < 3; i++) {
            getDurationAllAlgorithms(numbers10, num10Durations);
            getDurationAllAlgorithms(numbers100, num100Durations);
            getDurationAllAlgorithms(numbers1000, num1000Durations);
            getDurationAllAlgorithms(numbers1000Duplicates, num1000DuplicatesDurations);
            getDurationAllAlgorithms(numbersNearlyOrdered1000, num1000NearlyOrderedDurations);
            getDurationAllAlgorithms(numbersReverse1000, num1000ReverseDurations);
            getDurationAllAlgorithms(numbersSorted1000, num1000SortedDurations);
        }

        for (int i = 0; i < 5; i++) {
            num10Durations[i] = num10Durations[i] / 3;
            num100Durations[i] = num100Durations[i] / 3;
            num1000Durations[i] = num1000Durations[i] / 3;
            num1000DuplicatesDurations[i] = num1000DuplicatesDurations[i] / 3;
            num1000NearlyOrderedDurations[i] = num1000NearlyOrderedDurations[i] / 3;
            num1000ReverseDurations[i] = num1000ReverseDurations[i] / 3;
            num1000SortedDurations[i] = num1000SortedDurations[i] / 3;
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

    public static void getDurationAllAlgorithms(double[] array, long[] durationArray) {
        double[] originalCopy = Arrays.copyOf(array, array.length);
        double[] result;
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

    public static String PrintResults(long[] result, String arrayName) {
        return arrayName + ": INS[" + result[0] + "] SEL[" + result[1] + "] QCK[" + result[2] + "] MGR[" + result[3] + "] MGI[" + result[4] + ']';
    }

    public static double[] getDoubleArrayFromTextFile(String fileName, int arraySize) {
        double[] returnArray = new double[arraySize];
        int i = 0; // array index
        try {
            File file = new File(fileName);    //creates a new file instance
            FileReader ourFileReader = new FileReader(file);   //reads the file
            BufferedReader ourBufferedReader = new BufferedReader(ourFileReader);
            while (i < arraySize)
                returnArray[i++] = Double.parseDouble(ourBufferedReader.readLine());
            ourFileReader.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnArray;
    }
}

