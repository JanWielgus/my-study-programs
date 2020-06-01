package GraphPackage;

public class MyUndirectedGraph <V> extends MyDirectedGraph <V>
{
    public MyUndirectedGraph()
    {
        super();
    }


    @Override
    public void addEdge(V source, V destination, float weight)
    {
        // Add edges in both directions
        super.addEdge(source, destination, weight);
        super.addEdge(destination, source, weight);
    }
}
