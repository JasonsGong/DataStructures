package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/18
 * @Description 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr,0, arr.length -1,temp);
        System.out.println("归并排序之后的数组:"+ Arrays.toString(arr));
    }

    /**
     * 分治的过程
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(arr,left,mid,temp);
            //向右递归分解
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并的方法  治
     *
     * @param arr   需要排序的数组
     * @param left  右边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//左边有序序列的初始索引
        int j = mid + 1;//右边有序序列的初始化索引
        int t = 0;//指向temp数组的当前索引

        //1.
        //先把左右两边（有序）的数据填充到temp数组
        //直到左右两边的数据有一边处理完毕为止
        while (i <= mid && j <= right) {//相当于左右两边的数组都有一个指针
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {//反之将右边有序序列的当前元素 填充到temp数组
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //2.
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) {//说明左边的有序序列还有剩余的元素
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        //3.
        //将temp数组的元素拷贝到arr
        //并不是每次都拷贝
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
