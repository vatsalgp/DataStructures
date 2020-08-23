package graph;

class Edge {
    char dest;
    int weight;

    Edge(final char dest, final int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    Edge(final char dest) {
        this(dest, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Edge))
            return false;
        if (o == this)
            return true;
        Edge other = (Edge) o;
        return other.dest == this.dest;
    }
}
