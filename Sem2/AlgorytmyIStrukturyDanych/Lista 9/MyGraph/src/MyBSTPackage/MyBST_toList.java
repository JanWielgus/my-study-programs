package MyBSTPackage;

import java.util.ArrayList;
import java.util.List;

public class MyBST_toList
{
    enum Order
    {
        PRE_ORDER,
        IN_ORDER,
        POST_ORDER
    }

    public static <T> List<T> get(MyBST<T> bst, Order order)
    {
        List<T> result = new ArrayList<>();

        MyBST.Node<T> root = bst.getRoot();
        if (root == null)
            return result;

        if (order == Order.PRE_ORDER)
            fillArrayPreOrderRec(result, root);
        else if (order == Order.IN_ORDER)
            fillArrayInOrderRec(result, root);
        else
            fillArrayPostOrderRec(result, root);

        return result;
    }


    private static <T> void fillArrayPreOrderRec(List<T> list, MyBST.Node<T> node)
    {
        list.add(node.getElement());

        if (node.getLeftChild() != null)
            fillArrayPreOrderRec(list, node.getLeftChild());

        if (node.getRightChild() != null)
            fillArrayPreOrderRec(list, node.getRightChild());
    }


    private static <T> void fillArrayInOrderRec(List<T> list, MyBST.Node<T> node)
    {
        if (node.getLeftChild() != null)
            fillArrayInOrderRec(list, node.getLeftChild());

        list.add(node.getElement());

        if (node.getRightChild() != null)
            fillArrayInOrderRec(list, node.getRightChild());
    }


    private static <T> void fillArrayPostOrderRec(List<T> list, MyBST.Node<T> node)
    {
        if (node.getLeftChild() != null)
            fillArrayPostOrderRec(list, node.getLeftChild());

        if (node.getRightChild() != null)
            fillArrayPostOrderRec(list, node.getRightChild());

        list.add(node.getElement());
    }
}
