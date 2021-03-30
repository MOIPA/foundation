package com.ds.sort;

public class MergeSort {
    public int[] mergeSort(int[] nums) {
        doMergeSort(nums,0,nums.length-1);
        return nums;
    }

    private void doMergeSort(int[] nums, int start, int end) {
        if (start>=end)return;
        int mid = (start + end) / 2;
        doMergeSort(nums, start, mid);
        doMergeSort(nums, mid+1, end);
        // 二路归并 start-mid和mid+1-end 两个数组归并
        int[] results = new int[end - start + 1];
        int count=0;
        for (int i = start, j = mid + 1; i <= mid && j <= end; ) {
            if (nums[i] <= nums[j]) results[count++] = nums[i++];
            else results[count++] = nums[j++];
            if (i > mid)
                for (; j <= end; j++) {
                    results[count++] = nums[j];
                }
            if (j > end)
                for (; i <= mid; i++) {
                    results[count++] = nums[i];
                }
        }
        // 原数组拷贝
        count=0;
        for (int i = start; i <= mid; i++)
            nums[i] = results[count++];
        for (int i = mid+1; i <= end; i++)
            nums[i] = results[count++];
    }
}
class testMergeSort {
    public static void main(String[] args) {
        int[] nums = {45, 38, 65, 97, 76, 13, 27, 49};
        MergeSort insertSort = new MergeSort();
        int[] sort = insertSort.mergeSort(nums);
        for (int i :
                sort) {
            System.out.println(i);
        }
    }
}
