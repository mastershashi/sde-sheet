package PatternWise.Heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElement {

    public int[] findTopKFrequentElement(int nums[], int k) {
        if (nums.length == 0) {
            return new int[] {};
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.getValue(), b.getValue()));

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        frequencyMap.entrySet().forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
        for (Map.Entry<Integer, Integer> mapEntry : frequencyMap.entrySet()) {
            pq.add(mapEntry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.stream().mapToInt(Map.Entry::getKey).toArray();

    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int k = 2;
        TopKFrequentElement obj = new TopKFrequentElement();
        int[] result = obj.findTopKFrequentElement(nums, k);
        for (int i = result.length - 1; i >= 0; i--) {
            System.out.print(result[i] + " ");
        }

        System.out.println();
    }

}
