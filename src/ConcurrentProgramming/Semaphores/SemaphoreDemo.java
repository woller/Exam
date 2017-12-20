package ConcurrentProgramming.Semaphores;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo
{
    public static void main(String[] args)
    {
        Semaphore semaphore = new Semaphore(1);

        try
        {
            semaphore.acquire();
            //hvis du kommer hertil bliver antal dekrementeret med 1

            semaphore.release();
            //hvis du kommer hertil bliver antal inkrementeret tilbage til 1
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
