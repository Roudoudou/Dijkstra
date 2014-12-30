package fr.dijkstra;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by clucas on 23/12/2014.
 */
public class Vertex {
    private String name;

    private List<Edge> edges = new ArrayList<Edge>();

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void connectTo(Vertex target, int distance) {
        edges.add(new Edge(target, distance));
    }
}
