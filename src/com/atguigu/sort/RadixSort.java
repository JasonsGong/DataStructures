package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/26
 * @Description 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        //定义一个待排序的数组
        int arr[] = {53, 3, 542, 748, 14, 214};
        RadixSort.radixSort(arr);
    }

    //基数排序
    public static void radixSort(int[] arr) {
        //得到数组中最大数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //最大数的位数
        int maxLength = (max + "").length();
        //定义一个二维数组，表示十个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录每个桶中依次放入数据的个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {//循环的是每一轮，第一次是个位。第二次是百位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位数的值
                int digitOfElement = arr[j] / n % 10;
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                //[bucketElementCounts[digitOfElement]]++的意思是在digitOfElement对应的桶中数据个数加一
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶中顺序取出数据
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第K个桶，放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + (i + 1) + "轮:" + Arrays.toString(arr));
        }
    }
}
