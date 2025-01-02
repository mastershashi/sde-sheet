public class CustomHashMap<K,V> {

    private static class Entry<K,V>{
        K key;
        V value;
        Entry<K,V> next;
        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private Entry<K,V>[] table;
    private int loadFactor;
    private int size;
    private int threshold;
    public CustomHashMap(int capacity, int loadFactor){
        this.loadFactor = loadFactor;
        this.size = capacity;
        this.threshold = (int)(capacity * loadFactor);
        table = new Entry[capacity];
    }

    private int hash(K key){
        return Math.abs(key.hashCode()) % size;
    }

    private void put(K key, V value){
        int hash = hash(key);
        Entry<K,V> entry = table[hash];
        if(entry != null){
                if(entry.key.equals(key)){
                    entry.value = value;
                    return;
                }
                entry = entry.next;
        }
        // Add new entry
        entry = new Entry<K,V>(key, value);
        entry.next = table[hash];
        table[hash] = entry;

        size++;
        if(size >= threshold){
            resize();
        }
    }

    private V get(K key){
        int index = hash(key);
        Entry<K,V> entry = table[index];
        while(entry != null){
            if(entry.key.equals(key)){
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }
    
    private void remove(K key){
        int index = hash(key);
        Entry<K,V> entry = table[index];
        Entry<K,V> prev = null;
        while( entry!= null){
            if(entry.key.equals(key)){
                if(prev == null){
                    table[index] = entry.next;
                }else{
                    prev.next = entry.next;
                }
                size--;
                return;
            }
            entry = entry.next;
        }
    }

    private void resize(){
        int newCapacity = table.length * 2;
        Entry<K,V>[] newTable = new Entry[newCapacity];
        // rehash entries
        for(int i = 0; i < table.length; i++){
            Entry<K,V> entry = table[i];
            while(entry != null){
                Entry<K,V> next = entry.next;
                int hash = Math.abs(entry.key.hashCode()) % newCapacity;
                entry.next = newTable[hash];
                newTable[hash] = entry;
                entry = next;
            }
        }
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }


    
}
