package com.ds.sort;

/**
 * @author TR
 * @content 交换类排序
 * 冒泡排序
 * 快速排序
 */
public class SwapSort {
    public int[] swapSort(int[] nums) {
        for (int i = 0; i < nums.length; i++)
            for (int j = 0; j < nums.length-1; j++)
                if (nums[j+1] < nums[j]) {
                    int temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                }
        return nums;
    }

    public int[] quickSort(int[] nums) {
        doQuickSort(nums,0,nums.length-1);
        return nums;
    }
    public void doQuickSort(int[] nums, int l, int r) {
        if (l>=r)return;
        int temp = nums[l];
        int i=l,j=r;
        while (i < j) {
            // j从右往左(遇到i停止)找到第一个小于temp的值
            while(nums[j]>=temp && j>i) j--;
            nums[i++] = nums[j];
            //i可能等于j
            if (i>=j) break;
            // j处为空 i从左往右找到第一个大于temp的值
            while(nums[i]<temp && i<j) i++;
            nums[j--] = nums[i];
        }
        nums[j]=temp;
        doQuickSort(nums,l,j-1);
        doQuickSort(nums,j+1,r);
    }
}

class testSwapSort {
    public static void main(String[] args) {
        int[] nums = {45, 38, 65, 97, 76, 13, 27, 49};
        SwapSort insertSort = new SwapSort();
        int[] sort = insertSort.quickSort(nums);
        for (int i :
                sort) {
            System.out.println(i);
        }
    }
}
