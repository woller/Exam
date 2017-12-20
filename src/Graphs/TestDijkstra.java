package Graphs;

public class TestDijkstra
{
    public static void main(String[] args)
    {

        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("Los Angeles");
        graph.addVertex("Denver");
        graph.addVertex("Chicago");
        graph.addVertex("Kansas City");
        graph.addEdge (new Edge(0,1, 1015));
        graph.addEdge (new Edge(1,0, 1015));
        graph.addEdge (new Edge(1,2, 1003));
        graph.addEdge (new Edge(2,1, 1003));
        graph.addEdge (new Edge(2,3, 533));
        graph.addEdge (new Edge(3,2, 533));
        graph.addEdge (new Edge(3,1, 1));
        graph.addEdge (new Edge(1,3, 1));
        graph.addEdge (new Edge(0,3, 1663));
        graph.addEdge (new Edge(3,0,1663));

        System.out.println(graph.dijkstraSearch(0, 2));

    }
}
