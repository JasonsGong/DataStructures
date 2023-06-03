package com.atguigu.search;

import java.util.Arrays;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/30
 * @Description 插值查找算法
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        //创建一个数组 用于模拟需要查找数据的数组
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("查找的数的下标为:" + insertValueSearch(arr, 0, arr.length - 1, 30));
    }

    /**
     * 插值查找算法
     *
     * @param arr     目标数组(有序的)等差序列的最好，1-100有序的数组，找一个数只用找一次就行了
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 需要查找的值
     * @return 查找的值在数组中的索引
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("方法被调用了");
        //退出的条件和防止数组越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //求出mid,这个是插值查找算法的灵魂,自适应的查找
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) { //应向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左边递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}