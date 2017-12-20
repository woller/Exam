package Sorting.Slow;

import java.util.Arrays;

public class InsertionSort
{
    public static void main(String[] args)
    {
        int[] ints = {450, 661, 10, 577, 686, 704, 435, 585, 157, 95};
        System.out.println("InsertionSort:");
        System.out.println("Unsorted array: " + Arrays.toString(ints));
        insertionSort(ints);
        System.out.println("Sorted array: " + Arrays.toString(ints));

    }

    private static void insertionSort(int[] ints)
    {
        int i = 1;
        while (i < ints.length)
        {
            int j = i;
            while (j > 0 && ints[j - 1] > ints[j])
            {
                //swap the values
                int temp = ints[j - 1];
                ints[j - 1] = ints[j];
                ints[j] = temp;
                j--;
            }
            i++;
        }
    }
}
