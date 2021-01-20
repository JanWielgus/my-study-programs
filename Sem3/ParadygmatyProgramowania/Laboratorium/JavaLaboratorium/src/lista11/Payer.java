package lista11;

import java.util.Random;

public class Payer extends Thread
{
    private BankAccount bankAccount;

    public Payer(String name, BankAccount bankAccount)
    {
        super(name);
        this.bankAccount = bankAccount;
        setDaemon(true);
    }

    @Override
    public void run()
    {
        Random rand = new Random();

        while (true)
        {
            try {
                bankAccount.deposit(rand.nextInt(3) + 1);
                sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }
}
