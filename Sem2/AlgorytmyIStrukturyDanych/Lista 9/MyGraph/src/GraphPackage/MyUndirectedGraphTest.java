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
        expectedEdges.add(new Edge<>(2, 3, 4, Edge.DirectionType.UNDIRECTED));
        expectedEdges.add(new Edge<>(0, 3, 5, Edge.DirectionType.UNDIRECTED));
        expectedEdges.add(new Edge<>(0, 1, 10, Edge.DirectionType.UNDIRECTED));

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
        expectedEdges.add(new Edge<>("d", "g", 5, Edge.DirectionType.UNDIRECTED));
        expectedEdges.add(new Edge<>("a", "d", 2, Edge.DirectionType.UNDIRECTED));
        expectedEdges.add(new Edge<>("a", "e", 3, Edge.DirectionType.UNDIRECTED));
        expectedEdges.add(new Edge<>("b", "e", 3, Edge.DirectionType.UNDIRECTED));
        expectedEdges.add(new Edge<>("e", "h", 1, Edge.DirectionType.UNDIRECTED));
        expectedEdges.add(new Edge<>("b", "c", 2, Edge.DirectionType.UNDIRECTED));
        expectedEdges.add(new Edge<>("f", "h", 7, Edge.DirectionType.UNDIRECTED));


        // check if all edges are in the mst result list
        for (Edge<String> edge: expectedEdges)
            Assertions.assertTrue(contain(mstEdgeList, edge));
    }


    @Test
    void shortestPathsFromOneSourceTest1()
    {
        MyUndirectedGraph<String> stringGraph = new MyUndirectedGraph<>();

        stringGraph.addVertex("a");
        stringGraph.addVertex("b");
        stringGraph.addVertex("c");
        stringGraph.addVertex("d");
        stringGraph.addVertex("e");
        stringGraph.addVertex("f");
        stringGraph.addVertex("g");
        stringGraph.addVertex("h");

        stringGraph.addEdge("a", "b", 2);
        stringGraph.addEdge("a", "c", 5);
        stringGraph.addEdge("b", "d", 3);
        stringGraph.addEdge("b", "e", 4);
        stringGraph.addEdge("c", "d", 5);
        stringGraph.addEdge("c", "f", 6);
        stringGraph.addEdge("d", "e", 3);
        stringGraph.addEdge("d", "f", 1);
        stringGraph.addEdge("e", "f", 4);
        stringGraph.addEdge("e", "g", 8);
        stringGraph.addEdge("e", "h", 2);
        stringGraph.addEdge("f", "g", 7);
        stringGraph.addEdge("g", "h", 1);


        List<Path<String>> shortestPathsList = stringGraph.getShortestPathsFromSource("a");


        for (Path<String> path: shortestPathsList)
        {
            System.out.println("Src: " + path.getSource() +
                    " Dest: " + path.getDestination() +
                    " Len: " + path.getLength());
        }
    }







    <V> boolean contain(List<Edge<V>> list, Edge<V> edge)
    {
        for (Edge<V> curEdge: list)
            if (edge.compareTo(curEdge) == 0)
                return true;
        return false;
    }

}