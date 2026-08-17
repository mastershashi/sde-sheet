package systemdesign.LLD.TimeBasedKeyValueStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyValueStore {

    // Here we are assumping the timestamp is in sorted order that's why applying
    // binary search in List.
    // if timestamp is not in sorted order we need to use Treemap in place of
    // ArrayList , use KeyMapStoreUnSorted.java
    private Map<Integer, List<Version>> keyMap;

    public KeyValueStore() {
        this.keyMap = new HashMap<>();
    }

    public String get(int key, int timestamp) {
        // complexity o(log n)
        if (!keyMap.containsKey(key)) {
            return "";
        }
        List<Version> versionList = keyMap.get(key);
        String result = "";
        int left = 0;
        int right = versionList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (versionList.get(mid).getTimestamp() == timestamp) {
                return versionList.get(mid).getValue();
            }
            if (versionList.get(mid).getTimestamp() < timestamp) {
                result = versionList.get(mid).getValue();
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public void put(int key, String value, int timestamp) {
        // complexity 0(1)
        keyMap.computeIfAbsent(key, k -> new ArrayList<>())
                .add(new Version(timestamp, value));
    }

    public static void main(String[] args) {
        KeyValueStore obj = new KeyValueStore();
        obj.put(1, "shashi", 1);
        obj.put(1, "shashi1", 2);
        obj.put(1, "shashi2", 3);
        obj.put(1, "shashi3", 4);

        System.out.println("result main " + obj.get(1, 5));
    }
}
