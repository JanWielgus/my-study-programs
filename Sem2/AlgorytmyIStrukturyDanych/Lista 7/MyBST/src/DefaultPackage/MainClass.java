package DefaultPackage;

import java.util.Comparator;

public class MainClass
{
    public static void main(String[] args) {


        MyBST<Integer> bst = new MyBST<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        bst.addElement(7);
        bst.addElement(5);
        bst.addElement(3);
        bst.addElement(10);
        bst.addElement(8);
        bst.addElement(12);

        System.out.println(MyBST_toList.get(bst, MyBST_toList.Order.IN_ORDER));
        //System.out.println(bst.getMaximum());
    }
}
