package map;

class Node<K, V> {
    K key;
    V value;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    Node(K key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof Node<?, ?>)
            return ((Node<?, ?>) obj).key.equals(key);
        else
            return false;
    }
}