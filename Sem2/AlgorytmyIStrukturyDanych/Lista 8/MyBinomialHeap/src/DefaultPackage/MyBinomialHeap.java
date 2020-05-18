package DefaultPackage;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;

public class MyBinomialHeap <T extends Comparable<? super T>>
{
    private ArrayList<Node> roots;




    public MyBinomialHeap()
    {
        roots = new ArrayList<>();
    }


    public void insert(T value)
    {
        Node newNode = new Node(value);

        // add new node on the index 0
        roots.add(0, newNode);

        // merges all trees with the same degree
        repairRootsArray();
    }


    public T minimum()
    {
        return minimumNode().data;
    }


    public T extractMin()
    {
        Node minNode = minimumNode();
        T elementToReturn = minNode.data;

        // remove minimum element from the heap
        removeRootNode(minNode);

        return elementToReturn;
    }


    public void union(MyBinomialHeap<T> heap2)
    {
        int lastID = 0;

        // Put all roots to this roots array in ascending order
        for (Node node: heap2.roots)
        {
            // find place
            while (lastID < roots.size() && roots.get(lastID).degree <= node.degree)
                lastID++;

            roots.add(node);
            lastID++;
        }


        // if there are any roots with the same degree, merges them
        repairRootsArray();
    }


    public void decreaseKey(T element, T smallerElement)
    {
        if (smallerElement.compareTo(element) >= 0)
            throw new IllegalArgumentException("Passed element should be smaller than previous one");

        Node elementNode = null;

        // find node with that element in all root nodes
        for (Node node: roots)
        {
            elementNode = findElementInNode(node, element);
            if (elementNode != null)
                break;
        }

        // If such element was not found
        if (elementNode == null)
            throw new IllegalArgumentException("There is no such element in heap");

        elementNode.setData(smallerElement);

        // bubble up
        while (elementNode.parent != null && elementNode.parent.data.compareTo(elementNode.data) > 0)
        {
            elementNode.parent.swap(elementNode);
            elementNode = elementNode.parent;
        }
    }


    public void delete(T valueToDelete)
    {
        if (valueToDelete == null)
            throw new NullPointerException("Value to delete cannot be null");

        // find node with that element
        Node elementNode = null;

        // find node with that element in all root nodes
        for (Node node: roots)
        {
            elementNode = findElementInNode(node, valueToDelete);
            if (elementNode != null)
                break;
        }

        // If such element was not found
        if (elementNode == null)
            throw new IllegalArgumentException("There is no such element in heap");

        // Move this value to the top
        while (elementNode.parent != null)
        {
            elementNode.parent.swap(elementNode);
            elementNode = elementNode.parent;
        }

        // Remove node from root
        removeRootNode(elementNode);
    }


    public String toString()
    {
        String result = "";
        for (Node node: roots)
            result += " " + node.toString();
        return result;
    }




    // Merge two nodes and return merged node
    private Node mergeNodes(Node node1, Node node2)
    {
        if (node1 == null || node2 == null)
            throw new NullPointerException("Node 1 or Node 2 are null pointers");

        // If node1 will be root
        if (node1.data.compareTo(node2.data) <= 0)
        {
            node2.parent = node1;
            node1.addChild(node2);
            node1.increaseDegree();
            return node1;
        }
        else
        {
            node1.parent = node2;
            node2.addChild(node1);
            node2.increaseDegree();
            return node2;
        }
    }

    // merges all roots with the same degree from roots array
    // all nodes have to be sorted in the ascending order
    private void repairRootsArray()
    {
        // check if there are some trees with the same degree
        for (int i=1; i<roots.size(); i++)
        {
            if (roots.get(i-1).degree == roots.get(i).degree)
            {
                Node merged = mergeNodes(roots.get(i-1), roots.get(i));
                roots.set(i-1, merged);
                roots.remove(i);
                i--;
            }
        }
    }


    private Node findElementInNode(Node localRoot, T data)
    {
        if (localRoot == null)
            throw new NullPointerException("Root to find element in is null");
        if (data == null)
            throw new NullPointerException("Data to find is null");

        if (data == localRoot.data)
            return localRoot;
        else
        {
            // search in children nodes
            for (Node node: localRoot.children)
            {
                Node temp = findElementInNode(node, data);
                if (temp != null)
                    return temp;
            }
        }

        // return null if not found
        return null;
    }

    private void removeRootNode(Node node)
    {
        // remove node from root array
        roots.remove(node);

        // promote all children to the root array
        // Put them in roots array in ascending order
        int lastID = 0;
        for (Node child: node.getChildrenList())
        {
            // find place
            while (lastID < roots.size() && roots.get(lastID).degree <= node.degree)
                lastID++;

            roots.add(child);
            child.parent = null;
            lastID++;
        }

        // merge trees if there more than one of each degree
        repairRootsArray();
    }


    private Node minimumNode()
    {
        if (roots.size() == 0)
            throw new IllegalStateException("Heap is empty and has no minimum element");

        // Assume that fist in array is the lowest
        Node minNode = roots.get(0);

        for (int i=1; i<roots.size(); i++)
        {
            if (roots.get(i).data.compareTo(minNode.data) < 0)
                minNode = roots.get(i);
        }

        return minNode;
    }







    class Node
    {
        private Node parent;
        private ArrayList<Node> children;
        private T data;
        private int degree; // num of children


        public Node(T data)
        {
            this.parent = null;
            children = new ArrayList<>();
            this.data = data;
            degree = 0;
        }

        public void setData(T data)
        {
            this.data = data;
        }

        public T getData()
        {
            return this.data;
        }

        public void addChild(Node child)
        {
            this.children.add(child);
            child.parent = this;
            degree += child.degree;
        }

        public ArrayList<Node> getChildrenList()
        {
            return children;
        }

        public void swap(Node nodeToSwap)
        {
            if (nodeToSwap == null)
                throw new NullPointerException("Node to swap is null");

            T temp = data;
            data = nodeToSwap.getData();
            nodeToSwap.setData(temp);
        }

        public void increaseDegree()
        {
            degree++;
        }

        public String toString()
        {
            String result = "";
            result += " " + data.toString();
            for (Node child: children)
                result += " " + child.toString();
            return result;
        }
    }




}
