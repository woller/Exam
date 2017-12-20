package Graphs;

import java.util.*;

public abstract class AbstractGraph<V> implements IGraph<V>
{
    private List<V> vertices;
    protected HashMap<V, List<Edge>> neighbors;

    public AbstractGraph()
    {
        vertices = new ArrayList<>();
        neighbors = new HashMap<>();
    }

    public AbstractGraph(V[] vertices, HashMap<V, List<Edge>> neighbors)
    {
        this.vertices = Arrays.asList(vertices);
        this.neighbors = neighbors;
    }

    public AbstractGraph(List<V> vertices, HashMap<V, List<Edge>> neighbors)
    {
        this.vertices = vertices;
        this.neighbors = neighbors;
    }

    public AbstractGraph(V[] vertices, int[][] neighbors)
    {
        this.vertices = Arrays.asList(vertices);
        this.neighbors = new HashMap<>();
        for (V vertex : vertices)
        {
            this.neighbors.put(vertex, new ArrayList<>());
        }
        createAdjacencyLists(neighbors, vertices.length);

    }

    public AbstractGraph(List<V> vertices, List<Edge> neighbors)
    {
        this.vertices = vertices;
        this.neighbors = new HashMap<>();
        for (V vertex : vertices)
        {
            this.neighbors.put(vertex, new ArrayList<>());
        }
        createAdjacencyLists(neighbors, vertices.size());
    }

    public AbstractGraph(List<Edge> edges, int numberOfVertices)
    {
        for (int i = 0; i < numberOfVertices; i++)
        {
            addVertex((V) new Integer(i));
        }
        createAdjacencyLists(edges, numberOfVertices);

    }

    public HashMap<V, List<Edge>> getNeighborsHashMap()
    {
        return neighbors;
    }

    /** Create adjacency lists for each vertex - hapset direkte fra bogen - s. 1029*/
    private void createAdjacencyLists(int[][] edges, int numberOfVertices)
    {
        for (int i = 0; i < edges.length; i++)
        {
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    private void createAdjacencyLists(List<Edge> neighbors, int numberOfVertices)
    {
        for (Edge edge : neighbors)
        {
            addEdge(edge);
        }
    }

    public boolean addEdge(Edge edge)
    {
        return (addEdge(edge.u, edge.v));
    }

    @Override
    public int getSize()
    {
        return vertices.size();
    }

    @Override
    public List<V> getVertices()
    {
        return vertices;
    }

    @Override
    public V getVertex(int index)
    {
        if (index >= 0 && index < vertices.size())
        {
            return vertices.get(index);
        }
        return null;
    }

    @Override
    public int getIndex(V vertex)
    {
        return vertices.indexOf(vertex);
    }

    @Override
    public List<Integer> getNeighbors(int index)
    {
        List<Integer> list = new ArrayList<>();
        List<Edge> edgeList = neighbors.get(getVertex(index));
        for (Edge edge : edgeList)
        {
            list.add(edge.v);
        }
        return list;
    }

    @Override
    public int getDegree(int index)
    {
        return neighbors.get(getVertex(index)).size();
    }

    @Override
    public void printEdges()
    {
        for (Map.Entry<V, List<Edge>> vertexListEntry : neighbors.entrySet())
        {
            for (Edge edge : vertexListEntry.getValue())
            {
                System.out.println(getVertex(edge.u) + " to " + getVertex(edge.v));
            }
        }

    }

    @Override
    public void clear()
    {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addVertex(V vertex)
    {
        if (!vertices.contains(vertex))
        {
            vertices.add(vertex);
            neighbors.put(vertex, new ArrayList<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(int u, int v)
    {
        V vertex = getVertex(u);
        List<Edge> edgeList = neighbors.get(vertex);
        Edge edge = new Edge(u, v);
        if (!edgeList.contains(edge))
        {
            edgeList.add(edge);
            return true;
        }
        return false;
    }

    @Override
    public Tree dfs(int root)
    {
        //prepare everything
        int[] parent = new int[vertices.size()];
        for (int i : parent)
        {
            i = -1;
        }
        boolean[] isVisited = new boolean[vertices.size()];
        List<Integer> searchOrder = new ArrayList<>();

        dfs(root, parent, isVisited, searchOrder);

        return new Tree(root, searchOrder, parent);
    }

    private void dfs(int root, int[] parent, boolean[] isVisited, List<Integer> searchOrder)
    {
        isVisited[root] = true;
        searchOrder.add(root);

        for (Integer neighbor : getNeighbors(root))
        {
            if (!isVisited[neighbor])
            {
                parent[neighbor] = root;
                dfs(neighbor, parent, isVisited, searchOrder);
            }
        }
    }

    @Override
    public Tree bfs(int root)
    {
        //prepare everything
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i : parent)
        {
            i = -1;
        }
        boolean[] isVisited = new boolean[vertices.size()];

        //create an empty queue for storing vertices to be visited - using LinkedList as a queue
        LinkedList<Integer> queue = new LinkedList<>();
        //add v into the queue and into the searchOrder list
        queue.offer(root); //enqueueing root
        isVisited[root] = true;


        while (!queue.isEmpty())
        {
            int u = queue.poll(); //deque to u
            searchOrder.add(u);
            for (Integer w : getNeighbors(u))
            {
                if (!isVisited[w])
                {
                    queue.addLast(w);
                    parent[w] = u;
                    isVisited[w] = true;
                }
            }
        }

        return new Tree(root, searchOrder, parent);
    }

    public class Tree
    {
        int root;
        List<Integer> searchOrder;
        int[] parent;

        public Tree(int root, List<Integer> searchOrder, int[] parent)
        {
            this.root = root;
            this.searchOrder = searchOrder;
            this.parent = parent;
        }

        public int getRoot()
        {
            return root;
        }

        public int getParent(int v)
        {
            return parent[v];
        }

        public List<Integer> getSearchOrder()
        {
            return searchOrder;
        }

        public int getNumberOfVerticesFound()
        {
            return searchOrder.size();
        }

        //return the path of vertices from a vertex to the root
        public List<V> getPath(int index)
        {
            ArrayList<V> path = new ArrayList<>();

            do
            {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);

            return path;
        }

        //print path from root to a vertex
        public void printPath(int index)
        {
            List<V> path = getPath(index);
            System.out.println("A path from " + vertices.get(root) + " to " + vertices.get(index));
            for (int i = path.size(); i >= 0; i--)
            {
                System.out.println(path.get(i) + " ");
            }
        }

        //print the whole tree
        public void printTree()
        {
            System.out.println("Root is: " + vertices.get(root));
            System.out.println("Edges: ");
            for (int i = 0; i < parent.length; i++)
            {
                if (parent[i] != -1)
                {
                    //Display an edge
                    System.out.println("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }
}
