package GraphPackage;

import java.util.ArrayList;
import java.util.List;

public class Path <V extends Comparable<V>>
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

    public Path(V source)
    {
        edgeList = new ArrayList<>();

        setSource(source);
    }

    public Path(V source, List<Edge<V>> edgeList)
    {
        if (source == null)
            throw new NullPointerException("Source vertex cannot be null");
        if (edgeList == null)
            throw new NullPointerException("Edge list cannot be null");
        if (edgeList.size() == 0)
            throw new IllegalArgumentException("Edge list have to contain at least one edge. Check other constructors");

        setPath(source, edgeList);
    }


    // Edge list have to be consistent
    public void setPath(V source, List<Edge<V>> edgeList)
    {
        if (source == null || destination == null)
            throw new NullPointerException("Source and destination cannot be null");
        if (edgeList == null)
            throw new NullPointerException("Edge list cannot be null");
        if (edgeList.size() == 0)
            throw new IllegalStateException("Edge list have to contain at least ove edge");


        setSource(source);
        destination = source;
        length = 0;
        edgeList.clear();

        for (Edge<V> edge: edgeList)
            addEdge(edge);
    }


    public void setPath(Path<V> path)
    {
        this.edgeList = new ArrayList<>(path.edgeList);
        this.length = path.length;
        this.source = path.source;
        this.destination = path.destination;
    }

    public void setSource(V source)
    {
        edgeList.clear();
        length = 0;

        this.source = source;
        this.destination = source; // zero length path
    }


    // Add source vertex before the first edge
    public void addEdge(Edge<V> edge)
    {
        // Prevent from adding edge to path without source
        if (source == null)
            throw new IllegalStateException("There is no source vertex");

        if (edge == null)
            throw new NullPointerException("Passed edge cannot be null");


        if (edge.getDirectionType() == Edge.DirectionType.DIRECTED)
        {
            // edge source have to match to previous destination
            if (this.destination.compareTo(edge.getSource()) == 0)
            {
                this.destination = edge.getDestination();
                edgeList.add(edge);
                length += edge.getWeight();
            }
            else
                throw new IllegalStateException("Given edge has different source than last destination");
        }
        else // undirected
        {
            // edge source or destination can match to last destination
            if (edge.getSource().compareTo(this.destination) == 0)
                this.destination = edge.getDestination();
            else if (edge.getDestination().compareTo(this.destination) == 0)
            {
                this.destination = edge.getSource();
                edge.reverse(); // swap source and destination
            }
            else
                throw new IllegalStateException("Given edge do not match to the path destination");

            edgeList.add(edge);
            length += edge.getWeight();
        }
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
