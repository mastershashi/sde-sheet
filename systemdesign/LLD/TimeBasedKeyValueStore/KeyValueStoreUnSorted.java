package systemdesign.LLD.TimeBasedKeyValueStore;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class KeyValueStoreUnSorted {

    private Map<Integer, TreeMap<Integer, String>> keyMap;
    // for thread safe version check ThreadSafeKeyValueStore.java

    public KeyValueStoreUnSorted() {
        this.keyMap = new HashMap<>();
    }

    public String get(int key, int timestamp) {
        // complexity o(log n)
        if (!keyMap.containsKey(key)) {
            return "";
        }
        TreeMap<Integer, String> map = keyMap.get(key);
        if (map == null) {
            return "";
        }
        Map.Entry<Integer, String> mapEntry = map.floorEntry(timestamp);
        return mapEntry == null ? " " : mapEntry.getValue(); // o(logn)

    }

    public void put(int key, String value, int timestamp) {
        // complexity 0(1)
        keyMap.computeIfAbsent(key, k -> new TreeMap<>())
                .put(timestamp, value);
    }

    public static void main(String[] args) {
        KeyValueStoreUnSorted obj = new KeyValueStoreUnSorted();
        obj.put(1, "shashi", 1);
        obj.put(1, "shashi1", 2);
        obj.put(1, "shashi2", 3);
        obj.put(1, "shashi3", 4);

        System.out.println("result main " + obj.get(1, 5));
    }
}
