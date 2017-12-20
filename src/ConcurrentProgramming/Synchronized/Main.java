package ConcurrentProgramming.Synchronized;

public class Main
{
    public static void main(String[] args)
    {
        Varelager varelager = new Varelager();
        Kunde kunde = new Kunde(varelager);
        Leverandor leverandor = new Leverandor(varelager);

        Thread kThread = new Thread(kunde);
        kThread.start();
        Thread lThread = new Thread(leverandor);
        lThread.start();
    }

}
