package GraphPackage;

public class Edge <V>
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
}
