package ConcurrentProgramming.Problemet;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        //setup
        BankAccount account = new BankAccount(0);

        ArrayList<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            list.add(new Thread(new Transaction(account)));
        }

        for (Thread thread : list)
        {
            thread.start();
        }

        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(account);

    }
}
