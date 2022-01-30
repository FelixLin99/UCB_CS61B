package Lab.lab2.IntList;

import org.junit.Test;
import static org.junit.Assert.*;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple1() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple2(){
        IntList lst = IntList.of(1, 2, 7, 91, 100);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 4 -> 49 -> 91 -> 100", lst.toString());
        assertTrue(changed);
    }
}
