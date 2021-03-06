package graph;

import java.util.*;

public class Graph {
    private final ArrayList<Vertex> vertices = new ArrayList<Vertex>();

    public void addVertex(final char name) {
        if (!containsVertex(name))
            vertices.add(new Vertex(name));
    }

    public void addEdge(final char v1, final char v2, final int weight) {
        if (weight > 0) {
            addVertex(v1);
            addVertex(v2);
            if (!containsEdge(v1, v2)) {
                vertex(v1).addEdge(v2, weight);
                vertex(v2).addEdge(v1, weight);
            }
        }
    }

    public void removeVertex(final char v) {
        if (containsVertex(v)) {
            for (final var edge : vertex(v).edges)
                removeEdgeHelper(edge.dest, v);
            vertices.remove(new Vertex(v));
        }
    }

    public void removeEdge(final char v1, final char v2) {
        if (containsEdge(v1, v2)) {
            removeEdgeHelper(v1, v2);
            removeEdgeHelper(v2, v1);
        }
    }

    public boolean containsEdge(final char v1, final char v2) {
        return getWeightOfEdge(v1, v2) != -1;
    }

    public boolean containsVertex(final char v) {
        if (getIndex(v) >= vertices.size())
            return false;
        else
            return vertex(v) != null;
    }

    public void printVertices() {
        for (final var vertex : vertices)
            System.out.println(vertex.name);
    }

    public void printEdges() {
        for (final var vertex : vertices)
            for (final var edge : vertex.edges)
                printEdge(vertex.name, edge.dest, edge.weight);
    }

    public void printPath(final char src, char dest) {
        if (containsVertex(src) && containsVertex(dest)) {
            final PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
            final var previous = new HashMap<Character, Character>();
            final var visited = new HashSet<Character>();
            queue.add(new Edge(src, src));
            int weight = 0;
            while (!queue.isEmpty()) {
                final var node = queue.remove();
                final var vertex = node.dest;
                if (!visited.contains(vertex)) {
                    visited.add(vertex);
                    previous.put(vertex, node.src);
                    if (vertex == dest) {
                        weight = node.weight;
                        break;
                    }
                    for (final var edge : vertex(vertex).edges)
                        queue.add(new Edge(vertex, edge.dest, edge.weight + node.weight));
                }
            }
            final Stack<String> stack = new Stack<String>();
            while (dest != src) {
                final char prev = previous.get(dest);
                stack.push(prev + " -> " + dest);
                dest = prev;
            }
            while (!stack.isEmpty())
                System.out.println(stack.pop());
            System.out.println("Total: " + weight);
        }
    }

    public void BFS(char v) {
        final var visited = new HashSet<Character>();
        final Queue<Character> queue = new ArrayDeque<Character>();
        queue.add(v);
        while (!queue.isEmpty()) {
            v = queue.remove();
            if (!visited.contains(v)) {
                visited.add(v);
                System.out.println(v);
                for (final var edge : vertex(v).edges)
                    queue.add(edge.dest);
            }
        }
    }

    public void DFSIterative(char v) {
        final var visited = new HashSet<Character>();
        final Stack<Character> stack = new Stack<Character>();
        stack.add(v);
        while (!stack.isEmpty()) {
            v = stack.pop();
            if (!visited.contains(v)) {
                visited.add(v);
                System.out.println(v);
                for (final var edge : vertex(v).edges)
                    stack.add(edge.dest);
            }
        }
    }

    public void DFSRecursive(final char v) {
        final var visited = new HashSet<Character>();
        DFS(v, visited);
    }

    public void printConnectedParts() {
        final var out = new LinkedList<LinkedList<Character>>();
        final var visited = new HashSet<Character>();
        for (final var vertex : vertices)
            if (!visited.contains(vertex.name))
                out.add(getConnectedParts(vertex.name, visited));
        for (final var list : out) {
            for (final var vertex : list)
                System.out.print(vertex + " ");
            System.out.println();
        }
    }

    public void printHamiltonianPath() {
        final var visited = new HashSet<Character>();
        for (final var vertex : vertices)
            hamiltonian(vertex.name, visited, "");
    }

    private void hamiltonian(final char vertex, final HashSet<Character> visited, final String path) {
        if (!visited.contains(vertex)) {
            visited.add(vertex);
            if (visited.size() == vertices.size())
                System.out.println(path + vertex);
            for (final var edge : vertex(vertex).edges)
                hamiltonian(edge.dest, visited, path + vertex);
            visited.remove(vertex);
        }
    }

    private LinkedList<Character> getConnectedParts(Character v, final HashSet<Character> visited) {
        final var list = new LinkedList<Character>();
        final Queue<Character> queue = new ArrayDeque<Character>();
        queue.add(v);
        while (!queue.isEmpty()) {
            v = queue.remove();
            if (!visited.contains(v)) {
                visited.add(v);
                list.add(v);
                for (final var edge : vertex(v).edges)
                    queue.add(edge.dest);
            }
        }
        return list;
    }

    private void DFS(final char v, final HashSet<Character> visited) {
        if (!visited.contains(v)) {
            visited.add(v);
            System.out.println(v);
            for (final var edge : vertex(v).edges)
                DFS(edge.dest, visited);
        }
    }

    private void printEdge(final char src, final char dest, final int weight) {
        System.out.println(src + " -> " + dest + " @ " + weight);
    }

    private void removeEdgeHelper(final char v1, final char v2) {
        vertex(v1).edges.remove(new Edge(v1, v2));
    }

    private int getWeightOfEdge(final char v1, final char v2) {
        if (!containsVertex(v1))
            return -1;
        if (!containsVertex(v2))
            return -1;
        for (final var edge : vertex(v1).edges)
            if (edge.dest == v2)
                return edge.weight;
        return -1;
    }

    private Vertex vertex(final char v) {
        return vertices.get(getIndex(v));
    }

    private int getIndex(final char v) {
        return v - 'A';
    }
}