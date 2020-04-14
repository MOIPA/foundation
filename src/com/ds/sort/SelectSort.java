package com.ds.sort;

/**
 * @author TR
 * @content 简单选择排序
 */
public class SelectSort {
    public int[] selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j=i+1;j<nums.length;j++){
                if (nums[min]>nums[j])min = j;
            }
            int temp = nums[min];
            nums[min] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }
}

class testChooseSort {
    public static void main(String[] args) {
        int[] nums = {45, 38, 65, 97, 76, 13, 27, 49};
        SelectSort insertSort = new SelectSort();
        int[] sort = insertSort.selectSort(nums);
        for (int i :
                sort) {
            System.out.println(i);
        }
    }
}
