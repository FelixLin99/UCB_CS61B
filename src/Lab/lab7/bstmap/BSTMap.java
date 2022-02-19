package Lab.lab7.bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.*;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UCB_CS61B
 * @description:
 * @date 2022/2/19 15:06
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;
        private int size;

        public BSTNode(K key, V value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null){
            throw new IllegalArgumentException("key should not be null.");
        }
        return containsKey(root, key);
    }

    private boolean containsKey(BSTNode node, K key){
        if (node == null){
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return containsKey(node.left, key);
        }
        else if (cmp > 0){
            return containsKey(node.right, key);
        }
        else{
            return true;
        }
    }

    @Override
    public V get(K key) {
        if (key == null){
            throw new IllegalArgumentException("key should not be null.");
        }
        return get(root, key);
    }

    private V get(BSTNode node, K key){
        if (node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            return get(node.left, key);
        }
        else if (cmp > 0){
            return get(node.right, key);
        }
        else{
            return node.value;
        }
    }



    @Override
    public int size() {
        return (root == null)? 0 : root.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null){
            throw new IllegalArgumentException("key should not be null.");
        }
        if (value == null){
            remove(key);
            return;
        }
        put(root, key, value);
    }

    private void put(BSTNode node, K key, V value){
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            if (node.left != null){
                put(node.left, key,value);
            }
            else{
                node.left = new BSTNode(key, value, 0);
                root.size++;
            }
        }
        else if (cmp > 0){
            if (node.right != null){
                put(node.right, key,value);
            }
            else{
                node.right = new BSTNode(key, value, 0);
            }
        }
        else{
            node.value = value;
        }
    }

    @Override

    public Set<K> keySet() {
        return keySet(root);
    }

    private Set<K> keySet(BSTNode node){
        if (node == null){
            return null;
        }
        HashSet<K> out = new HashSet<>();
        out.add(node.key);
        out.addAll(keySet(node.left));
        out.addAll(keySet(node.right));
        return out;
    }

    /* Breadth First Search */
    private Set<K> keySetBFS(){

        return null;
    }
    /* Depth First Search */
    private Set<K> keySetDFS(){

        return null;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("");
//        BSTNode deletedNode = delete(key);
//        return (deletedNode == null)? null: deletedNode.value;
    }

    /* Delete the node which node.key == key, and return it */
    private BSTNode delete(K key){
        if (key == null){
            throw new IllegalArgumentException("key should not be null.");
        }
        LinkedList<BSTNode> deletedNodes = new LinkedList<>();
        deletedNodes.add(new BSTNode(null, null, -1));
        root = delete(root, key, deletedNodes);
        if (deletedNodes.get(0).size == -1){
            return null;
        }
        return deletedNodes.get(0);

    }

    /* A helper method of private BSTNode delete(K key){}. Return the root but not the deleted node.  */

    private BSTNode delete(BSTNode root, K key, LinkedList<BSTNode> deletedNodes) {
        if (root == null){
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0){
            root.left = delete(root.left, key, deletedNodes);
        }
        else if (cmp > 0){
            root.right = delete(root.right, key, deletedNodes);
        }
        else{
            if (root.right == null){
                BSTNode tmp = root.left;
                root.left = null;
                deletedNodes.set(0, root);
                return tmp;
            }
            if (root.left == null){
                BSTNode tmp = root.right;
                root.right = null;
                deletedNodes.set(0, root);
                return tmp;
            }

            BSTNode tmp = root;
            BSTNode x = removeMin(root.right);



        }
        return null;
    }


    private BSTNode removeMin(BSTNode node){
        if (node == null || node.left == null){
            return node;
        }
        BSTNode tmpNode = node;
        while(tmpNode.left != null){
            tmpNode = tmpNode.left;
        }

        return null;
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("");
        //return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}
