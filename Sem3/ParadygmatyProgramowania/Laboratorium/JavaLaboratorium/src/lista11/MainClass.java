package lista11;

public class MainClass
{
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(0);
        AccountHolder[] holders = new AccountHolder[5];

        for (int i=0; i < holders.length; i++)
            holders[i] = new AccountHolder("Holder" + (i+1), bankAccount);

        Payer payer = new Payer("Payer", bankAccount);

        for (AccountHolder holder : holders)
            holder.start();
        payer.start();
    }
}
