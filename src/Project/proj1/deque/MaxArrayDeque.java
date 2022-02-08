package Project.proj1.deque;

import java.util.Comparator;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UCB_CS61B
 * @description: TODO
 * @date 2022/2/821:18
 */
public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comparator;
    private ArrayDeque<T> arrayDeque;

    public MaxArrayDeque(Comparator<T> c){
        super();
        this.comparator = c;
    }

    /* Returns the maximum element in the deque as governed by the previously given Comparator.
    If the MaxArrayDeque is empty, simply return null.
     */
    public T max(){
        if (this.arrayDeque.isEmpty()){
            return null;
        }
        T maxItem = this.arrayDeque.get(0);
        for (T item : this.arrayDeque){
            if (this.comparator.compare(item, maxItem) > 0){
                maxItem = item;
            }
        }
        return maxItem;
    }

    /* Returns the maximum element in the deque as governed by the parameter Comparator c.
    If the MaxArrayDeque is empty, simply return null.

     */
    public T max(Comparator<T> c){
        if (this.arrayDeque.isEmpty()){
            return null;
        }
        T maxItem = this.arrayDeque.get(0);
        for (T item : this.arrayDeque){
            if (c.compare(item, maxItem) > 0){
                maxItem = item;
            }
        }
        return maxItem;
    }

}
