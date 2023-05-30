package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/16
 * @Description 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1,-1,89};
        System.out.println("插入前的数组:"+Arrays.toString(arr));
        InsertSort.insertSort(arr);
    }

    /**
     * 插入排序
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            int insertVal = arr[i];
            //待插入的数的前一个数的下标
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //退出循环之后 代表找到了要插入数据的位置
            //插入数据
            arr[insertIndex + 1] = insertVal;
            System.out.println("第" + i  + "次插入的结果:" + Arrays.toString(arr));
        }
        System.out.println("最终的结果:" + Arrays.toString(arr));
    }
}
