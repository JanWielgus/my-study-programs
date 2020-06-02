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

    public Path(V source, List<Edge<V>> edgeList)
    {
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
        destination = null;
        length = 0;
        edgeList.clear();

        for (Edge<V> edge: edgeList)
            addEdge(edge);
    }

    public void setSource(V source)
    {
        this.source = source;
    }


    // Add source vertex before the first edge
    public void addEdge(Edge<V> edge)
    {
        // Prevent from adding edge to path without source
        if (source == null)
            throw new IllegalStateException("There is no source vertex");

        boolean result = false;

        // If this is the first edge
        if (destination == null)
        {
            // Check destination conditions

            if (edge.getDirectionType() == Edge.DirectionType.DIRECTED)
            {
                // have to match to this source
                if (edge.getSource().compareTo(this.source) == 0)
                {
                    this.destination = edge.getDestination();
                    edgeList.add(edge);
                    length += edge.getWeight();
                }
                else
                    throw new IllegalStateException("Given edge has different source than this path");
            }
            else // undirected edge
            {
                // Any vertex can match source

                if (edge.getSource().compareTo(this.source) == 0)
                    this.destination = edge.getDestination();
                else if (edge.getDestination().compareTo(this.source) == 0)
                    this.destination = edge.getSource();

                edgeList.add(edge);
                length += edge.getWeight();
            }
        }

        // If this is not the first edge
        else // destination != null
        {
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
                    this.destination = edge.getSource();

                edgeList.add(edge);
                length += edge.getWeight();
            }
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
