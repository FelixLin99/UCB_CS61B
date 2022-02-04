package Project.proj1.deque;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UCB_CS61B
 * @description: Use two ArrayLists to realize Deque; ArrayList[0] is a sentinel;
 * @date 2022/2/415:09
 */
public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private T[] array;
    private int headLoc;
    private int tailLoc;
    private int size;

    public ArrayDeque(){
        this.array = (T[]) new Object[8];
        this.size = 0;
        this.headLoc = this.array.length+1;
        this.tailLoc = this.array.length+1;
    }

    @Override
    public void addFirst(T item) {
        if (item == null){
            throw new IllegalArgumentException("cannot add null");
        }
        if (isEmpty()){
            this.array[0] = item;
            this.headLoc = 0;
            this.tailLoc = 0;
            this.size = 1;
            return;
        }
        if (this.size >= this.array.length-1){
            resize(this.size * 2);
        }
        this.headLoc = (this.array.length + this.headLoc - 1) % this.array.length;
        this.array[this.headLoc] = item;
        this.size += 1;
    }

    @Override
    public void addLast(T item) {
        if (item == null){
            throw new IllegalArgumentException("cannot add null");
        }
        if (isEmpty()){
            this.array[0] = item;
            this.headLoc = 0;
            this.tailLoc = 0;
            this.size = 1;
            return;
        }
        if (this.size >= this.array.length-1){
            resize(this.size * 2);
        }
        this.tailLoc = (this.tailLoc + 1) % this.array.length;
        this.array[this.tailLoc] = item;
        this.size += 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        /*
        Prints the items in the deque from first to last, separated by a space.
        Once all the items have been printed, print out a new line
         */
        int currLoc = this.headLoc;
        for (int i = 0; i<this.size; i++){
            System.out.print(this.array[currLoc].toString() + " ");
            currLoc = (currLoc + 1) % this.array.length;
        }
        System.out.println("");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            throw new RuntimeException("cannot remove item from the empty array");
        }
        if (this.size == 1){
            T returnItem = this.array[0];
            this.array[0] = null;
            this.size = 0;
            this.headLoc = this.array.length+1;
            this.tailLoc = this.array.length+1;
            return returnItem;
        }
        T returnItem = this.array[this.headLoc];
        this.array[this.headLoc] = null;
        this.headLoc = (this.headLoc + 1) % this.array.length;
        this.size -= 1;
        if (this.size > 16 && (this.size / (double)(this.array.length)) < 0.25){
            resize(this.size * 2);
        }
        return returnItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            throw new RuntimeException("cannot remove item from the empty array");
        }
        if (this.size == 1){
            T returnItem = this.array[0];
            this.array[0] = null;
            this.size = 0;
            this.headLoc = this.array.length+1;
            this.tailLoc = this.array.length+1;
            return returnItem;
        }

        T returnItem = this.array[this.tailLoc];
        this.array[this.tailLoc] = null;
        this.tailLoc = (this.array.length + this.tailLoc - 1) % this.array.length;
        this.size -= 1;
        if (this.size > 16 && (this.size / (double)(this.array.length)) < 0.25){
            resize(this.size * 2);
        }
        return returnItem;
    }

    @Override
    public T get(int index) {
        if (isEmpty()){
            return null;
        }
        if (index >= this.size || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        int loc = (this.headLoc + index) % this.array.length;
        return this.array[loc];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayIterator();
    }

    private class MyArrayIterator implements Iterator<T> {
        private int index;

        public MyArrayIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T t = get(index);
            index += 1;
            return t;
        }

    }

    @Override
    public boolean equals(Object o) {
        /*
        if it is a Deque and
        if it contains the same contents
         */
        if (!(o instanceof Deque) || ((Deque<?>) o).size()!=this.size){
            return false;
        }
        for (int i = 0; i < this.size; i++){
            if (this.get(i) != ((Deque<?>) o).get(i)){
                return false;
            }
        }
        return true;
    }

    private void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < this.size; i += 1) {
            newArray[i] = get(i);
        }
        this.array = newArray;
        this.headLoc = 0;
        this.tailLoc = this.size - 1;
    }

}
