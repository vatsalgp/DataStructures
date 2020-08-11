package tree;

public class BST<T extends Comparable<T>> {
    private Node<T> Root;

    public void print() {
        print(Root);
    }

    private void print(final Node<T> node) {
        if (node != null) {
            print(node.left);
            System.out.println(node.data);
            print(node.right);
        }
    }

    public void push(final T val) {
        Root = push(Root, val);
    }

    private Node<T> push(final Node<T> node, final T val) {
        if (node == null)
            return new Node<T>(val);
        final int compare = val.compareTo(node.data);
        if (compare < 0)
            node.left = push(node.left, val);
        else if (compare > 0)
            node.right = push(node.right, val);
        return node;
    }

    public boolean contains(final T val) {
        return contains(Root, val);
    }

    private boolean contains(final Node<T> node, final T val) {
        if (node == null)
            return false;
        final int compare = val.compareTo(node.data);
        if (compare < 0)
            return contains(node.left, val);
        else if (compare > 0)
            return contains(node.right, val);
        else
            return true;
    }
}
