package GraphPackage;

public interface MyGraphInterface <V>
{
    void addVertex(V vertex);
    public void addEdge(V source, V destination,int weight);
}
