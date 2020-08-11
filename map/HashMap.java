package map;

import java.util.LinkedList;
import java.util.ArrayList;

public class HashMap<K, V> {
    int capacity = 64;
    int size = 0;
    ArrayList<LinkedList<Node<K, V>>> list = new ArrayList<>(capacity);

    public HashMap() {
        for (int i = 0; i < capacity; i++)
            list.add(null);
    }

    public void put(final K key, final V value) {
        final int hash = getHash(key);
        if (list.get(hash) == null)
            list.set(hash, new LinkedList<Node<K, V>>());
        final var subList = list.get(hash);
        boolean found = false;
        for (final var node : subList)
            if (node.key.equals(key)) {
                node.value = value;
                found = true;
                break;
            }
        if (!found) {
            subList.add(new Node<K, V>(key, value));
            size++;
        }
    }

    public V get(final K key) {
        final int hash = getHash(key);
        final var subList = list.get(hash);
        if (subList == null)
            return null;
        for (final var node : subList)
            if (node.key.equals(key))
                return node.value;
        return null;
    }

    public void remove(final K key) {
        final int hash = getHash(key);
        final var subList = list.get(hash);
        if (subList != null)
            subList.remove(new Node<K, V>(key));
    }

    public ArrayList<K> getKeys() {
        final var out = new ArrayList<K>();
        for (final var subList : list)
            if (subList != null)
                for (final var node : subList)
                    out.add(node.key);
        return out;
    }

    public boolean contains(final K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    int getHash(final K key) {
        return Math.abs(key.hashCode()) % capacity;
    }
}