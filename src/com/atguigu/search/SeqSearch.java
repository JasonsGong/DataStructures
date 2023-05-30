package com.atguigu.search;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/29
 * @Description 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};//没有顺序的数组
        System.out.println("目标值的下标:"+seqSearch(arr, 11));
    }

    /**
     * 找到一个满足条件的值就返回
     * 线性查找是逐一比对，发现有相同的值时就返回这个值的下标
     * @param arr 需要在这里面找目标值的数组
     * @param value 目标值
     * @return 目标值的下标
     */
    public static int seqSearch(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
