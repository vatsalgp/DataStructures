package graph;

import java.util.LinkedList;

class Vertex {
    char name;
    LinkedList<Edge> edges;

    Vertex(final char name) {
        this.name = name;
        this.edges = new LinkedList<Edge>();
    }

    public void addEdge(final char vertex, final int weight) {
        edges.add(new Edge(vertex, weight));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Vertex))
            return false;
        if (o == this)
            return true;
        Vertex other = (Vertex) o;
        return other.name == this.name;
    }
}