# Cache Implementation

This projects intends to mimics the core functionalities of a Cache, building features for faster and efficient retrieval of data. The fundamental of faster retrieval of data in Cache relies on the principle of intelligently storing limited data in the main memory (or any fast memory). For simplicity I have chosen LRU (Least Recently Used) as the eviction policy but it is extendible to accomodate new policies.

## Functional Requirements

* Memory: Cache will be of fixed size
* Fast Access: Cache Insert and lookup operation should be fast , preferably O(1) time
* Eviction Policy: if Memory Limit is reached, then evict the entry which is least recently used
* A CacheManager class will manage the creation of a Cache based on eviction strategy and also maintains created caches 

## Methods Implemented

```
clear() // Clears the cache
evict() // Removes a key value pair from cache
put() // Add an element to cache
lookup() // Get an element from cache
getName() // Get name of cache
getAllItems() // Get all items from cache
```

## Building the project

This is a gradle project and you can use the following command to build artifacts (jar, classes) and run tests.
```
./gradlew build
```

## Design Considerations & Assumptions
* In the real world, for using Cache projects mostly make a network call to Redis/Memcached server. But this project focussed on the essense of Cache by having Cache library available at JAR dependency.
* When we aim to achieve O(1) lookup, obvious data structure comes to our mind is a HashMap. HashMap provides O(1) insertion and lookup. 
* For thread safe operations, ConcurrentHashMap is chosen.
* To keep track of the least recently used items, another data structure that provides fast insertion & deletion had to be chosen, namely LinkedList
* HashMap will hold the keys and address of the Nodes of  LinkedList. Meanwhile LinkedList will hold the values of keys.
* To track the recently used items, we move the items in the LinkedList. If an item is accessed/added recently it will be moved to the head of the LinkedList. During eviction, we remove the last item from the LinkedList.

## Running the Application

An example runner class LRUCacheExampleRunner.java is created to simualte the working of LRUCache.

## Future Enhancements

* Introducing the concept of TTL for the cached data.
* Add implementations for multiple eviction strategies.
* Allow use of any `Object` as key or value in Cache.
