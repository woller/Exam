package Sorting.Slow;

import java.util.Arrays;

public class BubbleSort
{
    public static void main(String[] args)
    {
        //set up array to be sorted
        int[] ints = {450, 661, 10, 577, 686, 704, 435, 585, 157, 95};
        //int[] ints = {4, 2, 1};
        System.out.println("BubbleSort:");
        System.out.println("Unsorted array: " + Arrays.toString(ints));
        bubbleSort(ints);
        System.out.println("Sorted array: " + Arrays.toString(ints));

    }

    private static void bubbleSort(int[] ints)
    {
        int n = ints.length;

        boolean swapped;
        do
        {
            swapped = false;
            for (int i = 1; i < n; i++)
            {
                //if this pair is out of order
                if (ints[i-1] > ints[i])
                {
                    //swap them
                    int temp = ints[i-1];
                    ints[i-1] = ints[i];
                    ints[i] = temp;

                    //set swapped true
                    swapped = true;
                }
            }
        } while (swapped);
    }
}
