package GraphPackage;


public class Edge <V> implements Comparable<Edge>
{
    public enum DirectionType
    {
        DIRECTED,
        UNDIRECTED
    }

    private V source;
    private V destination;
    private float weight;
    private DirectionType directionType;

    public Edge(V source, V destination, float weight, DirectionType directionType)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.directionType = directionType;
    }

    public V getSource()
    {
        return source;
    }

    public V getDestination()
    {
        return destination;
    }

    public float getWeight()
    {
        return weight;
    }


    @Override
    public int compareTo(Edge o)
    {
        if (getWeight() == o.getWeight())
        {
            boolean temp1 = getSource() == o.getSource() &&
                    getDestination() == o.getDestination();
            boolean temp2 = getSource() == o.getDestination() &&
                    getDestination() == o.getSource();

            if (directionType == DirectionType.DIRECTED)
            {
                if (temp1)
                    return 0;
            }
            else
            {
                if (temp1 || temp2)
                    return 0;
            }
        }

        return getWeight() > o.getWeight() ? 1 : -1;
    }
}
