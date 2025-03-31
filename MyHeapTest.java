import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class MyHeapTest {
    public static void main(String[] args) {
        final int TEST_SIZE = 1000000; // Number of test operations (insertions and deletions)
        Random random = new Random(); // Random number generator for generating test data
        String csvFilename = "heap_test_results.csv"; // Output CSV file to store test results

        try (FileWriter writer = new FileWriter(csvFilename)) { // FileWriter to write results to CSV
            writer.append("Heap Type,Execution Time (ms)\n"); // CSV header for heap type and execution time

            // Min-Heap Test
            writer.append(testHeap(TEST_SIZE, random, new MyHeap<>(Comparator.naturalOrder()), "MyHeap Min-Heap"));
            writer.append(testHeap(TEST_SIZE, random, new PriorityQueue<>(), "PriorityQueue Min-Heap"));

            // Max-Heap Test
            writer.append(testHeap(TEST_SIZE, random, new MyHeap<>(Comparator.reverseOrder()), "MyHeap Max-Heap"));
            writer.append(testHeap(TEST_SIZE, random, new PriorityQueue<>(Comparator.reverseOrder()), "PriorityQueue Max-Heap"));

            System.out.println("Test results written to " + csvFilename); // Notify that the results have been written
        } catch (IOException e) { // Handle IO exceptions
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

    /**
     * This method tests heap operations (offer and poll) on a given heap implementation.
     * It performs the operations on the given heap and records the execution time.
     *
     * @param size the number of operations to perform
     * @param random the random number generator to generate test data
     * @param heap the heap implementation (either MyHeap or PriorityQueue)
     * @param heapType a string representing the type of heap (for CSV logging)
     * @return a CSV-formatted string containing the heap type and execution time
     */
    private static String testHeap(int size, Random random, Object heap, String heapType) {
        long startTime = System.currentTimeMillis(); // Record the start time of the test

        // Check if the heap is of type MyHeap and perform operations
        if (heap instanceof MyHeap) {
            MyHeap<Integer> myHeap = (MyHeap<Integer>) heap;
            for (int i = 0; i < size; i++) { // Insert elements into the heap
                myHeap.offer(random.nextInt());
            }
            for (int i = 0; i < size; i++) { // Remove elements from the heap
                myHeap.poll();
            }
        }
        // Check if the heap is of type PriorityQueue and perform operations
        else if (heap instanceof PriorityQueue) {
            PriorityQueue<Integer> pq = (PriorityQueue<Integer>) heap;
            for (int i = 0; i < size; i++) { // Insert elements into the priority queue
                pq.offer(random.nextInt());
            }
            for (int i = 0; i < size; i++) { // Remove elements from the priority queue
                pq.poll();
            }
        }

        long endTime = System.currentTimeMillis(); // Record the end time of the test
        long duration = endTime - startTime; // Calculate the execution time
        return heapType + "," + duration + "\n"; // Return a CSV string with heap type and execution time
    }
}
