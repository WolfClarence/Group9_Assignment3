import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class MyHeapTest {
    public static void main(String[] args) {
        final int TEST_SIZE = 1000000;
        Random random = new Random();
        String csvFilename = "heap_test_results.csv";

        try (FileWriter writer = new FileWriter(csvFilename)) {
            writer.append("Heap Type,Execution Time (ms)\n");

            // Min-Heap Test
            writer.append(testHeap(TEST_SIZE, random, new MyHeap<>(Comparator.naturalOrder()), "MyHeap Min-Heap"));
            writer.append(testHeap(TEST_SIZE, random, new PriorityQueue<>(), "PriorityQueue Min-Heap"));

            // Max-Heap Test
            writer.append(testHeap(TEST_SIZE, random, new MyHeap<>(Comparator.reverseOrder()), "MyHeap Max-Heap"));
            writer.append(testHeap(TEST_SIZE, random, new PriorityQueue<>(Comparator.reverseOrder()), "PriorityQueue Max-Heap"));

            System.out.println("Test results written to " + csvFilename);
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

    private static String testHeap(int size, Random random, Object heap, String heapType) {
        long startTime = System.currentTimeMillis();

        if (heap instanceof MyHeap) {
            MyHeap<Integer> myHeap = (MyHeap<Integer>) heap;
            for (int i = 0; i < size; i++) {
                myHeap.offer(random.nextInt());
            }
            for (int i = 0; i < size; i++) {
                myHeap.poll();
            }
        } else if (heap instanceof PriorityQueue) {
            PriorityQueue<Integer> pq = (PriorityQueue<Integer>) heap;
            for (int i = 0; i < size; i++) {
                pq.offer(random.nextInt());
            }
            for (int i = 0; i < size; i++) {
                pq.poll();
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        return heapType + "," + duration + "\n";
    }
}
