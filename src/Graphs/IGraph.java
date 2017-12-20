package Graphs;

import java.util.List;

public interface IGraph<V>
{
    //returns the number of vertices in a graph
    public int getSize();
    //returns the vertices in the graph
    public List<V> getVertices();
    //returns the vertex object for the specifed vertex index
    public V getVertex(int index);
    //returns the index for the specified vertex
    public int getIndex(V vertex);
    //Returns the neighbors of vertex with the specified index.
    public List<Integer> getNeighbors(int index);
    //Returns the degree for a specified vertex index.
    public int getDegree(int index);
    //Prints the edges.
    public void printEdges();
    //Clears the graph.
    public void clear();
    //Returns true if v is added to the graph. Returns false if v is already in the graph.
    public boolean addVertex(V vertex);
    //Adds an edge from u to v to the graph throws IllegalArgumentException if u or v is invalid. Returns true if the edge is added and false if (u, v) is already in the graph.
    public boolean addEdge(int u, int v);
    //Obtains a depth-first search tree starting from v.
    public AbstractGraph<V>.Tree dfs(int root);
    //Obtains a breadth-first search tree starting from v.
    public AbstractGraph<V>.Tree bfs(int root);
}
