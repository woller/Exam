package ConcurrentProgramming.Synchronized;

public class Varelager
{
    public int antalVarer = 0;

    public synchronized void increment()
    {
        while (antalVarer > 4)
        {
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }


        }
        antalVarer++;
        notifyAll();


        System.out.println("Antal steget til: " + antalVarer);
    }

    public synchronized void decrement()
    {
        while (antalVarer < 1)
        {
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }


        }
        antalVarer--;
        notifyAll();

        System.out.println("Antal faldet til: " + antalVarer);
    }

}
