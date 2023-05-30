package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/17
 * @Description 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr   需要排序的数组
     * @param left  左下标
     * @param right 右下标
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        //pivot 中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0;
        //while循环的目的是让比pivot小的放到左边  大的放在右边
        while (l < r) {
            //在pivot的左边一直找 找到大于等于pivot的值 才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的左边一直找 找到大于等于pivot的值 才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l >= r成立  说明pivot的左右两边的值 已经是按照左边全部是
            //小于等于pivot值 右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完之后发现arr[l] = pivot值
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换完之后发现arr[r] = pivot值
            if (arr[r] == pivot) {
                l++;
            }
        }

        //如果l == r 必须l++ r--
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {//左边的数全部有序
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
