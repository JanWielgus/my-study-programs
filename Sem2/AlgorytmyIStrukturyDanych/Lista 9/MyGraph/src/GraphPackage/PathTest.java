package GraphPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PathTest
{
    Path<String> testPath;

    @BeforeEach
    void beforeEach()
    {
        testPath = new Path<>();

        testPath.setSource("a");
        testPath.addEdge(new Edge<>("a", "b", 5, Edge.DirectionType.DIRECTED));
        testPath.addEdge(new Edge<>("c", "b", 5, Edge.DirectionType.UNDIRECTED));
        testPath.addEdge(new Edge<>("c", "d", 10, Edge.DirectionType.DIRECTED));
    }


    @Test
    void addNotMatchingEdgeExceptionTest1()
    {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            testPath.addEdge(new Edge<>("g", "h", 123, Edge.DirectionType.UNDIRECTED));
        });

        Assertions.assertThrows(IllegalStateException.class, () -> {
            testPath.addEdge(new Edge<>("e", "d", 2, Edge.DirectionType.DIRECTED));
        });

        Assertions.assertDoesNotThrow(() -> {
            testPath.addEdge(new Edge<>("e", "d", 2, Edge.DirectionType.UNDIRECTED));
        });
    }


    @Test
    void interfaceTest1()
    {
        Assertions.assertEquals(20, testPath.getLength());
        Assertions.assertEquals("a", testPath.getSource());
        testPath.addEdge(new Edge<>("e", "d", 2, Edge.DirectionType.UNDIRECTED));
        Assertions.assertEquals("e", testPath.getDestination());
    }


    @Test
    void edgeListTest1()
    {
        List<Edge<String>> expectedEdgeList = new ArrayList<>();
        expectedEdgeList.add(new Edge<>("a", "b", 5, Edge.DirectionType.DIRECTED));
        expectedEdgeList.add(new Edge<>("b", "c", 5, Edge.DirectionType.DIRECTED));
        expectedEdgeList.add(new Edge<>("c", "d", 10, Edge.DirectionType.DIRECTED));

        List<Edge<String>> actualEdgeList = testPath.getEdgeList();
        for (int i=0; i<actualEdgeList.size(); i++)
            Assertions.assertEquals(0, expectedEdgeList.get(i).compareTo(actualEdgeList.get(i)));
    }
}
