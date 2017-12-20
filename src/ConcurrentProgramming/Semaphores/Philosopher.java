package ConcurrentProgramming.Semaphores;

import java.util.Random;

public class Philosopher implements Runnable
{
    private int id;
    private int leftForkIndex;
    private int rightForkIndex;
    private DiningTable table;
    private Random random;

    public Philosopher(int leftForkIndex, int rightForkIndex, DiningTable table, Random random, int id)
    {
        this.leftForkIndex = leftForkIndex;
        this.rightForkIndex = rightForkIndex;
        this.table = table;
        this.random = random;
        this.id = id;
    }

    @Override
    public void run()
    {
        while (true)
        {
            //left fork first approach
            table.pickUpLeft(leftForkIndex);
            table.eat(leftForkIndex, rightForkIndex, id);
            try
            {
                //simulate thinking
                System.out.println("Philosopher " + id + " is thinking");
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

}
