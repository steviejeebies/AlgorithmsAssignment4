package com.company;

import static org.junit.Assert.assertEquals;

import com.company.SortComparison;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

    }


    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------

    /**
     * Main Method.
     * Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     */
    public static void main(String[] args) {
        double [] empty = {};
        double [] number1 = {1};
        double [] numbers10 = getArrayFromTextFile("numbers10.txt", 10);
        double [] numbers100 = getArrayFromTextFile("numbers100.txt", 100);
        double [] numbers1000 = getArrayFromTextFile("numbers1000.txt", 1000);
        double [] numbers1000Duplicates = getArrayFromTextFile("numbers1000Duplicates.txt", 1000);
        double [] numbersNearlyOrdered1000 = getArrayFromTextFile("numbersNearlyOrdered1000.txt", 1000);
        double [] numbersReverse1000 = getArrayFromTextFile("numbersReverse1000.txt", 1000);
        double [] numbersSorted1000 = getArrayFromTextFile("numbersSorted1000.txt", 1000);


    }

    public static double [] getArrayFromTextFile(String fileName, int arraySize)
    {
        double [] returnArray = new double[arraySize];
        int i = 0; // array index
        try {
            File file = new File(fileName);    //creates a new file instance
            FileReader ourFileReader = new FileReader(file);   //reads the file
            BufferedReader ourBufferedReader = new BufferedReader(ourFileReader);
            while (i < arraySize) {
                returnArray[i++] = Double.parseDouble(ourBufferedReader.readLine());
            }
            ourFileReader.close();    //closes the stream and release the resources
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnArray;
    }

}

