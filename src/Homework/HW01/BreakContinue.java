package Homework.HW01;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UC Berkeley_CS61B
 * @description: TODO
 * @date 2022/1/2612:08
 */
public class BreakContinue {
    /**
     * @desc that replaces each element a[i] with the sum of a[i] through a[i + n],
     *      but only if a[i] is positive valued.
     *      If there are not enough values because we reach the end of the array,
     *      we sum only as many values as we have
     */
    public static void windowPosSum(int[] a, int n) {
        for(int index=0; index<a.length-1; index++){
            if(a[index]<=0){
                continue;
            }
            else{
                for(int tmp=index+1; tmp<a.length; tmp++){
                    if(tmp - index > n){
                        break;
                    }
                    else{
                        a[index] += a[tmp];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}