package graph;

class Edge implements Comparable<Edge> {
    char src;
    char dest;
    int weight;

    Edge(final char src, final char dest, final int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    Edge(final char src, final char dest) {
        this(src, dest, 1);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Edge))
            return false;
        if (o == this)
            return true;
        final Edge other = (Edge) o;
        return other.dest == this.dest && other.src == this.src;
    }

    @Override
    public int compareTo(final Edge o) {
        return this.weight - o.weight;
    }
}
