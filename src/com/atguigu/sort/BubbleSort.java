package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/15
 * @Description 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        BubbleSort.sort(arr);
    }

    /**
     * 冒泡排序
     * @param arr  需要排序的数组
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = 0; //临时变量
            boolean flag = false; //表示是否进行过排序
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数字大于后面的数字就交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "次排序:" + Arrays.toString(arr));
            if(!flag){ //说明一次都没有交换  说明已经有序
                break;
            }
        }
        System.out.println("排序之后的数组:" + Arrays.toString(arr));
    }
}
