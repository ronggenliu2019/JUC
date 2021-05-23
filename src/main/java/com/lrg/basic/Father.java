package com.lrg.basic;

/**
 * 两种初始化：
 * （1）类初始化，包括static 变量定义，static块，按顺序调用
 * （2）实例初始化，包括变量定义，块，按顺序调用，然后是构造方法。
 */
public class Father {
    public int i = method();
    public static int j = test();

    static {
        System.out.print("(5)");
    }

    public Father() {
        System.out.print("(3)");
        System.out.println("\n---------父类实例化完成-----");
    }

    public Father(int i) {
        System.out.print("(6)");
        System.out.println("\n---------父类实例化完成-----");
    }

    {
        i++;
        System.out.print("(4)" + i);
    }

    static int test() {
        System.out.print("(2)");
        return 0;
    }

    protected int method() {
        System.out.print("(1)");
        return 0;
    }

    public static void main(String[] args) {
//        Father f = new Father();
    }
}
