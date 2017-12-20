package ConcurrentProgramming.Problemet;

public class BankAccount
{
    private int balance;

    public BankAccount(int balance)
    {
        this.balance = balance;
    }

    //tilføj synchronized
    public void deposit(int amount)
    {
        int temp = balance;
        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        temp += amount;
        balance = temp;
    }

    @Override
    public String toString()
    {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}
