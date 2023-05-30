package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GongChangjiang
 * @version 1.0
 * @Date 2023/5/29
 * @Description 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("目标值的下标:" + binarySearch(arr, 0, arr.length - 1, 3));
        int[] arr2 = {1, 8, 10, 89, 89, 89, 1000, 1234};
        System.out.println("目标值在数组中下标的集合:" + binarySearch2(arr2, 0, arr2.length - 1, 89));
    }

    /**
     * 二分查找
     *
     * @param arr     需要在这里面查找目标值的数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 目标值在数组中的下标，找到就返回下标，没有就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //结束递归的条件
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找查找出所有与目标值相同的数组的下标
     *
     * @param arr     需要在这里面查找目标值的数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的的值
     * @return 目标值在数组中的下标的集合
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //结束递归的条件
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            List<Integer> resList = new ArrayList<>();
            int temp = mid - 1;
            while (true) { //向左边探测相同值的元素
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //否则就把temp放入到集合中
                resList.add(temp);
                temp--;//左移
            }
            resList.add(mid);
            //向右边探测
            temp = mid + 1;
            while (true) { //向右边探测相同值的元素
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                //否则就把temp放入到集合中
                resList.add(temp);
                temp++;//右移
            }
            return resList;
        }
    }

}
