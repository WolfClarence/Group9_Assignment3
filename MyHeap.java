import java.util.*;

/**
 * A custom implementation of a binary heap (priority queue) that mimics Java's PriorityQueue.
 * This heap supports generic types and requires a Comparator to define element ordering.
 *
 * @param <T> the type of elements stored in the heap, which must be comparable via the provided Comparator.
 */
public class MyHeap<T> {
    private final List<T> heap; // Internal list representing the heap structure.
    private final Comparator<? super T> comparator; // Comparator to define the ordering of elements.

    /**
     * Constructs a new empty heap with the specified comparator.
     *
     * @param comparator the comparator that defines the order of elements in the heap.
     */
    public MyHeap(Comparator<? super T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * Inserts a new element into the heap while maintaining the heap structure.
     *
     * @param element the element to be added to the heap.
     */
    public void offer(T element) {
        heap.add(element);
        siftUp(heap.size() - 1); // Restore heap property by moving the new element up.
    }

    /**
     * Retrieves and removes the root element (smallest or largest, depending on comparator) from the heap.
     *
     * @return the root element of the heap, or null if the heap is empty.
     */
    public T poll() {
        if (heap.isEmpty()) return null;
        T result = heap.get(0);
        T lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            siftDown(0); // Restore heap property by moving the new root element down.
        }
        return result;
    }

    /**
     * Retrieves, but does not remove, the root element of the heap.
     *
     * @return the root element of the heap, or null if the heap is empty.
     */
    public T peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return the current size of the heap.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Restores the heap property by moving an element up the tree if necessary.
     * This method is called when a new element is added.
     *
     * @param index the index of the element to sift up.
     */
    private void siftUp(int index) {
        T element = heap.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T parentElement = heap.get(parentIndex);
            if (comparator.compare(element, parentElement) >= 0) break;
            heap.set(index, parentElement);  // Move the parent element down.
            index = parentIndex;
        }
        heap.set(index, element);  // Place the element at the correct position.
    }

    /**
     * Restores the heap property by moving an element down the tree if necessary.
     * This method is called when the root element is removed.
     *
     * @param index the index of the element to sift down.
     */
    private void siftDown(int index) {
        T element = heap.get(index);
        int size = heap.size();
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            // Compare with left child
            if (leftChild < size && comparator.compare(heap.get(leftChild), element) < 0) {
                smallest = leftChild;
            }

            // Compare with right child
            if (rightChild < size && comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0) {
                smallest = rightChild;
            }

            // If the current index holds the smallest value, stop sifting down.
            if (smallest == index) break;

            heap.set(index, heap.get(smallest));  // Move the smallest child up.
            index = smallest;
        }
        heap.set(index, element);  // Place the element at the correct position.
    }
}
