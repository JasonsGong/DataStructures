package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/16
 * @Description 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        int[] arr1 = {3, 9, -1, 10, -2};
        int[] arr2 = {3, 9, -1, 10, -2};
        System.out.println("自己的代码");
        SelectSort.selectSort(arr2);
        System.out.println("老师的代码");
        SelectSort.selectSortByTeacher(arr1);
    }

    /**
     * 选择排序
     * 自己写的 中间的过程和老师的不一样 不确定是不是选择排序
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {//从第一个数字开始 依次找到最小值
                int temp = 0;
                if(arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.println("第次"+(i+1)+"排序的结果:"+Arrays.toString(arr));
        }
        System.out.println("最终的结果:"+Arrays.toString(arr));
    }

    /**
     * 老师的代码
     */
    public static void selectSortByTeacher(int[] arr){

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {//从第一个数字开始 依次找到最小值
                if(min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            //交换
            arr[minIndex] = arr[i];
            arr[i] = min;
            System.out.println("第次"+(i+1)+"排序的结果:"+Arrays.toString(arr));
        }
        System.out.println("最终的结果:"+Arrays.toString(arr));
    }
}