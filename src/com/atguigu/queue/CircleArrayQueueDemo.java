package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/4/28
 * @Description 模拟环形队列
 * <p>
 * 环形队列满的条件:(rear + 1) % maxSize == front
 * 环形队列空的条件:rear == front
 * 环形队列中有效数据的个数: (rear + maxSize - front) % maxSize
 */

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        System.out.println("测试环形队列");
        CircleArray queue = new CircleArray(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }
}

class CircleArray {
    private int maxSize; // 表示数组的最大容量
    private int front; // 队列头  初始值是0
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定
    private int rear; // 队列尾
    private int[] arr; // 该数据用于存放数据, 模拟队列

    /**
     * 构造方法
     *
     * @param arrMaxSize 环形队列的最大容量
     */
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否为满
     */
    public boolean isFull() {
        //因为是环形队列 如果尾部队列的后一个指向队列的头部 那么我们认为队列是满的
        return (rear + 1) % maxSize == front;   //这里是一个理解点
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return rear == front;
    }


    /**
     * 添加数据
     */
    public void addQueue(int n) {
        //先判断队列是否为满  满了就不添加
        if (this.isFull()) {
            System.out.println("队列为满,不能添加数据!");
            return;
        }
        //添加数据
        arr[rear] = n;
        //将人rear向后移
        rear = (rear + 1) % maxSize;  //这里是一个理解点
    }


    /**
     * 取出数据
     */
    public int getQueue() {
        //先判断队列是否为空  空了就无法取出数据
        if (this.isEmpty()) {
            System.out.println("队列为空,无法取出数据!");
            throw new RuntimeException("队列空，不能取数据");
        }
        //不为空 取出数据
        int result = arr[front];
        front = (front + 1) % maxSize; //这里是一个理解点
        return result;
    }


    /**
     * 显示对垒数据的方法
     */
    public void showQueue() {
        //判断队列是否为空 如果队列为空就不遍历
        if (this.isEmpty()) {
            System.out.println("队列为空!");
            return;
        }
        //遍历的方式 从front开始遍历  遍历多少个元素
        int count = this.size();
        for (int i = front; i < front + count; i++) {//主要作用是遍历多少次
            System.out.println("arr[" + i % maxSize + "]=" + arr[i % maxSize]);
        }
    }

    /**
     * 求出当前数列中有效数据的个数
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;  //这是一个理解点
    }

    /**
     * 显示队列的头元素
     */
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }

}