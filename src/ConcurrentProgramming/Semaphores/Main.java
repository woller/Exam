package ConcurrentProgramming.Semaphores;

import java.util.ArrayList;
import java.util.Random;

/**
 Five silent philosophers sit at a round table with bowls of spaghetti. Forks are placed between
 each pair of adjacent philosophers. Each philosopher must alternately think and eat. However, a
 philosopher can only eat spaghetti when they have both left and right forks. Each fork can be
 held by only one philosopher and so a philosopher can use the fork only if it is not being used
 by another philosopher. After an individual philosopher finishes eating, they need to put down
 both forks so that the forks become available to others.  A philosopher can take the fork on
 their right or the one on their left as they become available, but cannot start eating before
 getting both forks. Eating is not limited by the remaining amounts of spaghetti or stomach
 space; an infinite supply and an infinite demand are assumed. The problem is how to design a
 discipline of behavior (a concurrent algorithm) such that no philosopher will starve; i.e.,
 each can forever continue to alternate between eating and thinking, assuming that no philosopher
 can know when others may want to eat or think.
 **/

public class Main
{
    public static void main(String[] args)
    {
        //set the table
        DiningTable table = new DiningTable();

        Random random = new Random();

        ArrayList<Philosopher> philosophers = new ArrayList<>();

        //invite the philosophers
        for (int i = 0; i < 5; i++)
        {
            if (i != 4)
            {
                Philosopher philosopher = new Philosopher(i, i + 1, table, random, i + 1);
                philosophers.add(philosopher);
                Thread thread = new Thread(philosopher);
                thread.start();
            } else
            {
                Philosopher philosopher = new Philosopher(0, 4, table, random, i + 1);
                philosophers.add(philosopher);
                Thread thread = new Thread(philosopher);
                thread.start();
            }

        }
    }
}
