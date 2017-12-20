package Sorting.Faster;

import java.util.Arrays;

public class MergeSort
{
    public static void main(String[] args)
    {
        int[] ints = {450, 661, 10, 577, 686, 704, 435, 585, 157, 95};
        System.out.println("InsertionSort:");
        System.out.println("Unsorted array: " + Arrays.toString(ints));
        sort(ints);
        System.out.println("Sorted array: " + Arrays.toString(ints));
    }

    private static void sort(int[] ints)
    {
        int[] helper = new int[ints.length];
        mergeSort(0, ints.length - 1, ints, helper);
    }

    private static void mergeSort(int low, int high, int[] ints, int[] helper)
    {
        //check if low is smaller than high. If not, array is sorted
        if (low < high)
        {
            //get the middle element index
            int middle = low + (high - low) / 2;
            //sort left side of array
            mergeSort(low, middle, ints, helper);
            //sort right side of array
            mergeSort(middle + 1, high, ints, helper);
            //combine them both
            merge(low, middle, high, ints, helper);
        }
    }

    private static void merge(int low, int middle, int high, int[] ints, int[] helper)
    {
        //copy both parts into helper array
        for (int i = low; i <= high; i++)
        {
            helper[i] = ints[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        //copy the smallest values from either the left or the right side back to original array
        while (i <= middle && j <= high)
        {
            if (helper[i] <= helper[j])
            {
                ints[k] = helper[i];
                i++;
            } else
            {
                ints[k] = helper[j];
                j++;
            }
            k++;
        }

        //copy the rest of the left side to the target array
        while (i <= middle)
        {
            ints[k] = helper[i];
            i++;
            k++;
        }
    }
}
