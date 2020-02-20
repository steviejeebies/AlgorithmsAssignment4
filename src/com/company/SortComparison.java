// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2020
 */
package com.company;
import java.util.Random;

class SortComparison {

    /**
     * A method that randomises the order of the array to make it shorter.
     * Based on the Fisher–Yates algorithm.
     * @param arr: An unsorted array of doubles.
     * @return an array with the order of the elements shuffled
     *
     * Theta(N), we iterate through each element of the array, and all
     * values within the for-loop have a time-complexity of O(1), i.e.
     * they are constant values.
     *
     */
    static void randomizeArray(double arr[])
    {
        Random randomValue = new Random();
        int length = arr.length;
        for (int index = length-1; index > 0; index--) {
            int selectedValue = randomValue.nextInt(index+1);   // pick random integer (< size of index)
            double temp = arr[index];  // store the value currently in array[index], as we will replace it
            arr[index] = arr[selectedValue];    // get array[random generated value], store it in array[index]
            arr[selectedValue] = temp;
        }
    }

    /**
     * A method that randomises the order of the array to make it shorter.
     * Based on the Fisher–Yates algorithm.
     * @param arr: An array in which we want to exchange elements;
     * @param indexA : index of array element we want to swap the value of with arr[indexB]
     * @param indexB : index of array element we want to swap the value of with arr[indexA]
     * @return an array with the order of the elements shuffled
     *
     * Theta(1), as there are no for-loops, and all operations are constant.
     */
    static void exchange(double arr[], int indexA, int indexB)
    {
        double temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
        return;
    }

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     * O(N^2), as we are iterating through the whole array in the first for-loop, then
     * the while-loop will check every preceding value to see where to store the value.
     * The best case will be O(N), if the array is already in order, but O(N^2) if the
     * array is in reverse order.
     **/
    static double [] insertionSort(double a[]) {
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
     * Theta(N^2), as we are interating through the whole array, then iterating
     * through the full array again in the second for-loop. Both worst case
     * and best case will be the same, the algorithm is Theta(N^2)
     */
    static double [] selectionSort (double a[]) {
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

        return a;
    }//end selectionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
        if(a == null) return null;
        int length = a.length;
        if(length <= 1) return a;
        if(length <= 10) return insertionSort(a);   //if length <= 10, cut-off to insertion sort
        randomizeArray(a);      // O(N)
        quickSortRecursive(a, 0, a.length-1);
        return a;

    }//end quicksort

    static void quickSortRecursive(double a[], int low, int high)
    {
        if(low >= high) return;
        int lessThan = low;
        int greaterThan = high;

        if(a[lessThan] > a[greaterThan]) exchange(a, lessThan, greaterThan);

        int i = lessThan + 1;
        double pivotValue = a[lessThan];
        double iterationValue;

        while(i < greaterThan)
        {
            iterationValue = a[i];
            if(iterationValue < lessThan) exchange(a, i++, lessThan++);
            if(iterationValue > greaterThan) exchange(a, i++, greaterThan--);
            else i++;   // if equal, keep in place;
        }

        quickSortRecursive(a, low, lessThan - 1);
        quickSortRecursive(a, greaterThan - 1, high);
    }

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

        return

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

