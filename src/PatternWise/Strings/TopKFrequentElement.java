package PatternWise.Strings;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TopKFrequentElement {
    List<String> returnTopKFrequentElement(String[] str, int k) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        List<String> resultList = new ArrayList<>();
        for (String st : str) {
            frequencyMap.put(st, frequencyMap.getOrDefault(st, 0) + 1);
        }
        System.out.println(frequencyMap.values());

        // approach 1 using streams
        // Map<String, Integer> result = frequencyMap.entrySet()
        // .stream()
        // .filter(entry -> entry.getValue() == k)
        // .sorted(Map.Entry.comparingByKey())
        // .collect(
        // Collectors.toMap
        // (Map.Entry::getKey,
        // Map.Entry::getValue,
        // (a,b) -> a,
        // LinkedHashMap::new));

        // approach 2 using Map , sorting
        // List<Map.Entry<String, Integer>> result = new
        // ArrayList<>(frequencyMap.entrySet());
        // result.sort(Map.Entry.comparingByKey()); // o(nlogn)
        // for( Map.Entry<String, Integer> mapEntry : result){
        // if( mapEntry.getValue().equals(k)){
        // resultList.add(mapEntry.getKey());
        // }
        // }
        // return resultList;

        // approach 3 using min heap
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> frequencyMap.get(a) - frequencyMap.get(b));
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == k) {
                minHeap.add(entry.getKey());
            }
        }
        while (!minHeap.isEmpty()) {
            resultList.add(minHeap.poll());
        }
        resultList.sort((a,b) -> a.compareTo(b));
        return resultList;

    }

    public static void main(String[] args) {
        String str[] = { "i", "love", "leetcode", "i", "love", "coding" };
        int k = 2;
        TopKFrequentElement obj = new TopKFrequentElement();
        System.out.println("output" + obj.returnTopKFrequentElement(str, k));

    }

}
