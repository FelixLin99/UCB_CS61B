package Project.proj1.deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UCB_CS61B
 * @description: TODO
 * @date 2022/2/821:51
 */
public class MaxArrayDequeTest {

    public static class myComparator1<Integer> implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return (java.lang.Integer) o1 - (java.lang.Integer)o2;
        }
    }

    public static class myComparator2<String> implements Comparator<String>{

        @Override
        public int compare(String str1, String str2) {
            return str1.toString().compareTo(str2.toString());
        }
    }

    @Test
    /* randomized tests */
    public void randomizedTest(){
        MaxArrayDeque<Integer> lld = new MaxArrayDeque<>(new myComparator1());
        LinkedList<Integer> benchMarkLld = new LinkedList<>();

        int N = 40000;
        int randVal;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            switch (operationNumber){
                // addFirst
                case 0:
                    randVal = StdRandom.uniform(0, 100);
                    lld.addFirst(randVal);
                    benchMarkLld.addFirst(randVal);
                    System.out.println("addLast(" + randVal + ")");
                    break;
                // addLast
                case 1:
                    randVal = StdRandom.uniform(0, 100);
                    lld.addLast(randVal);
                    benchMarkLld.addLast(randVal);
                    System.out.println("addLast(" + randVal + ")");
                    break;
                // removeFirst
                case 2:
                    if (lld.size() == 0 || benchMarkLld.size() == 0){
                        continue;
                    }
                    assertEquals(benchMarkLld.removeFirst(), lld.removeFirst());
                    break;
                // removeLast
                case 3:
                    if (lld.size() == 0 || benchMarkLld.size() == 0){
                        continue;
                    }
                    assertEquals(benchMarkLld.removeLast(), lld.removeLast());
                    break;
                // get
                case 4:
                    if (lld.size() == 0 || benchMarkLld.size() == 0){
                        continue;
                    }
                    randVal = StdRandom.uniform(0, benchMarkLld.size());
                    assertEquals(benchMarkLld.get(randVal), lld.get(randVal));
                    break;
                // size
                case 5:
                    assertEquals(lld.size(), benchMarkLld.size());
                    break;
                default:
                    break;
            }
        }
    }
}
