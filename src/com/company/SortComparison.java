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
    public static double [] insertionSort(double a[]) {
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
     * Modified version of insertion sort, to allow for sorting for a fixed number of
     * elements within an array.
     * @param a: An unsorted array of doubles.
     * @param low: lowest element of the array we should consider while sorting.
     * @param high: highest element of the array we should consider while sorting.
     *
     * O(N^2) worst case/average case, as we are iterating through the whole array in
     * the first for-loop, then the while-loop will check every preceding value to see
     * where to store the value. The best case will be O(N), if the array is already in
     * order, but O(N^2) if the array is in reverse order.
     **/
    private static void insertionSort(double a[], int low, int high)
    {
        double value;
        int  i, j;
        int size = high - low;

        for(i = low + 1; i < size; i++) {
            value = a[i];
            j = i;

            while((j > 0) && (a[j-1] > value)) {
                a[j] = a[j-1];
                j = j - 1;
            }

            a[j] = value;
        }
    }
    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     * Theta(N^2) algorithm. First for-loop will iterate through the array
     * O(N) times. Second for-loop is an aithmetic sequence, which we can consider
     * as O(N^2). Best case, no exchanges take place, as it is already in order.
     * Worst-case, we need to exchange every time (i.e. in reverse order). In both
     * cases, we still need to iterate through the for-loops with O(N^2) as the order
     * of growth. Therefore, our algorithm is Theta(N)
     */
    public static double [] selectionSort (double a[]) {
        int length = a.length;
        int i, j;
        boolean needToExchange = false; // can be used to reduce the number of exchanges

        for(i = 0; i < length-1; i++)
        {
            int minIndex = i;
            for(j = i+1; j < length; j++)
            {
                if(a[j] < a[minIndex]) {
                    minIndex = j;
                    needToExchange = true;
                }
            }

            if(needToExchange)
            {
                double tempValue = a[minIndex];
                a[minIndex] = a[i];
                a[i] = tempValue;
                needToExchange = false;
            }

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
    public static double [] quickSort (double a[]){
        if(a == null) return null;
        int length = a.length;
        if(length <= 1) return a;
        if(length <= 10) return insertionSort(a);   //if length <= 10, cut-off to insertion sort
        randomizeArray(a);      // O(N)
        quickSortRecursive(a, 0, a.length-1);
        return a;

    }//end quicksort

    private static void quickSortRecursive(double a[], int low, int high)
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
            if(iterationValue < pivotValue) exchange(a, i++, lessThan++);
            if(iterationValue > pivotValue) exchange(a, i++, greaterThan--);
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
     * @param original: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    public static double[] mergeSortIterative (double original[]) {
        int arraySize = original.length;
        double temp [] = new double[arraySize];
        double switchArray [];  // this is just a pointer, that we will use to switch 'original' and 'temp'
                                // arrays while calling the merge method. This means we can use the same merge() method
                                // as the recursive version, meaning we don't have to waste any time copying 'original'
                                // into 'temp'. This requires no extra memory, other than the space required for the
                                // pointer itself.

        for(int partSize = 1; partSize < arraySize; partSize = partSize + partSize) {
            // each iteration of this for-loop doubles the size of each
            // section we are merging, meaning that this for-loop will iterate
            // lgN times

            for(int low = 0; low < arraySize - partSize; low += partSize + partSize) {
                merge(original, temp, low, low + partSize - 1, Math.min(low + partSize + partSize - 1, arraySize - 1));
            }

            // switch 'original' and 'temp' for next merge() call
            switchArray = original;
            original = temp;
            temp = switchArray;
        }
        return original;

    }//end mergesortIterative



    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    public static double[] mergeSortRecursive (double a[]) {
        double temp [] = new double[a.length];
        if(a.length == 1 || a.length == 0) return a;
        mergeSortBottomUp(temp, a, 0, a.length-1);
        return a;

    }//end mergeSortRecursive

    private static void mergeSortBottomUp(double original[], double temp[], int low, int high) {
        if(low >= high) return;
        //if(high <= low + 9) insertionSort(original, low, high);     // cutoff to insertion sort for sections
                                                                    // of the array smaller than 10
        int mid = low + (high - low) / 2;
        mergeSortBottomUp(temp, original, low, mid);
        mergeSortBottomUp(temp, original, mid+1, high);
        //if(temp[mid+1] >= temp[mid]) return;                       // if the lowest element of the second half is
                                                                     // greater than the last element of the first half,
                                                                     // there is no need to merge them.
        merge(original, temp, low, mid, high);                       // merge first half with second half.
    }

    private static void merge(double original[], double temp[], int low, int mid, int high) {
        // As we will continuously switch 'original' and 'temp' as the parameters of this method (when calling
        // in both mergeSortRecursive() and mergeSortIterative()), then we do not need to waste time copying
        // the elements of 'original' into the elements of 'temp'
        int i = low, j = mid + 1;
        for(int k = low; k <= high; k++)
        {
            if(i > mid) temp[k] = original[j++];
            else if(j > high) temp[k] = original[i++];
            else if(original[j] < original[i]) temp[k] = original[j++];
            else temp[k] = original[i++];
        }
    }

    public static void main(String[] args) {
        double array [] = {1, 4, 2, 9, 20, 5, 4, 3, 16, 0, 5, 3, 0, 5, 33, 10};
        insertionSort(array);
        double array2 [] = {1, 4, 2, 9, 20, 5, 4, 3, 16, 0, 5, 3, 0, 5, 33, 10};
        selectionSort(array2);
        double array3 [] = {1, 4, 2, 9, 20, 5, 4, 3, 16, 0, 5, 3, 0, 5, 33, 10};
        array3 = mergeSortRecursive(array3);
        double array4 [] = {1, 4, 2, 9, 20, 5, 4, 3, 16, 0, 5, 3, 0, 5, 33, 10};
        array4 = mergeSortIterative(array4);
        //double array5 [] = {1, 4, 2, 9, 20, 5, 4, 3, 16, 0, 5, 3, 0, 5, 33, 10};
        //quickSort(array5);
        System.out.println("done");
        
    }

}//end class

