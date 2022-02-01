package Lab.lab3.randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> integerBuggyAList = new BuggyAList<>();
        AListNoResizing<Integer> integerAListNoResizing = new AListNoResizing<>();
        int epoch = 3;
        for (int i = 0; i<epoch; i++){
            integerBuggyAList.addLast(i);
            integerAListNoResizing.addLast(i);
        }
        for (int i = epoch-1; i>=0; i--){
            assertEquals(integerAListNoResizing.removeLast(), integerBuggyAList.removeLast());
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");

            } else if (operationNumber == 1){
                // getLast
                if (L.size() == 0 || B.size() == 0){
                    continue;
                }
                Integer lastL = L.getLast();
                Integer lastB = B.getLast();
                assertEquals(lastL, lastB);
            } else if (operationNumber == 2){
                // removeLast
                if (L.size() == 0 || B.size() == 0){
                    continue;
                }
                Integer lastL = L.removeLast();
                Integer lastB = B.removeLast();
                assertEquals(lastL, lastB);
            } else if (operationNumber == 3) {
                // size
                int sizeL = L.size();
                int sizeB = B.size();
                assertEquals(sizeL, sizeB);
            }
        }
    }
}
