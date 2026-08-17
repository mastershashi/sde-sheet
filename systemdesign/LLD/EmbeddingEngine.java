package systemdesign.LLD;
import java.util.*;

public class EmbeddingEngine {
    private final int targetDimension;

    public EmbeddingEngine(int targetDimension) {
        this.targetDimension = targetDimension;
    }

    /**
     * Converts a raw text chunk into a deterministic, normalized geometric vector.
     * Algorithmic Strategy: Tokenization -> Term Frequency Computation -> Feature Hashing -> L2 Normalization
     */
    public double[] convertChunkToVector(String textChunk) {
        double[] vector = new double[targetDimension];
        
        if (textChunk == null || textChunk.trim().isEmpty()) {
            return vector; // Return a zero-filled vector for safety
        }

        // 1. Tokenize text using clean regex boundaries & normalize case
        String[] tokens = textChunk.toLowerCase()
                .replaceAll("[^a-zA-Z0-9\\s]", "")
                .split("\\s+");

        // 2. Calculate Term Frequencies (TF) using a frequency map
        Map<String, Integer> termCounts = new HashMap<>();
        for (String token : tokens) {
            if (!token.isEmpty()) {
                termCounts.put(token, termCounts.getOrDefault(token, 0) + 1);
            }
        }

        // 3. Feature Hashing Trick (Maps infinite vocabulary to fixed target dimensions)
        for (Map.Entry<String, Integer> entry : termCounts.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();

            // Compute raw TF weight (using log normalization to prevent long-tail document explosions)
            double tfWeight = 1.0 + Math.log(count);

            // Deterministically distribute the string's hash into our target array indexes
            int index = Math.abs(word.hashCode()) % targetDimension;
            
            // Derive a sign property (+1 or -1) from a secondary hash function to reduce structural collisions
            double sign = (Integer.rotateLeft(word.hashCode(), 13) % 2 == 0) ? 1.0 : -1.0;

            vector[index] += (tfWeight * sign);
        }

        // 4. L2 Normalization pass (Enforces Euclidean Vector Magnitude to equal exactly 1.0)
        // Crucial for Cosine Similarity evaluations downstream.
        double sumOfSquares = 0.0;
        for (double val : vector) {
            sumOfSquares += val * val;
        }
        
        double magnitude = Math.sqrt(sumOfSquares);
        if (magnitude > 0.0) {
            for (int i = 0; i < targetDimension; i++) {
                vector[i] /= magnitude;
            }
        }

        return vector;
    }

    // --- Verification Runner ---
    public static void main(String[] args) {
        int dimension = 1536;
        EmbeddingEngine engine = new EmbeddingEngine(dimension);

        String chunkA = "Microservice architectures distribute components across isolated cloud virtual instances.";
        String chunkB = "Monolithic applications retain unified bounded contexts inside one system execution loop.";

        double[] vectorA = engine.convertChunkToVector(chunkA);
        double[] vectorB = engine.convertChunkToVector(chunkB);

        System.out.println("--- Algorithmic Vector Generation ---");
        System.out.println("Vector A Dimensions: " + vectorA.length);
        System.out.printf("Vector A Sample Vectors: [%.4f, %.4f, %.4f]\n", vectorA[0], vectorA[1], vectorA[2]);
        System.out.println("\nVector B Dimensions: " + vectorB.length);
        System.out.printf("Vector B Sample Vectors: [%.4f, %.4f, %.4f]\n", vectorB[0], vectorB[1], vectorB[2]);
        System.out.println("--- Non-Zero Elements in Vector A ---");
for (int i = 0; i < vectorA.length; i++) {
    if (vectorA[i] != 0.0) {
        System.out.printf("Index [%d] = %.4f\n", i, vectorA[i]);
    }
}
    }
}