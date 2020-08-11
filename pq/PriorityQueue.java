package pq;

import java.util.ArrayList;

public class PriorityQueue<T extends Comparable<T>> {
    private final ArrayList<T> queue = new ArrayList<T>(32);

    public void add(final T ele) {
        if (ele != null) {
            queue.add(ele);
            levelUp(queue.size() - 1);
        }
    }

    public T remove() {
        final T ele = peek();
        final int last = queue.size() - 1;
        final T temp = queue.get(last);
        queue.set(0, temp);
        queue.set(last, ele);
        queue.remove(last);
        levelDown(0);
        return ele;
    }

    public T peek() {
        return queue.get(0);
    }

    public boolean contains(final T ele) {
        return queue.contains(ele);
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    private void levelUp(final int childIndex) {
        final int parentIndex = (childIndex - 1) / 2;
        if (parentIndex != childIndex) {
            final T parent = queue.get(parentIndex);
            final T child = queue.get(childIndex);
            if (child.compareTo(parent) < 0) {
                queue.set(parentIndex, child);
                queue.set(childIndex, parent);
                levelUp(parentIndex);
            }
        }
    }

    private void levelDown(final int parentIndex) {
        final int childIndex = getChildIndex(parentIndex);
        if (childIndex > 0) {
            final T parent = queue.get(parentIndex);
            final T child = queue.get(childIndex);
            if (child.compareTo(parent) < 0) {
                queue.set(parentIndex, child);
                queue.set(childIndex, parent);
                levelDown(childIndex);
            }
        }
    }

    private int getChildIndex(final int parentIndex) {
        final int leftChildIndex = 2 * parentIndex + 1;
        final int rightChildIndex = 2 * parentIndex + 2;
        final int last = queue.size() - 1;
        if (leftChildIndex > last)
            return -1;
        if (rightChildIndex > last)
            return leftChildIndex;
        if (queue.get(leftChildIndex).compareTo(queue.get(rightChildIndex)) < 0)
            return leftChildIndex;
        return rightChildIndex;
    }
}