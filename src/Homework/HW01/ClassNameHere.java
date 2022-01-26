package Homework.HW01;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UC Berkeley_CS61B
 * @description: TODO
 * @date 2022/1/2611:45
 */
public class ClassNameHere {
    /**
     * Returns the maximum value from m.
     * */
    public static int max(int[] m) {
        int maximum = m[0];
        int index = 1;
        while(index < m.length){
            if (m[index] > maximum){
                maximum = m[index];
            }
            index++;
        }
        return maximum;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}
