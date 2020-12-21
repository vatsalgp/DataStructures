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

    public void insert(final T val) {
        Root = insert(Root, val);
    }

    private Node<T> insert(final Node<T> node, final T val) {
        if (node == null)
            return new Node<T>(val);
        final int compare = val.compareTo(node.data);
        if (compare < 0)
            node.left = insert(node.left, val);
        else if (compare > 0)
            node.right = insert(node.right, val);
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
