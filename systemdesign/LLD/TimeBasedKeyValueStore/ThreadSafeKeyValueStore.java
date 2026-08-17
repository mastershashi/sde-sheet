package systemdesign.LLD.TimeBasedKeyValueStore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ThreadSafeKeyValueStore {

    private Map<Integer, ConcurrentSkipListMap<Integer, String>> keyMap;
    // for thread safe version check ThreadSafeKeyValueStore.java

    public ThreadSafeKeyValueStore() {
        this.keyMap = new ConcurrentHashMap<>();
    }

    public String get(int key, int timestamp) {
        // complexity o(log n)
        ConcurrentSkipListMap<Integer, String> map = keyMap.get(key);
        if (map == null) {
            return "";
        }
        Map.Entry<Integer, String> mapEntry = map.floorEntry(timestamp);
        return mapEntry == null ? "" : mapEntry.getValue(); // o(logn)

    }

    public void put(int key, String value, int timestamp) {
        // avg complexity 0(1)
        // worst case complexity o(logn)
        keyMap.computeIfAbsent(key, k -> new ConcurrentSkipListMap<>())
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
