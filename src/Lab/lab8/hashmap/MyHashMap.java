package Lab.lab8.hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Shuhui Lin
 */

/** My notes:
 *  null keys will never be inserted
 * When you are searching for a Node in a Collection, simply iterate over the Collection, and find the Node whose key is .equal() to the key you are searching for
 *
 *
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int initialSize = 16;
    private double maxLoad = 0.75;
    private int items = 0;

    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(initialSize);
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        buckets = createTable(this.initialSize);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.maxLoad = maxLoad;
        this.buckets = createTable(this.initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<Node>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    @Override
    public void clear() {
        buckets = createTable(this.initialSize);
    }

    @Override
    public boolean containsKey(K key) {
        int idx = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[idx] == null){
            return false;
        }
        for(Node item : buckets[idx]){
            if (key.equals(item.key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int idx = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[idx] == null){
            return null;
        }
        for(Node item : buckets[idx]){
            if (key.equals(item.key)){
                return item.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return items;
    }

    @Override
    public void put(K key, V value) {
        int idx = Math.floorMod(key.hashCode(), buckets.length);
        buckets[idx] = (buckets[idx]==null)? createBucket(): buckets[idx];
        for (Node node : buckets[idx]){
            if (key.equals(node.key)){
                buckets[idx].remove(node);
                items--;
            }
        }
        buckets[idx].add(createNode(key, value));
        items++;
        if ((double)items / buckets.length > 0.75){
            resize();
        }
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> output = new HashSet<>();
        for (int idx = 0; idx < buckets.length; idx++){
            if (buckets[idx] != null){
                for (Node node : buckets[idx]){
                    output.add(node.key);
                }
            }
        }
        return output;
    }

    @Override
    public V remove(K key) {
        int idx = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[idx] == null){
            return null;
        }
        for(Node item : buckets[idx]){
            if (key.equals(item.key)){
                V tmp = item.value;
                buckets[idx].remove(item);
                return tmp;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        int idx = Math.floorMod(key.hashCode(), buckets.length);
        if (buckets[idx] == null){
            return null;
        }
        for(Node item : buckets[idx]){
            if (key.equals(item.key) && value.equals(item.value)){
                buckets[idx].remove(item);
                return value;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


    //helper method
    // If required to resize down, we can use the following method and set newLoad like buckets.length / 2
    private void resize(int newLoad){

    }

    private void resize(){
        Collection<Node>[] oldBuckets = this.buckets;
        this.buckets = createTable(oldBuckets.length * 2);
        for (int idx = 0; idx < oldBuckets.length; idx++){
            if (oldBuckets[idx] != null){
                for (Node node : oldBuckets[idx]){
                    put(node.key, node.value);
                }
            }
        }
    }


}
