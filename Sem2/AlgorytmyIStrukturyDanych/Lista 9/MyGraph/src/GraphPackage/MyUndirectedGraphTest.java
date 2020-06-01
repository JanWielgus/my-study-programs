package GraphPackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void tempTest1()
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

        List<Edge> minEdges = testGraph.getMinimumSpanningTree();

        for (Edge edge: minEdges)
        {
            System.out.println("Src: " + edge.getSource() + " Dest: " + edge.getDestination()
                + " Weight: " + edge.getWeight());
        }
    }
}
