package Project.proj1.deque;

import java.util.Iterator;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UCB_CS61B
 * @description: LinkedListDeque with circular sentinel topology based;
 * @date 2022/2/4 15:06
 */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{

    // nested class
    private class Node<T>{
        private T item;
        private Node prev;
        private Node next;

        public Node(){};
        public Node(T i, Node p, Node n){
            this.item = i;
            this.prev = p;
            this.next = n;
        }
    }

    private Node<T> sentinelNode;
    private int size;


    public LinkedListDeque(){
        // build a sentinel node
        this.sentinelNode = new Node<T>();
        this.sentinelNode.item = null;
        this.sentinelNode.prev = this.sentinelNode;
        this.sentinelNode.next = this.sentinelNode;
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (item == null){
            throw new IllegalArgumentException("cannot be null");
        }
        Node<T> firstNode = new Node<>(item, this.sentinelNode.prev, this.sentinelNode);
        this.sentinelNode.prev.next = firstNode;
        this.sentinelNode.prev = firstNode;
        this.size += 1;
    }

    @Override
    public void addLast(T item) {
        if (item == null){
            throw new IllegalArgumentException("cannot be null");
        }
        Node<T> lastNode = new Node<>(item, this.sentinelNode, this.sentinelNode.next);
        this.sentinelNode.next.prev = lastNode;
        this.sentinelNode.next = lastNode;
        this.size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean equals(Object o){
        /*
        if it is a Deque and
        if it contains the same contents
         */
        if (! (o instanceof Deque)){
            return false;
        }
        for (int i = 0; i < this.size; i++){
            if (this.get(i) != ((Deque<?>) o).get(i)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void printDeque() {
        /*
        Prints the items in the deque from first to last, separated by a space.
        Once all the items have been printed, print out a new line.
         */
        Node<T> currNode = new Node<>();
        currNode = this.sentinelNode;
        for (int i = 0; i < this.size; i++){
            currNode = currNode.prev;
            System.out.print(currNode.item.toString() + " ");
        }
        System.out.println();
        currNode = currNode.prev;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        Node<T> firstNode = this.sentinelNode.prev;
        firstNode.prev.next = this.sentinelNode;
        this.sentinelNode.prev = firstNode.prev;
        this.size -= 1;
        return firstNode.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        Node<T> lastNode = this.sentinelNode.next;
        lastNode.next.prev = this.sentinelNode;
        this.sentinelNode.next = lastNode.next;
        this.size -= 1;
        return lastNode.item;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0){
            return null;
        }
        Node<T> currNode =  this.sentinelNode;
        for (int i = 0; i <= index; i++){
            currNode = currNode.prev;
        }
        T returnValue = currNode.item;
        findSentinel();

        return returnValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizPos;
        private Node<T> currNode;

        public LinkedListIterator() {
            wizPos = 0;
            currNode = sentinelNode;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            currNode = currNode.prev;
            T returnItem = currNode.item;
            wizPos += 1;
            if (wizPos == size){
                findSentinel();
            }
            return returnItem;
        }
    }


    public T getRecursive(int index){
        if (index >= size || index < 0){
            return null;
        }
        if (index == 0){
            T returnItem = this.sentinelNode.item;
            findSentinel();
            return returnItem;
        }
        this.sentinelNode = this.sentinelNode.prev;
        return getRecursive(index - 1);
    }

    /*
    Help methods
     */
    private void findSentinel(){
        while(this.sentinelNode.item != null){
            this.sentinelNode = this.sentinelNode.next;
        }
    }

}
