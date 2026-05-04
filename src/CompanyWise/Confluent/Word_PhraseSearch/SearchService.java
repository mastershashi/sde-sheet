package CompanyWise.Confluent.Word_PhraseSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchService {
    private InvertedIndex index;

    public SearchService(InvertedIndex index) {
        this.index = index;
    }

    // Single word search
    public Set<Integer> searchWord(String word) {
        word = normalize(word);
        if (!index.index.containsKey(word))
            return new HashSet<>();
        return index.index.get(word).keySet();
    }

    // Phrase search
    public Set<Integer> searchPhrase(String phrase) {
        String[] words = phrase.split("\\s+");

        List<Map<Integer, List<Integer>>> postings = new ArrayList<>();

        for (String word : words) {
            word = normalize(word);
            if (!index.index.containsKey(word))
                return new HashSet<>();
            postings.add(index.index.get(word));
        }

        return intersectPhrase(postings);
    }

    private Set<Integer> intersectPhrase(List<Map<Integer, List<Integer>>> postings) {
        Set<Integer> result = new HashSet<>(postings.get(0).keySet());

        for (Map<Integer, List<Integer>> posting : postings) {
            result.retainAll(posting.keySet());
        }

        Set<Integer> finalDocs = new HashSet<>();

        for (int docId : result) {
            if (checkPositions(docId, postings)) {
                finalDocs.add(docId);
            }
        }

        return finalDocs;
    }

    private boolean checkPositions(int docId,
            List<Map<Integer, List<Integer>>> postings) {

        List<Integer> firstPositions = postings.get(0).get(docId);

        for (int pos : firstPositions) {
            boolean match = true;

            for (int i = 1; i < postings.size(); i++) {
                List<Integer> positions = postings.get(i).get(docId);
                if (!positions.contains(pos + i)) {
                    match = false;
                    break;
                }
            }

            if (match)
                return true;
        }

        return false;
    }

    private String normalize(String word) {
        return word.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    public static void main(String[] args) {

        // Step 1: Create index
        InvertedIndex index = new InvertedIndex();

        // Step 2: Add documents
        Document doc1 = new Document(1, "Distributed systems are scalable and reliable");
        Document doc2 = new Document(2, "Distributed systems are hard to design");
        Document doc3 = new Document(3, "Microservices architecture uses distributed systems");

        index.addDocument(doc1);
        index.addDocument(doc2);
        index.addDocument(doc3);

        // Step 3: Create search service
        SearchService searchService = new SearchService(index);

        // Step 4: Word Search
        System.out.println("🔍 Word Search: 'distributed'");
        System.out.println(searchService.searchWord("distributed"));
        // Expected: [1, 2, 3]

        System.out.println("\n🔍 Word Search: 'scalable'");
        System.out.println(searchService.searchWord("scalable"));
        // Expected: [1]

        // Step 5: Phrase Search
        System.out.println("\n🔍 Phrase Search: 'distributed systems'");
        System.out.println(searchService.searchPhrase("distributed systems"));
        // Expected: [1, 2, 3]

        System.out.println("\n🔍 Phrase Search: 'systems are hard'");
        System.out.println(searchService.searchPhrase("systems are hard"));
        // Expected: [2]

        System.out.println("\n🔍 Phrase Search: 'are scalable'");
        System.out.println(searchService.searchPhrase("are scalable"));
        // Expected: [1]

        // Step 6: Edge Cases
        System.out.println("\n🔍 Word Search: 'nonexistent'");
        System.out.println(searchService.searchWord("nonexistent"));
        // Expected: []

        System.out.println("\n🔍 Phrase Search: 'distributed hard'");
        System.out.println(searchService.searchPhrase("distributed hard"));
        // Expected: [] (not contiguous)
    }
}
