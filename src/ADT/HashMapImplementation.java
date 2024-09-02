package ADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simple implementation of a HashMap using separate chaining for collision resolution.
 * This implementation provides basic operations such as add, remove, get, and contains.
 * It also supports resizing the backing table when the load factor exceeds a specified threshold.
 * 
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 * 
 * @author Heng Han Yee
 */
public class HashMapImplementation<K, V> implements HashMap<K, V>, Iterable<K> {
    private static final int STARTING_SIZE = 16;
    private static final double MAX_LOAD_FACTOR = 0.75;
    private MapEntry<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMapImplementation() {
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    /**
     * Adds a key-value pair to the map.
     * 
     * @param key the key to be added
     * @param value the value to be associated with the key
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
    @Override
    public V add(K key, V value) {
        return put(key, value);
    }

    /**
     * Puts a key-value pair into the map.
     * 
     * @param key the key to be added
     * @param value the value to be associated with the key
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
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

    /**
     * Removes the mapping for a key from this map if it is present.
     * 
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
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

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * 
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
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

    /**
     * Returns true if this map contains a mapping for the specified key.
     * 
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        table = new MapEntry[STARTING_SIZE];
        size = 0;
    }

    /**
     * Returns the number of key-value mappings in this map.
     * 
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an array containing all of the keys in this map.
     * 
     * @return an array containing all of the keys in this map
     */
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

    /**
     * Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default mapping of the key
     * @return the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key
     */
    public V getOrDefault(K key, V defaultValue) {
        V value = this.get(key);
        return (value != null) ? value : defaultValue;
    }
    
    /**
     * Returns an array containing all of the values in this map.
     * 
     * @return an array containing all of the values in this map
     */
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

    /**
     * Resizes the backing table to the specified new length.
     * 
     * @param newLength the new length of the backing table
     */
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

    /**
     * Returns the backing table of this map.
     * 
     * @return the backing table of this map
     */
    @Override
    public MapEntry<K, V>[] getTable() {
        return table;
    }

    /**
     * Returns the index for the specified key.
     * 
     * @param key the key whose index is to be calculated
     * @return the index for the specified key
     */
    private int getIndex(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    /**
     * Returns an iterator over the keys in this map.
     * 
     * @return an iterator over the keys in this map
     */
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

    /**
     * A class representing a map entry (key-value pair) in the hash map.
     * 
     * @param <K> the type of keys maintained by this map entry
     * @param <V> the type of mapped values
     */
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