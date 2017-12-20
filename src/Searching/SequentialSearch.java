package Searching;

public class SequentialSearch
{
    public static void main(String[] args)
    {
        //set up array to be searched
        int[] ints =  {21, 65, 2, 99, 237, 3426, 123, 65, 65156, 6321, 3};

        System.out.println("Index of 99: " + sequentialSearch(99, ints));
    }

    private static int sequentialSearch(int target, int[] ints)
    {
        for (int i = 0; i < ints.length; i++)
        {
            if (ints[i] == target)
            {
                return i;
            }
        }
        return -1;
    }
}
