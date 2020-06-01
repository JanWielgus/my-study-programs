package GraphPackage;


public class Edge <V> implements Comparable<Edge>
{
    private V source;
    private V destination;
    private float weight;

    public Edge(V source, V destination, float weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
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
        if (getSource() == o.getSource() &&
                getDestination() == o.getDestination() &&
                getWeight() == o.getWeight())
            return 0;

        return getWeight() > o.getWeight() ? 1 : -1;
    }
}
