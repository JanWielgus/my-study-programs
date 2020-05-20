package DefaultPackage;

public class MainClass
{

    public static void main(String[] args) {


        MyBinomialHeap<Integer> testHeap1 = new MyBinomialHeap<>();
        MyBinomialHeap<Integer> testHeap2 = new MyBinomialHeap<>();

        testHeap1.insert(5);
        testHeap1.insert(2);
        testHeap1.insert(1);
        testHeap1.insert(100);
        System.out.println(testHeap1.toString());

        testHeap1.decreaseKey(1, -1);
        System.out.println(testHeap1.toString());

        testHeap1.delete(100);
        System.out.println(testHeap1.toString());


        testHeap2.insert(100);
        testHeap2.insert(50);
        testHeap2.insert(14);
        testHeap2.insert(124);
        testHeap2.insert(5);
        testHeap2.insert(73);
        testHeap2.delete(50);
        testHeap2.delete(124);
        System.out.println(testHeap2);
    }
}
