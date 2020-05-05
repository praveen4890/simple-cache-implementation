package io.praveenkumar.cache;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LRUCache implements Cache {

    private String cacheName;
    private int cacheSize = 10; //default
    private LinkedList<Node> list = new LinkedList<>();
    private Map<String, Node> map = new ConcurrentHashMap<>();

    public LRUCache(String name, CacheConfig config) {
        this.cacheName = name;
    }

    @Override
    public void clear() {
        list = new LinkedList<>();
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void evict(String key) {
        if(map.containsKey(key)) {
            list.remove(map.get(key));
            map.remove(key);
        }
    }

    @Override
    public void put(String key, String value) {
        Node node = new Node(key, value);
        if(map.containsKey(key)) {
            list.remove(map.get(key));
        }
        map.put(key, node);
        list.addFirst(node);
        while(list.size() > cacheSize) {
            map.remove(list.getLast().getKey());
            list.removeLast();
        }
    }

    @Override
    public String lookup(String key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            list.remove(node);
            list.addFirst(node);
            return node.getValue();
        }
        return null;
    }

    @Override
    public String getName() {
        return cacheName;
    }
}
