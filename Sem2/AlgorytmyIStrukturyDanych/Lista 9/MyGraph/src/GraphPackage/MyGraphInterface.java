package GraphPackage;

import java.util.List;

public interface MyGraphInterface <V>
{
    void addVertex(V vertex);
    void addEdge(V source, V destination, float weight);
    Edge<V> getEdge(V sourceVertex, V destinationVertex);
    List<V> getVertexList();
    List<Edge<V>> getEdgeList();
}
