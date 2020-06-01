package GraphPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyUndirectedGraphTest
{
    private MyUndirectedGraph<Integer> testGraph;

    @BeforeEach
    void beforeEach()
    {
        testGraph = new MyUndirectedGraph<>();
    }


    @Test
    void minSpanningTreeTest1()
    {
        testGraph.addVertex(0);
        testGraph.addVertex(1);
        testGraph.addVertex(2);
        testGraph.addVertex(3);

        testGraph.addEdge(0, 1, 10);
        testGraph.addEdge(0, 2, 6);
        testGraph.addEdge(0, 3, 5);
        testGraph.addEdge(1, 3, 15);
        testGraph.addEdge(2, 3, 4);

        List<Edge<Integer>> mstEdges = testGraph.getMinimumSpanningTree();

        // Create list of edges that should occur
        List<Edge<Integer>> expectedEdges = new ArrayList<>();
        expectedEdges.add(new Edge<>(2, 3, 4));
        expectedEdges.add(new Edge<>(0, 3, 5));
        expectedEdges.add(new Edge<>(0, 1, 10));

        // check if all edges are in the mst result list
        for (Edge<Integer> edge: expectedEdges)
            Assertions.assertTrue(contain(mstEdges, edge));

        /*
        for (Edge edge: mstEdges)
        {
            System.out.println("Src: " + edge.getSource() + " Dest: " + edge.getDestination()
                + " Weight: " + edge.getWeight());
        }*/
    }


    @Test
    void minSpanningTreeTest2()
    {
        MyUndirectedGraph<String> secondTestGraph = new MyUndirectedGraph<>();

        secondTestGraph.addVertex("a");
        secondTestGraph.addVertex("b");
        secondTestGraph.addVertex("c");
        secondTestGraph.addVertex("d");
        secondTestGraph.addVertex("e");
        secondTestGraph.addVertex("f");
        secondTestGraph.addVertex("g");
        secondTestGraph.addVertex("h");

        secondTestGraph.addEdge("a", "b", 4);
        secondTestGraph.addEdge("a", "d", 2);
        secondTestGraph.addEdge("a", "e", 3);
        secondTestGraph.addEdge("b", "e", 3);
        secondTestGraph.addEdge("b", "h", 4);
        secondTestGraph.addEdge("b", "f", 8);
        secondTestGraph.addEdge("b", "c", 2);
        secondTestGraph.addEdge("c", "f", 9);
        secondTestGraph.addEdge("d", "g", 5);
        secondTestGraph.addEdge("e", "g", 5);
        secondTestGraph.addEdge("e", "h", 1);
        secondTestGraph.addEdge("f", "h", 7);
        secondTestGraph.addEdge("g", "h", 6);

        List<Edge<String>> mstEdgeList = secondTestGraph.getMinimumSpanningTree();

        // Create list of edges that should occur
        List<Edge<String>> expectedEdges = new ArrayList<>();
        expectedEdges.add(new Edge<>("g", "d", 5));
        expectedEdges.add(new Edge<>("a", "d", 2));
        expectedEdges.add(new Edge<>("a", "e", 3));
        expectedEdges.add(new Edge<>("b", "e", 3));
        expectedEdges.add(new Edge<>("e", "h", 1));
        expectedEdges.add(new Edge<>("b", "c", 2));
        expectedEdges.add(new Edge<>("f", "h", 7));


        // check if all edges are in the mst result list
        for (Edge<String> edge: expectedEdges)
            Assertions.assertTrue(contain(mstEdgeList, edge));
    }




    <V> boolean contain(List<Edge<V>> list, Edge<V> edge)
    {
        for (Edge<V> curEdge: list)
            if (edge.compareTo(curEdge) == 0)
                return true;
        return false;
    }

}
