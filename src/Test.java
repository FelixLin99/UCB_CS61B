import Lab.lab7.bstmap.BSTMap;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Felix
 * @title: Shuhui Lin
 * @projectName UCB_CS61B
 * @description: TODO
 * @date 2022/2/1419:25
 */
public class Test {
    public static void main(String[] args) {
        LinkedList<String> llst = new LinkedList<>();
        Stack<String> strings = new Stack<>();
        llst.add("1");
        llst.add("2");
        llst.add("3");
        System.out.println(llst.pop());
        System.out.println(llst.toString());
        System.out.println(llst.removeLast());
        System.out.println(llst.toString());
    }
}
