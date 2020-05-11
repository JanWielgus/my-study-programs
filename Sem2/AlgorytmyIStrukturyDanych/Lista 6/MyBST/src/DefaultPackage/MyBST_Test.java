package DefaultPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Comparator;
import java.util.List;

public class MyBST_Test
{
    private MyBST<Integer> bst;

    @BeforeEach
    void beforeEach()
    {
        bst = new MyBST<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

    }


    @Test
    void preOrderShowTest()
    {
        bst.addElement(7);
        bst.addElement(5);
        bst.addElement(3);
        bst.addElement(10);
        bst.addElement(8);
        bst.addElement(12);

        List<Integer> list = MyBST_toList.get(bst, MyBST_toList.Order.PRE_ORDER);

        Assertions.assertArrayEquals(new Integer[] {7, 5, 3, 10, 8, 12}, list.toArray());
    }


    @Test
    void inOrderShowTest()
    {
        bst.addElement(7);
        bst.addElement(5);
        bst.addElement(3);
        bst.addElement(10);
        bst.addElement(8);
        bst.addElement(12);

        List<Integer> list = MyBST_toList.get(bst, MyBST_toList.Order.IN_ORDER);

        Assertions.assertArrayEquals(new Integer[] {3, 5, 7, 8, 10, 12}, list.toArray());
    }


    @Test
    void postOrderShowTest()
    {
        bst.addElement(7);
        bst.addElement(5);
        bst.addElement(3);
        bst.addElement(10);
        bst.addElement(8);
        bst.addElement(12);

        List<Integer> list = MyBST_toList.get(bst, MyBST_toList.Order.POST_ORDER);

        Assertions.assertArrayEquals(new Integer[] {3, 5, 8, 12, 10, 7}, list.toArray());
    }


    @Test
    void maxMinTest()
    {
        bst.addElement(7);
        bst.addElement(5);
        bst.addElement(3);
        bst.addElement(10);
        bst.addElement(8);
        bst.addElement(12);
        Assertions.assertEquals(3, bst.getMinimum());
        Assertions.assertEquals(12, bst.getMaximum());

        bst.addElement(15);
        bst.addElement(13);
        bst.addElement(1);
        bst.addElement(2);
        Assertions.assertEquals(1, bst.getMinimum());
        Assertions.assertEquals(15, bst.getMaximum());
    }


    @Test
    void upperTest()
    {
        bst.addElement(7);
        bst.addElement(10);
        bst.addElement(6);
        bst.addElement(8);
        bst.addElement(3);
        bst.addElement(12);
        bst.addElement(9);
        bst.addElement(4);
        bst.addElement(5);

        Assertions.assertNull(bst.upper(12));
        Assertions.assertEquals(8, bst.upper(7));
        Assertions.assertEquals(6, bst.upper(5));
    }


    @Test
    void leafDeleteTest()
    {
        bst.addElement(7);
        bst.addElement(10);
        bst.addElement(6);
        bst.addElement(8);
        bst.addElement(3);
        bst.addElement(12);
        bst.addElement(9);
        bst.addElement(4);
        bst.addElement(5);

        bst.delete(5);

        List<Integer> list = MyBST_toList.get(bst, MyBST_toList.Order.PRE_ORDER);
        Assertions.assertArrayEquals(new Integer[] {7, 6, 3, 4, 10, 8, 9, 12}, list.toArray());

        bst.delete(12);
        list = MyBST_toList.get(bst, MyBST_toList.Order.PRE_ORDER);
        Assertions.assertArrayEquals(new Integer[] {7, 6, 3, 4, 10, 8, 9}, list.toArray());
    }


    @Test
    void deleteWithOneChildTest()
    {
        bst.addElement(7);
        bst.addElement(10);
        bst.addElement(6);
        bst.addElement(8);
        bst.addElement(3);
        bst.addElement(12);
        bst.addElement(9);
        bst.addElement(4);
        bst.addElement(5);

        bst.delete(3);

        List<Integer> list = MyBST_toList.get(bst, MyBST_toList.Order.PRE_ORDER);
        Assertions.assertArrayEquals(new Integer[] {7, 6, 4, 5, 10, 8, 9, 12}, list.toArray());

        bst.delete(8);
        list = MyBST_toList.get(bst, MyBST_toList.Order.PRE_ORDER);
        Assertions.assertArrayEquals(new Integer[] {7, 6, 4, 5, 10, 9, 12}, list.toArray());
    }


    @Test
    void deleteWithTwoChildrenTest()
    {
        bst.addElement(7);
        bst.addElement(10);
        bst.addElement(6);
        bst.addElement(8);
        bst.addElement(3);
        bst.addElement(12);
        bst.addElement(9);
        bst.addElement(4);
        bst.addElement(5);

        bst.delete(7);

        List<Integer> list = MyBST_toList.get(bst, MyBST_toList.Order.PRE_ORDER);
        Assertions.assertArrayEquals(new Integer[] {8, 6, 3, 4, 5, 10, 9, 12}, list.toArray());
    }


    @Test
    void deleteEverythingTest()
    {
        bst.addElement(7);
        bst.addElement(10);
        bst.addElement(6);
        bst.addElement(8);
        bst.addElement(3);
        bst.addElement(12);
        bst.addElement(9);
        bst.addElement(4);
        bst.addElement(5);

        bst.delete(3);
        System.out.println("usuniete: 3");
        bst.delete(10);
        System.out.println("usuniete: 10");
        bst.delete(12);
        System.out.println("usuniete: 12");
        bst.delete(9);
        System.out.println("usuniete: 9");
        bst.delete(8);
        System.out.println("usuniete: 8");
        bst.delete(4);
        System.out.println("usuniete: 4");

        System.out.println(MyBST_toList.get(bst, MyBST_toList.Order.IN_ORDER));

        bst.delete(7);
        System.out.println("usuniete: 7");


        Assertions.assertEquals(6, bst.upper(5));

        List<Integer> list = MyBST_toList.get(bst, MyBST_toList.Order.PRE_ORDER);
        Assertions.assertArrayEquals(new Integer[] {6, 5}, list.toArray());
    }


}
