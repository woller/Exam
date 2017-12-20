package Graphs;

import java.util.HashMap;
import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V>
{
    public UnweightedGraph(V[] vertices, HashMap<V, List<Edge>> neighbors)
    {
        super(vertices, neighbors);
    }

    public UnweightedGraph(List<V> vertices, HashMap<V, List<Edge>> neighbors)
    {
        super(vertices, neighbors);
    }

    public UnweightedGraph(V[] vertices, int[][] neighbors)
    {
        super(vertices, neighbors);
    }

    public UnweightedGraph(List<V> vertices, List<Edge> neighbors)
    {
        super(vertices, neighbors);
    }

    public UnweightedGraph()
    {
        super();
    }

    public UnweightedGraph(List<Edge> edges, int numberOfVertices)
    {
        super(edges, numberOfVertices);
    }
}
