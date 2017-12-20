package ConcurrentProgramming.Problemet;

public class Transaction implements Runnable
{
    private BankAccount account;

    public Transaction(BankAccount account)
    {
        this.account = account;
    }

    @Override
    public void run()
    {
        account.deposit(100);
    }
}
