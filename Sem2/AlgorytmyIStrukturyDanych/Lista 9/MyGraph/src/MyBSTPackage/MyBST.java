package MyBSTPackage;

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


    Node<T> getRoot() {
        return root;
    }


    public void addElement(T element)
    {
        if (element == null)
            throw new NullPointerException("Element to add cannot be null");

        if (root == null) // if this is a first element
            root = new Node<>(null, element);
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
                localRoot.leftChild = new Node<>(localRoot, element);
            else
                addElement(element, localRoot.leftChild);
        }

        // Goes to the right subtree
        else
        {
            if (localRoot.rightChild == null)
                localRoot.rightChild = new Node<>(localRoot, element);
            else
                addElement(element, localRoot.rightChild);
        }
    }


    private void addTree(Node<T> treeToAddRoot, Node<T> localRoot)
    {
        // do nothing if this element already exists
        if (treeToAddRoot == localRoot)
            return;

        // Goes to the left subtree
        if (comparator.compare(treeToAddRoot.element, localRoot.element) < 0)
        {
            if (localRoot.leftChild == null)
                localRoot.leftChild = new Node<>(localRoot, element);
            else
                addElement(element, localRoot.leftChild);
        }

        // Goes to the right subtree
        else
        {
            if (localRoot.rightChild == null)
                localRoot.rightChild = new Node<>(localRoot, element);
            else
                addElement(element, localRoot.rightChild);
        }
    }


    public T upper(T element)
    {
        if (root == null)
            return null;

        Node<T> curNode = search(element, root);
        if (curNode == null)
            throw new IllegalArgumentException("Element was not found");

        Node<T> result = upper(curNode);
        return result == null ? null : result.element;
    }


    private Node<T> upper(Node<T> baseNode)
    {
        if (baseNode == null)
            throw new NullPointerException("Base node cannot be null");

        // if right subtree is not null, upper (successor) is the minimum in this subtree
        if (baseNode.rightChild != null)
            return getMinimum(baseNode.rightChild);

        // Find a node, which is left child of it's parent
        Node<T> tempParent = baseNode.parent;
        while (tempParent != null && baseNode == tempParent.rightChild)
        {
            baseNode = tempParent;
            tempParent = tempParent.parent;
        }

        return tempParent;
    }



    public T lower(T element)
    {
        if (root == null)
            return null;

        Node<T> curNode = search(element, root);
        if (curNode == null)
            throw new IllegalArgumentException("Element was not found");

        Node<T> result = lower(curNode);
        return result == null ? null : result.element;
    }


    private Node<T> lower(Node<T> baseNode)
    {
        if (baseNode == null)
            throw new NullPointerException("Base node cannot be null");

        // if has a left child, predecessor (lower) is the minimum in left subtree
        if (baseNode.leftChild != null)
            return getMinimum(baseNode.leftChild);

        // Find a node, which is right child of it's parent
        Node<T> tempParent = baseNode.parent;
        while (tempParent != null && baseNode == tempParent.leftChild)
        {
            baseNode = tempParent;
            tempParent = tempParent.parent;
        }

        return tempParent;

        // TODO: square it away
    }


    public void delete(T element)
    {
        if (element == null)
            throw new NullPointerException("Element to delete cannot be null");

        // Find node with element to delete
        Node<T> nodeToDelete = search(element, root);

        if (nodeToDelete == null)
            throw new IllegalArgumentException("Element to delete was not found");

        delete(nodeToDelete);
    }


    private void delete(Node<T> nodeToDelete)
    {
        if (nodeToDelete == null)
            throw new NullPointerException("Node to delete cannot be null");

        // If this node is leaf, just set proper child node in parent to null
        if (nodeToDelete.isLeaf())
        {
            deleteLeafNode(nodeToDelete);
            return;
        }

        // If has only one child, replace node to delete with this only child
        Node<T> childNode = nodeToDelete.hasOnlyOneChild();
        if (childNode != null) // has only one child
        {
            deleteWithOnlyChild(nodeToDelete, childNode);
            return;
        }

        // If has two children
        deleteWithTwoChildren(nodeToDelete);
    }


    private void deleteLeafNode(Node<T> leafNodeToDelete)
    {
        // if node to delete is root
        if (leafNodeToDelete.parent == null)
            root = null;
        else
        {
            Node<T> localParent = leafNodeToDelete.parent;

            // Set proper child node to null
            if (leafNodeToDelete == localParent.leftChild)
                localParent.leftChild = null;
            else
                localParent.rightChild = null;
        }
    }


    private void deleteWithOnlyChild(Node<T> nodeToDelete, Node<T> childNode)
    {
        // If this is root
        if (nodeToDelete.parent == null)
        {
            root = childNode;
            childNode.parent = null;
            return;
        }

        Node<T> localParent = nodeToDelete.parent;
        childNode.parent = localParent;

        if (nodeToDelete == localParent.leftChild)
            localParent.leftChild = childNode;
        else
            localParent.rightChild = childNode;
    }


    private void deleteWithTwoChildren(Node<T> nodeToDelete)
    {
        // Find the successor
        Node<T> replacingNode = upper(nodeToDelete);

        // test (REMOVE IF WORKING)
        if (replacingNode.leftChild != null && replacingNode.rightChild != null) throw new IllegalStateException("Algorytm dziala zle, nie powinno tego byc");

        // delete the replacing node from the old place (this will only detach it from the tree)
        delete(replacingNode);

        // now only need to change the element inside the deleted node (parent and children remain unchanged)
        nodeToDelete.element = replacingNode.element;
    }


    public T getMinimum()
    {
        return getMinimum(root).element;
    }

    private Node<T> getMinimum(Node<T> localRoot)
    {
        while (localRoot.leftChild != null)
            localRoot = localRoot.leftChild;
        return localRoot;
    }

    public T getMaximum()
    {
        return getMaximum(root).element;
    }

    private Node<T> getMaximum(Node<T> localRoot)
    {
        while (localRoot.rightChild != null)
            localRoot = localRoot.rightChild;
        return localRoot;
    }


    private Node<T> search(T element, Node<T> localRoot)
    {
        if (element == null)
            throw new NullPointerException("Element to search cannot be null");

        // If root is null or root is searched element
        if (localRoot == null || localRoot.element == element)
            return localRoot;

        // Search in the left subtree if searched element is smaller
        if (comparator.compare(element, localRoot.element) < 0)
            return search(element, localRoot.leftChild);
        else
            return search(element, localRoot.rightChild);
    }








    static class Node<T>
    {
        private Node<T> parent;
        private Node<T> rightChild;
        private Node<T> leftChild;
        private T element;


        public Node(Node<T> parent, T element) {
            this.parent = parent;
            this.element = element;
            rightChild = null;
            leftChild = null;
        }

        public Node(Node<T> parent, T element, Node<T> rightChild, Node<T> leftChild) {
            this.parent = parent;
            this.element = element;
            this.rightChild = rightChild;
            this.leftChild = leftChild;
        }

        /*
        public Node(Node<T> initNode)
        {
            this.parent = initNode.parent;
            this.element = initNode.element;
            this.rightChild = initNode.rightChild;
            this.leftChild = initNode.leftChild;
        }*/


        public boolean isLeaf() {
            return (leftChild == null && rightChild == null);
        }


        // Only child if has only one child
        // Null in other way
        public Node<T> hasOnlyOneChild()
        {
            if (isLeaf() || (rightChild != null && leftChild != null))
                return null;
            else if (leftChild != null)
                return leftChild;
            else
                return rightChild;
        }

        public Node<T> getLeftChild() {
            return leftChild;
        }

        public Node<T> getRightChild() {
            return rightChild;
        }

        public T getElement() {
            return element;
        }
    }
}
