package Graphs;

public class Edge
{
    //these two ints are the indices of the edge's vertices
    public int u;
    public int v;
    public int weight;

    public Edge(int u, int v)
    {
        this.u = u;
        this.v = v;
    }

    public Edge(int u, int v, int weight)
    {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public boolean equals(Object o)
    {
        return u == ((Edge) o).u && v == ((Edge) o).v && weight == ((Edge) o).weight;
    }
}
