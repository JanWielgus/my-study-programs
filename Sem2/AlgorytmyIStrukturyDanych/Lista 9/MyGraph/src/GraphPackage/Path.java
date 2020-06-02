package GraphPackage;

import java.util.ArrayList;
import java.util.List;

public class Path <V>
{
    private List<Edge<V>> edgeList;
    float length;
    V source;
    V destination;

    public Path()
    {
        edgeList = new ArrayList<>();
        length = 0;
        source = null;
        destination = null;
    }

    public Path(V source, V destination, List<Edge<V>> edgeList)
    {
        setupPath(source, destination, edgeList);
    }

    public void setupPath(V source, V destination, List<Edge<V>> edgeList)
    {
        this.source = source;
        this.destination = destination;
        this.edgeList = edgeList;

        // calculate total length
        length = 0;
        for (Edge<V> edge: edgeList)
            length += edge.getWeight();
    }


    public float getLength()
    {
        return length;
    }

    public V getSource()
    {
        return source;
    }

    public V getDestination()
    {
        return destination;
    }

    public List<Edge<V>> getEdgeList()
    {
        return edgeList;
    }
}
