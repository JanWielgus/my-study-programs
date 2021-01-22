package lista11.zadanie4;

// Jan Wielgus


import java.util.concurrent.Semaphore;

class Philosopher extends Thread
{
    private Semaphore leftChopstick;
    private Semaphore rightChopstick;
    private Semaphore diningRoom;
    private int ID;

    private static int lastID = 1;

    public Philosopher(Semaphore leftStick, Semaphore rightStick, Semaphore diningRoom)
    {
        this.leftChopstick = leftStick;
        this.rightChopstick = rightStick;
        this.diningRoom = diningRoom;
        ID = lastID++;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            try {
                diningRoom.acquire();
                printID();
                println("Entered the dining room");

                leftChopstick.acquire();
                printID();
                println("Picked left chopstick");

                rightChopstick.acquire();
                printID();
                println("Picked right chopstick");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                printID();
                println("Putting left chopstick back");
                leftChopstick.release();

                printID();
                println("Putting right chopstick back");
                rightChopstick.release();

                printID();
                println("Leaving dining room");
                diningRoom.release();
            }
        }
    }

    public void printID()
    {
        System.out.print("Ph. ID: " + ID + " - ");
    }

    public static void println(String text)
    {
        System.out.println(text);
    }
}



class Main {
    public static void main(String[] args) {
        Semaphore diningRoomDoorman = new Semaphore(4, true);

        Semaphore[] chopsticks = new Semaphore[5];
        for (int i = 0; i < chopsticks.length; i++)
            chopsticks[i] = new Semaphore(1, true);

        Philosopher[] philosophers = new Philosopher[5];
        philosophers[0] = new Philosopher(chopsticks[4], chopsticks[0], diningRoomDoorman);
        for (int i = 1; i < philosophers.length; i++)
            philosophers[i] = new Philosopher(chopsticks[i - 1], chopsticks[i], diningRoomDoorman);

        for (Philosopher ph : philosophers)
            ph.start();

        try {
            for (Philosopher ph : philosophers)
                ph.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}