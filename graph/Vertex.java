package graph;

import java.util.LinkedList;

class Vertex {
    char name;
    LinkedList<Edge> edges;

    Vertex(final char name) {
        this.name = name;
        this.edges = new LinkedList<Edge>();
    }

    public void addEdge(final char dest, final int weight) {
        edges.add(new Edge(name, dest, weight));
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Vertex))
            return false;
        if (o == this)
            return true;
        final Vertex other = (Vertex) o;
        return other.name == this.name;
    }
}