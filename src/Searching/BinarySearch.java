package Searching;

public class BinarySearch
{
    public static void main(String[] args)
    {
        //set up array to be searched
        int[] ints =  {1, 13, 27, 31, 32, 47, 55, 68, 76, 87, 99}; //length = 11

        System.out.println("Index of 99: " + binarySearch(68, ints));
    }

    private static int binarySearch(int target, int[] ints)
    {
        return binaryHelper(ints, target, 0, ints.length - 1);
    }

    private static int binaryHelper(int[] ints, int target, int low, int high)
    {
        //early return, if element is not in array
        if (high < low)
        {
            return -1;
        }

        //find middle index
        int mid = (low + high) / 2;
        System.out.println(mid);
        //hvis ints[mid] > target må korrekt værdi ligge første halvdel af arrayet
        if (ints[mid] > target)
        {
            return binaryHelper(ints, target, low, mid - 1);
        } else if (ints[mid] < target)
        {
            return binaryHelper(ints, target, mid + 1, high);
        }
        else
        {
            return mid;
        }
    }
}
