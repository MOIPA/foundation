package com.ds.sort;

/**
 * @author TR
 * @content 桶排序：不写了，应该不考
 */
public class BucketSort {
    public int[] bucketSort(int[] nums) {

        return nums;
    }
}
class testBucketSort {
    public static void main(String[] args) {
        int[] nums = {45, 38, 65, 97, 76, 13, 27, 49};
        BucketSort insertSort = new BucketSort();
        int[] sort = insertSort.bucketSort(nums);
        for (int i :
                sort) {
            System.out.println(i);
        }
    }
}
