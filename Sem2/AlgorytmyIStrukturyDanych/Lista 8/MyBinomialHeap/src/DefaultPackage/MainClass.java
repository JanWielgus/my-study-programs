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
    }
}
