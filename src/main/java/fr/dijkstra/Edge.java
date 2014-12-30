package fr.dijkstra;

/**
 * Created by clucas on 23/12/2014.
 */
public class Edge {
    private Vertex target;

    private int distance;

    public Edge(Vertex target, int distance) {
        this.target = target;
        this.distance = distance;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
}
