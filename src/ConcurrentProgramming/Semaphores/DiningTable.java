package ConcurrentProgramming.Semaphores;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class DiningTable
{
    private ArrayList<Semaphore> forks;

    public DiningTable()
    {
        forks = new ArrayList<>();

        for (int i = 0; i < 5; i++)
        {
            Semaphore semaphore = new Semaphore(1);
            forks.add(semaphore);
        }
    }

    public void eat(int leftForkIndex, int rightForkIndex, int philosopherID)
    {
        try
        {
            forks.get(rightForkIndex).acquire();
            //simulate eating time
            System.out.println("Philosopher " + philosopherID + " is now eating");
            Thread.sleep(1000);
            forks.get(rightForkIndex).release();
            forks.get(leftForkIndex).release();

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void pickUpLeft(int leftForkIndex)
    {
        try
        {
            forks.get(leftForkIndex).acquire();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
