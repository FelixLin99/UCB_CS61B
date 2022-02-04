package Project.proj1.deque;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UCB_CS61B
 * @description: TODO
 * @date 2022/2/423:33
 */
public class Test {
    public static void main(String[] args) {
        int[] array = new int[]{1,1,1,1,1,1};
        int loc = 5;
        System.out.println((array.length+loc-1)%array.length);
    }
}
