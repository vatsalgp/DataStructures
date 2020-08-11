package tree;

class Node<T extends Comparable<T>> {
    T data;
    Node<T> left;
    Node<T> right;

    Node(final T data) {
        this.data = data;
    }
}
