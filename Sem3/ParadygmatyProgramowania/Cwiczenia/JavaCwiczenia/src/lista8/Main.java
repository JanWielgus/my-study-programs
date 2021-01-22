package lista8;

// Jan Wielgus

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Tworze pusta kolejke 3 elementow");
        MyQueue<Integer> testQueue = new MyCircularQueue<>(3);

        System.out.println("Czy jest pusta: " + testQueue.isEmpty());
        System.out.println("Czy jest pelna: " + testQueue.isFull());

        System.out.println("Dodaje 3 elementy");
        try {
            testQueue.enqueue(1);
            testQueue.enqueue(2);
            testQueue.enqueue(3);
        } catch (Exception e) {
            System.out.println("Blad implementacji!");
        }

        System.out.println("Czy jest pusta: " + testQueue.isEmpty());
        System.out.println("Czy jest pelna: " + testQueue.isFull());
        try {
            System.out.println("Pierwszy element: " + testQueue.first());
        } catch (Exception e) {
            System.out.println("Blad implementacji!");
        }


        System.out.println("Usuwam dwa elementy z kolejki");
        testQueue.dequeue();
        testQueue.dequeue();

        try {
            System.out.println("Pierwszy element: " + testQueue.first());
        } catch (Exception e) {
            System.out.println("Blad implementacji!");
        }

        System.out.println("Czy jest pusta: " + testQueue.isEmpty());
        System.out.println("Czy jest pelna: " + testQueue.isFull());

        System.out.println("Dodaje 2 elementy");
        try {
            testQueue.enqueue(4);
            testQueue.enqueue(5);
        } catch (Exception e) {
            System.out.println("Blad implementacji!");
        }

        System.out.println("Proba dodania elementu do pelnej kolejki");
        try {
            testQueue.enqueue(6);
        } catch (Exception e) {
            System.out.println("Udana. Wyjatek zostal rzucony");
        }

        System.out.println("Zdejmuje 3 elementy z kolejki");
        testQueue.dequeue();
        testQueue.dequeue();
        testQueue.dequeue();

        System.out.println("Czy jest pusta: " + testQueue.isEmpty());
        System.out.println("Czy jest pelna: " + testQueue.isFull());

        System.out.println("Proba dostepu do pierewszego elementu");
        try {
            testQueue.first();
        } catch (Exception e) {
            System.out.println("Udana. Wyjatek zostal rzucony");
        }
    }
}





