package Homework.HW01;

/**
 * @author Shuhui Lin
 * @title: DrawingATriangle
 * @projectName UC Berkeley_CS61B
 * @description: take it as review, but still way too simple...
 * @date 2022/1/2611:26
 */

public class DrawingATriangle {
    public static void drawTriangles(int n){
        int mark = 0;
        while(mark++ < n){
            System.out.print("*");
        }
    }
    public static void drawLines(int lines){
        int line_now = 0;
        while(line_now++ < lines){
            drawTriangles(line_now);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        if (args.length == 0){
            drawLines(5);
        }
        else{
            int lines = Integer.parseInt(args[0]);
            drawLines(lines);
        }
    }
}
