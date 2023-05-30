package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/17
 * @Description 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //shellSort(arr);
        shellSort2(arr);
    }

    /**
     * 希尔排序 使用的是交换法
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        //用于分组  10个数据  第一次分5组 第二次分2组  第三次分1组  每组分别插入排序
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前的那个元素大于加上步长后的那个元素，就交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("排序第" + (++count) + "轮的结果:" + Arrays.toString(arr));
        }
        System.out.println("最终的结果:" + Arrays.toString(arr));
    }

    /**
     * 使用移动法的希尔排序
     */
    public static void shellSort2(int[] arr) {
        //用于分组  10个数据  第一次分5组 第二次分2组  第三次分1组  每组分别插入排序
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                   arr[j] = temp;                }
            }
            System.out.println("排序第" + (++count) + "轮的结果:" + Arrays.toString(arr));
        }
        System.out.println("最终的结果:" + Arrays.toString(arr));
    }

}
