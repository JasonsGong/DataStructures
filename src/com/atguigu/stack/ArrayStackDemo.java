package com.atguigu.stack;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/10
 * @Description 使用数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        //入栈
        stack.push(1);
        stack.push(2);
        stack.push(3);
        //出栈
        System.out.println("出栈的数是:"+stack.pop());
        //显示栈中所有的数据
        stack.list();
    }
}

//定义一个ArrayStack表示栈
class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组 数组模拟栈 数据放在数组中
    private int top = -1;  //top表示栈顶 初始化为1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        //完成数组的初始化
        stack = new int[maxSize];
    }

    /**
     * 判断是否栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top == -1;
    }


    /**
     * 入栈的操作
     */
    public void push(int value) {
        //先判断栈是否为满
        if (isFull()) {
            System.out.println("栈满,无法添加数据!");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈的操作
     * 将栈顶的数据返回
     */
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈空,无法取出数据!");
        }
        //取出数据
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示栈的情况
     */
    public void list() {
        //先判断栈是否为空
        if (isEmpty()) {
            System.out.println("栈空,无法取出数据!");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }

}
