package ConcurrentProgramming.Synchronized;

import java.util.Random;

public class Kunde implements Runnable
{
    private Varelager varelager;
    private Random r;

    public Kunde(Varelager varelager)
    {
        this.varelager = varelager;
        this.r = new Random();
    }

    @Override
    public void run()
    {
        while (true)
        {
            varelager.decrement();
            try
            {
                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
