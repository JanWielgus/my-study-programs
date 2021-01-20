package lista11;

import java.util.Random;

public class AccountHolder extends Thread
{
    private BankAccount bankAccount;

    public AccountHolder(String name, BankAccount bankAccount)
    {
        super(name);
        this.bankAccount = bankAccount;
    }

    @Override
    public void run()
    {
        Random rand = new Random();

        for (int i=0; i<10; i++)
        {
            int randNum = rand.nextInt(10) + 1;
            if (rand.nextBoolean())
                bankAccount.deposit(randNum);
            else
                bankAccount.withdraw(randNum);
        }
    }
}
