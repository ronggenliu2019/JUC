package com.lrg.basic;

/**
 * 父类先初始化
 * 子类在初始化
 *
 * 实例化顺序：
 * （1）super
 * （2）非静态变量显示赋值
 * （3）非静态代码块
 * （4）构造函数
 */
public class Son extends Father {
//    public int i = method();

    static {
        int y = 0;
        System.out.print("(15)" + y);
    }
//    public static int j = test();

    public Son() {
        System.out.print("(13)");
        System.out.println("\n---------子类实例化完成-----");
    }

    public Son(int i) {
//        super(i);   // 默认super是无参的
        System.out.print("(16)");
        System.out.println("\n---------子类实例化完成-----");
    }

    {
        i++;
        System.out.print("(14)" + i);
    }

    static int test() {
        System.out.print("(12)");
        return 0;
    }

    @Override
    public int method() {
        System.out.print("(11)");
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("\n-----类初始化完成---------");
        Father f = new Father();
        Son s = new Son(1);
    }
}
