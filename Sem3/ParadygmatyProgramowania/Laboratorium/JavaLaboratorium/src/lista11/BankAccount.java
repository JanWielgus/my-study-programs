package lista11;

public class BankAccount
{
    private int balance;

    public BankAccount (int initialBalance) {
        balance = initialBalance;
    }

    public synchronized int deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposits: " + amount
                + " current balance is: " + balance);
        notifyAll();
        return balance;
    }

    public synchronized int withdraw(int amount) {

        if (balance < amount)
        {
            System.out.println(Thread.currentThread().getName() + " waiting - not enough money: (needs:"
                    + amount + ", balance:" + balance + ")");

            while (balance < amount) {
                try { wait(); } catch (InterruptedException e) {}
            }
        }

        balance -= amount;
        System.out.println(Thread.currentThread().getName() + " withdraws: "+ amount
                + " current balance is " + balance);
        return balance;
    }

}
