package Project.proj1.deque;

import Lab.lab3.randomizedtest.AListNoResizing;
import Lab.lab3.randomizedtest.BuggyAList;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /* Test equal method */
    public void equalTest(){
        //
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(4);
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addFirst(1);
        lld2.addFirst(2);
        lld2.addFirst(4);
        LinkedListDeque<Integer> lld3 = new LinkedListDeque<>();
        lld3.addFirst(1);
        lld3.addLast(2);
        lld3.addFirst(4);
        assertTrue("should return true", lld1.equals(lld2));
        assertFalse("should return false", lld1.equals(lld3));

        lld2.removeFirst();
        assertFalse("shoud return false", lld1.equals(lld2));

        //
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(4);
        assertTrue("should return true", lld1.equals(ad1));
    }

    @Test
    /* Test printDeque method */
    public void printDequeTest(){
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addFirst(1);
        lld2.addFirst(2);
        lld2.addFirst(4);

        String corrStr = new String("4 2 1");
        lld2.printDeque();

    }

    @Test
    /* Test get and getRecursive methods */
    public void getTest(){
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addFirst(1);
        lld2.addFirst(2);
        lld2.addFirst(4);
        lld2.addFirst(8);

        Integer getNum2 = lld2.get(0);
        Integer getRecursiveNum2 = lld2.getRecursive(0);
        assertEquals(8, (long)getNum2);
        assertEquals(8, (long)getRecursiveNum2);

        Integer getNum1 = lld2.get(1);
        Integer getRecursiveNum1 = lld2.getRecursive(1);
        assertEquals(4, (long)getNum1);
        assertEquals(4, (long)getRecursiveNum1);

        assertEquals(null, lld2.get(-1));
        assertEquals(null, lld2.get(100));
        assertEquals(null, lld2.getRecursive(-1));
        assertEquals(null, lld2.getRecursive(100));

    }

    @Test
    /* Test iterator (enhanced for loop) */
    public void iteratorTest(){
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        lld2.addFirst(1);
        lld2.addFirst(2);
        lld2.addFirst(4);
        lld2.addFirst(8);
        int i = 0;
        for (Integer item:lld2){
            assertEquals((long)lld2.get(i++), (long)item);
        }
    }

    @Test
    /* randomized tests */
    public void randomizedTest(){
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        LinkedList<Integer> benchMarkLld = new LinkedList<>();

        int N = 10000;
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
