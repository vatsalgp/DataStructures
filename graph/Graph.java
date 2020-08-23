package graph;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();

    public void addVertex(char name) {
        if (!containsVertex(name))
            vertices.add(new Vertex(name));
    }

    public void addEdge(char v1, char v2, int weight) {
        addVertex(v1);
        addVertex(v2);
        if (!containsEdge(v1, v2)) {
            vertex(v1).addEdge(v2, weight);
            vertex(v2).addEdge(v1, weight);
        }
    }

    public void removeVertex(char v) {
        if (containsVertex(v)) {
            for (var edge : vertex(v).edges)
                removeEdgeHelper(edge.dest, v);
            vertices.remove(new Vertex(v));
        }
    }

    public void removeEdge(char v1, char v2) {
        if (containsEdge(v1, v2)) {
            removeEdgeHelper(v1, v2);
            removeEdgeHelper(v2, v1);
        }
    }

    public boolean containsEdge(char v1, char v2) {
        return getWeightOfEdge(v1, v2) != -1;
    }

    public boolean containsVertex(char v) {
        if (getIndex(v) >= vertices.size())
            return false;
        else
            return vertex(v) != null;
    }

    public void printVertices() {
        for (var vertex : vertices)
            System.out.println(vertex.name);
    }

    public void printEdges() {
        for (var vertex : vertices)
            for (var edge : vertex.edges)
                System.out.println(vertex.name + " -> " + edge.dest + " @ " + edge.weight);
    }

    private void removeEdgeHelper(char v1, char v2) {
        vertex(v1).edges.remove(new Edge(v2));
    }

    private int getWeightOfEdge(char v1, char v2) {
        if (!containsVertex(v1))
            return -1;
        if (!containsVertex(v2))
            return -1;
        for (var edge : vertex(v1).edges)
            if (edge.dest == v2)
                return edge.weight;
        return -1;
    }

    private Vertex vertex(char v) {
        return vertices.get(getIndex(v));
    }

    private int getIndex(char v) {
        return v - 'A';
    }

}