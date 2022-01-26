package Homework.HW01;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UC Berkeley_CS61B
 * @description: TODO
 * @date 2022/1/2614:16
 */
public class EnhancedForBreakDemo {
    public static void main(String[] args) {
        String[] a = {"cat", "dog", "laser horse", "ketchup", "horse", "horbse"};

        for (String s : a) {
            for (int j = 0; j < 3; j += 1) {
                System.out.println(s);
                if (s.contains("horse")) {
                    break;
                }
            }
        }
    }
}