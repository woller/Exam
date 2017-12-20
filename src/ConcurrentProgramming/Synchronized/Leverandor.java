package ConcurrentProgramming.Synchronized;

import java.util.Random;

public class Leverandor implements Runnable
{
    private Varelager varelager;
    private Random r;

    public Leverandor(Varelager varelager)
    {
        this.varelager = varelager;
        this.r = new Random();
    }

    @Override
    public void run()
    {
        while (true)
        {
            varelager.increment();
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
