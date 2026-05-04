package CompanyWise.Confluent.Word_PhraseSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndex {
    Map<String, Map<Integer, List<Integer>>> index = new HashMap<>();

    public void addDocument(Document doc) {
        String[] words = doc.content.split("\\s+");

        for (int pos = 0; pos < words.length; pos++) {
            String word = normalize(words[pos]);

            index.putIfAbsent(word, new HashMap<>());
            index.get(word).putIfAbsent(doc.id, new ArrayList<>());
            index.get(word).get(doc.id).add(pos);
        }
    }

    private String normalize(String word) {
        return word.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

}
