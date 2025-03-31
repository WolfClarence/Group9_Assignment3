import java.io.*;
import java.util.*;

public class Top10Posts {

    /**
     * Reads a CSV file containing post data and extracts the top 10 posts
     * with the highest view counts using a minimum heap (priority queue).
     *
     * @param filename The path to the CSV file containing post data.
     */
    public static void getTop10Posts(String filename) {
        // Using a min heap with a fixed size of 10 to store the top 10 most viewed posts
        PriorityQueue<Post> minHeap = new PriorityQueue<>(10, (a, b) -> a.viewCount - b.viewCount);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine(); // Skip the header row in the CSV file

            // Read each line from the CSV file
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                int postId = Integer.parseInt(st.nextToken().trim());  // Extract postId
                int viewCount = Integer.parseInt(st.nextToken().trim());  // Extract viewCount

                // Insert the current post into the min heap
                if (minHeap.size() < 10) {
                    minHeap.offer(new Post(postId, viewCount));
                } else if (minHeap.peek().viewCount < viewCount) {
                    // If the view count of the current post is higher than the smallest value in the heap,
                    // remove the smallest element and insert the new post
                    minHeap.poll();
                    minHeap.offer(new Post(postId, viewCount));
                }
            }

            // Print the top 10 most viewed posts
            System.out.println("Top 10 Posts (postId, viewCount):");
            while (!minHeap.isEmpty()) {
                Post post = minHeap.poll();
                System.out.println("postId: " + post.postId + ", viewCount: " + post.viewCount);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    /**
     * Represents a post entity that contains a unique post ID and the number of views.
     */
    static class Post {
        int postId;   // Unique identifier for the post
        int viewCount; // Number of times the post has been viewed

        /**
         * Constructor for the Post class.
         *
         * @param postId    The unique identifier of the post.
         * @param viewCount The number of views for the post.
         */
        Post(int postId, int viewCount) {
            this.postId = postId;
            this.viewCount = viewCount;
        }
    }
}
