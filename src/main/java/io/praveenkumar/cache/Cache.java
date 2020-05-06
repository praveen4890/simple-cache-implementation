package io.praveenkumar.cache;

public interface Cache {

    /**
     * Removes all the key value mappings from this cache
     */
    void clear();

    /**
     * Remove a specific key and its associated value from the cache if present
     * @param key
     */
    void evict(String key);

    /**
     * Add or overwrites key and its associated value
     * @param key
     * @param value
     */
    void put(String key, String value);

    /**
     * Get the value for a specific key if present else null
     * @param key
     * @return
     */
    String lookup(String key);

    /**
     * Get the name of the cache
     * @return
     */
    String getName();

    /**
     * Helper method to print cache data
     */
    String getAllItems();
}
