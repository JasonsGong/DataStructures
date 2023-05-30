package com.atguigu.recursion;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/13
 * @Description 八皇后问题的解题思路及代码实现
 */
public class Queue8 {
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
    }

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义一个一维数组 记录皇后在列上的位置 arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    //记录摆法的次数
    int count = 0;

    /**
     * 编写一个方法  放置n个皇后
     * 会自己回溯
     */
    public void check(int n) {
        if (n == max) { //说明已经放到了第九个皇后了
            print(); //打印
            return;
        }
        //依次放入皇后 判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前的这个皇后n，放到该行的第i列
            array[n] = i;
            //判断当前放置第n个皇后到i列时，与前面放置的皇后是否冲突
            if (judge(n)) { //不冲突
                //接着放n+1个皇后
                check(n + 1);
            }
            //如果冲突 就继续执行 array[n] = i  就皇后在本行后移一个位置
            //因为在循环里面  i++会自增 n会后移
        }
    }

    /**
     * 当我们放置了n个皇后之后 就去检测
     * 该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 表示第n个皇后
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // array[8] = {0 , 4, 7, 5, 2, 6, 1, 3}   再次注意这里数组记录的是每个皇后在列上的值
            //array[i] == array[i] 判断是不是在同一列
            //Math.abs(n - i) == Math.abs(array[n] - array[i]) 判断是不是在同一斜线上
            //自己的理解:(n -i) 相当于在行上的距离   array[n] - array[i]相当于在列上的距离  行距离等于列距离 说明二者在同一斜线上
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {//与前面放置的位置是否冲突
                return false;
            }
        }
        return true;
    }

    /**
     * 定义一个数组 打印皇后拜访的位置
     */
    public void print() {
        System.out.println("第" + (++count) + "中摆法");
        int[][] arr = new int[max][max];
        for (int i = 0; i < array.length; i++) {
            arr[i][array[i]] = 1;
        }
        for (int[] ints : arr) {
            for (int i : ints) {
                System.out.print(i + "  ");
            }
            System.out.println();
        }
    }
}
