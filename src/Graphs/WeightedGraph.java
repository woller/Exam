package Graphs;

import java.util.*;

public class WeightedGraph<V> extends AbstractGraph<V>
{
    public WeightedGraph()
    {
        super();
    }

    public WeightedGraph(V[] vertices, HashMap<V, List<Edge>> neighbors)
    {
        super(vertices, neighbors);
    }

    public WeightedGraph(List<V> vertices, HashMap<V, List<Edge>> neighbors)
    {
        super(vertices, neighbors);
    }

    public WeightedGraph(V[] vertices, int[][] neighbors)
    {
        super(vertices, neighbors);
    }

    public WeightedGraph(List<V> vertices, List<Edge> neighbors)
    {
        super(vertices, neighbors);
    }

    @Override
    public boolean addEdge(Edge edge)
    {
        return addEdge(edge.u, edge.v, edge.weight);
    }

    private boolean addEdge(int u, int v, int weight)
    {
        V vertex = getVertex(u);
        List<Edge> edgeList = neighbors.get(vertex);
        Edge edge = new Edge(u, v, weight);
        if (!edgeList.contains(edge))
        {
            edgeList.add(edge);
            return true;
        }
        return false;
    }


    public Stack<V> dijkstraSearch(int source, int target)
    {
        List<V> vertices = getVertices();

        Set<Integer> q = new HashSet<>();
        int[] dist = new int[vertices.size()];
        int[] prev = new int[vertices.size()];

        for (int i = 0; i < vertices.size(); i++)
        {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            q.add(i);
        }

        dist[source] = 0;

        while (!q.isEmpty())
        {
            int u = findLeastDistanceVertex(q, dist);
            q.remove(u);

            for (Integer v : getNeighbors(u))
            {
                if (q.contains(v))
                {
                    int alt = dist[u] + getEdgeFromUtoV(u, v).weight;
                    if (alt < dist[v])
                    {
                        dist[v] = alt;
                        prev[v] = u;
                    }

                }
            }
        }

        for (int i = 0; i < dist.length; i++)
        {
            System.out.println("distance fra source til vertex i: " + i + " dist: " + dist[i]);
        }

        return getShortestPath(prev, target);
    }

    private Stack<V> getShortestPath(int[] prev, int target)
    {
        Stack<V> shortestPath = new Stack<>();

        shortestPath.push(getVertex(target));
        while (prev[target] > -1) //kunne også laves som for-loop - så ville man slippe for at skulle lave linjen før while-loopet
        {
            target = prev[target];
            shortestPath.push(getVertex(target));
        }

        return shortestPath;
    }

    private Edge getEdgeFromUtoV(int u, int v)
    {
        List<Edge> edgeList = getNeighborsHashMap().get(getVertex(u));
        for (Edge edge : edgeList)
        {
            if (edge.u == u && edge.v == v)
            {
                System.out.println("edge weight: " + edge.weight);
                return edge;
            }
        }
        return null;
    }

    private int findLeastDistanceVertex(Set<Integer> q, int[] dist)
    {
        int lowest = Integer.MAX_VALUE;
        int index = -1;
        for (Integer vertex : q)
        {
            int j = dist[vertex];
            if (j < lowest)
            {
                lowest = j;
                index = vertex;
            }
        }
        return index;
    }

}
