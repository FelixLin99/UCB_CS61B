package Project.proj1.deque;

import java.util.Iterator;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UCB_CS61B
 * @description: A Deque Interface, which will be implemented by ArrayDeque and LinkedListDeque
 * @date 2022/2/415:02
 */
public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public default boolean isEmpty(){
        return size()==0;
    }
    public int size();
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
    public Iterator<T> iterator();
    public boolean equals(Object o);
}
