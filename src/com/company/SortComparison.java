// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2020
 */
package com.company;

class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]) {
        double value;
        int  i, j;
        int size = a.length;

        for(i = 1; i < size; i++)
        {
            value = a[i];
            j = i;

            while((j > 0) && (a[j-1] > value))
            {
                a[j] = a[j-1];
                j = j - 1;
            }

            a[j] = value;
        }

        return a;
    }

    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){
        int length = a.length;
        int i, j;

        for(i = 0; i < length-1; i++)
        {
            int minIndex = i;
            for(j = i+1; j < length; j++)
            {
                if(a[j] < a[minIndex])
                    minIndex = j;
            }

            double tempValue = a[minIndex];
            a[minIndex] = a[i];
            a[i] = tempValue;

        }

    }//end selectionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
        int length = a.length;
        if(length <= 1) return a;

        // ASK ABOUT SHUFFLING ARRAY

    }//end quicksort

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {

        //todo: implement the sort

    }//end mergesortIterative



    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {


        //todo: implement the sort
    }//end mergeSortRecursive







    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }

}//end class

