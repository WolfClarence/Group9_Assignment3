public class Top10PostsTest {
    //Use the pre-generated dataset to test, which contains 10000 lines of data
    public static void main(String[] args) {
        String filename = "posts.csv"; // Path to the CSV file
        long startTime = System.currentTimeMillis(); // Start time for execution measurement
        Top10Posts.getTop10Posts(filename);
        long endTime = System.currentTimeMillis(); // End time for execution measurement
        System.out.println("Time taken: " + (endTime - startTime) + " ms"); // Print execution time
    }
}
