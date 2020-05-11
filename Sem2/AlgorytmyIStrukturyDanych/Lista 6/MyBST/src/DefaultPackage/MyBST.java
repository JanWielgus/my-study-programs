package DefaultPackage;

import java.util.Comparator;

public class MyBST<T>
{
    private Node<T> root;
    private final Comparator<T> comparator;


    public MyBST(Comparator<T> comparator)
    {
        root = null;
        this.comparator = comparator;
    }


    public void addElement(T element)
    {
        if (root == null) // if this is a first element
            root = new Node<>(element);
        else
            addElement(element, root); // private recurrent method
    }

    private void addElement(T element, Node<T> localRoot)
    {
        // do nothing if this element already exists
        if (element == localRoot.element)
            return;

        // Goes to the left subtree
        if (comparator.compare(element, localRoot.element) < 0)
        {
            if (localRoot.leftChild == null)
                localRoot.leftChild = new Node<>(element);
            else
                addElement(element, localRoot.leftChild);
        }

        // Goes to the right subtree
        else
        {
            if (localRoot.rightChild == null)
                localRoot.rightChild = new Node<>(element);
            else
                addElement(element, localRoot.rightChild);
        }
    }


    public T upper(T element)
    {
        Node<T> curNode = search(element, root);

        // if right is not null, then successor is min in the right subtree
        if (curNode.rightChild != null)
            return minimum(curNode.rightChild);

        // if right is null, start from root and search for successor
        Node<T> successor = null;
        Node<T> localRoot = root;

        while (localRoot != null)
        {
            int compResult = comparator.compare(element, localRoot.element);
            if (compResult < 0)
            {
                successor = localRoot;
                localRoot = localRoot.leftChild;
            }
            else if (compResult > 0)
                localRoot = localRoot.rightChild;
            else
                break;
        }

        return successor.element;
    }

    public T lower(T element)
    {
        Node<T> curNode = search(element, root);

        // if has a left child, left
        // TODO: square it away
    }


    public void delete(T element)
    {
        if (element == null)
            throw new NullPointerException("Element to delete is null");

        // Find node with element to delete
        Node<T> nodeToDelete = search(element, root);

        if (nodeToDelete.isLeaf())
            
    }


    public T minimum()
    {
        return minimum(root);
    }

    private T minimum(Node<T> localRoot)
    {
        Node<T> current = localRoot;

        while (current.leftChild != null)
            current = current.leftChild;

        return current.element;
    }

    private Node<T> search(T element, Node<T> localRoot)
    {
        // If root is null or root is searched element
        if (localRoot == null || localRoot.element == element)
            return localRoot;

        // Search in the left subtree if searched element is smaller
        if (comparator.compare(element, localRoot.element) < 0)
            return search(element, localRoot.leftChild);
        else
            return search(element, localRoot.rightChild);
    }








    private class Node<T>
    {
        private Node<T> rightChild;
        private Node<T> leftChild;
        private T element;


        public Node(T element) {
            this.element = element;
            rightChild = null;
            leftChild = null;
        }

        public Node(T element, Node<T> rightChild, Node<T> leftChild) {
            set(element, rightChild, leftChild);
        }

        public void set(T element, Node<T> rightChild, Node<T> leftChild) {
            setElement(element);
            setRightChild(rightChild);
            setLeftChild(leftChild);
        }

        public void setElement(T element) {
            this.element = element;
        }

        public void setRightChild(Node<T> rightChild) {
            this.rightChild = rightChild;
        }

        public void setLeftChild(Node<T> leftChild) {
            this.leftChild = leftChild;
        }

        public T getElement() {
            return element;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public boolean isLeaf() {
            return (leftChild == null && rightChild == null);
        }

    }
}
