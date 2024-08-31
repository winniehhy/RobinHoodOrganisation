package ADT;

/**
 * The HashMap interface defines the contract for a hash map data structure.
 * A hash map stores key-value pairs and allows for efficient retrieval,
 * insertion, and deletion of elements based on their keys.
 * 
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 * 
 *  Authors: Heng Han Yee
 */
public interface HashMap<K, V> {
    // Initial size of the backing array
    int STARTING_SIZE = 9;
    
    // Maximum load factor before resizing
    double MAX_LOAD_FACTOR = 0.67;

    /**
     * Adds a key-value pair to the map.
     * If the key already exists, updates the value.
     * 
     * @param key the key to add
     * @param value the value to associate with the key
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
    V add(K key, V value);

    V put(K key, V value);

    /**
     * Removes the key-value pair associated with the specified key.
     * 
     * @param key the key to remove
     * @return the value associated with the removed key, or null if the key was not found
     */
    V remove(K key);

    /**
     * Retrieves the value associated with the specified key.
     * 
     * @param key the key whose associated value is to be returned
     * @return the value associated with the specified key, or null if the key was not found
     */
    V get(K key);

    /**
     * Checks if the map contains the specified key.
     * 
     * @param key the key to check for
     * @return true if the map contains the key, false otherwise
     */
    boolean contains(K key);

    /**
     * Clears all key-value pairs from the map.
     */
    void clear();

    /**
     * Returns the number of key-value pairs in the map.
     * 
     * @return the number of key-value pairs in the map
     */
    int size();

    /**
     * Returns an array of all keys in the map.
     * 
     * @return an array containing all keys in the map
     */
    K[] keyArray();

    /**
     * Returns an array of all values in the map.
     * 
     * @return an array containing all values in the map
     */
    V[] valueArray();

    /**
     * Resizes the backing array to the specified length.
     * 
     * @param length the new length of the backing array
     */
    void resizeBackingTable(int length);

    /**
     * Returns the backing array of map entries.
     * 
     * @return an array containing all map entries
     */
    MapEntry<K, V>[] getTable();

    interface MapEntry<K, V> {
        /**
         * Returns the key corresponding to this entry.
         * 
         * @return the key corresponding to this entry
         */
        K getKey();

        /**
         * Returns the value corresponding to this entry.
         * 
         * @return the value corresponding to this entry
         */
        V getValue();

        /**
         * Replaces the value corresponding to this entry with the specified value.
         * 
         * @param value the new value to be stored in this entry
         */
        void setValue(V value);
    }
}