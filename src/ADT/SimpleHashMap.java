package ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements HashMap<K, V>, Iterable<K> {
    private static final int STARTING_SIZE = 16;
    private static final double MAX_LOAD_FACTOR = 0.75;
    private MapEntry<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public V add(K key, V value) {
        return put(key, value);
    }

    // Assuming put should return V
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }

        if ((double) size / table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2);
        }

        int index = getIndex(key);
        MapEntry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
            entry = entry.next;
        }
        table[index] = new MapEntry<>(key, value, table[index]);
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        int index = getIndex(key);
        MapEntry<K, V> entry = table[index];
        MapEntry<K, V> prev = null;
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.getValue();
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        MapEntry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public void clear() {
        table = new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public K[] keyArray() {
        int count = 0;
    
        // First, count the number of keys
        for (MapEntry<K, V> entry : table) {
            while (entry != null) {
                count++;
                entry = entry.next;
            }
        }
    
        // Create an array to hold the keys
        @SuppressWarnings("unchecked")
        K[] keys = (K[]) new Object[count];
    
        // Now, populate the array with keys
        int index = 0;
        for (MapEntry<K, V> entry : table) {
            while (entry != null) {
                keys[index++] = entry.getKey();
                entry = entry.next;
            }
        }
    
        return keys;
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = this.get(key);
        return (value != null) ? value : defaultValue;
    }
    
    

    @Override
    public V[] valueArray() {
        V[] values = (V[]) new Object[size];  // Use Object[] and cast
        int index = 0;
        for (MapEntry<K, V> entry : table) {
            while (entry != null) {
                values[index++] = entry.getValue();
                entry = entry.next;
            }
        }
        return values;
    }

    @Override
    public void resizeBackingTable(int newLength) {
        @SuppressWarnings("unchecked")
        MapEntry<K, V>[] newTable = (MapEntry<K, V>[]) new MapEntry[newLength];
        for (MapEntry<K, V> entry : table) {
            while (entry != null) {
                int newIndex = Math.abs(entry.getKey().hashCode() % newLength);
                MapEntry<K, V> nextEntry = entry.next;
                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;
                entry = nextEntry;
            }
        }
        table = newTable;
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        return table;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int currentIndex = 0;
            private MapEntry<K, V> currentEntry = null;
    
            @Override
            public boolean hasNext() {
                // Move to the next non-null entry in the current index
                if (currentEntry != null) {
                    return true;
                }
    
                // Move to the next non-null bucket in the table
                while (currentIndex < table.length) {
                    if (table[currentIndex] != null) {
                        currentEntry = table[currentIndex];
                        currentIndex++;
                        return true;
                    }
                    currentIndex++;
                }
    
                // No more elements
                return false;
            }
    
            @Override
            public K next() {
                // Ensure there's a next element
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
    
                // Return the key of the current entry and move to the next
                K key = currentEntry.getKey();
                currentEntry = currentEntry.next; // Move to the next entry in the bucket
                return key;
            }
        };
    }

    private static class MapEntry<K, V> implements HashMap.MapEntry<K, V> {
        private K key;
        private V value;
        private MapEntry<K, V> next;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public MapEntry(K key, V value, MapEntry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }
    }
}
