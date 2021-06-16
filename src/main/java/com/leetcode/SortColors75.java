package com.leetcode;

import java.util.Arrays;

public class SortColors75 {
    public static void main(String[] args) {
        int[] tmp = new int[]{2, 0, 2, 1, 1, 0};
        new Solution().sortColors(tmp);
        System.out.println(Arrays.toString(tmp));
    }

    /**
     * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
     * <p>
     * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
     * <p>
     * You must solve this problem without using the library's sort function.
     * <p>
     * 直接使用快排算法
     */
    private static class Solution {
        public void sortColors(int[] nums) {
            int start = 0;
            int end = nums.length - 1;
            doSort(nums, start, end);
        }

        private void doSort(int[] nums, int start, int end) {
            if (start >= end) return;
            int tmp = nums[start];
            int startPoint = start;
            int endPoint = end;
            while (startPoint < endPoint) {
                while (startPoint < endPoint && nums[endPoint] > tmp) endPoint--;
                if (startPoint < endPoint) nums[startPoint++] = nums[endPoint];
                while (startPoint < endPoint && nums[startPoint] < tmp) startPoint++;
                if (startPoint < endPoint) nums[endPoint--] = nums[startPoint];
            }
            nums[startPoint] = tmp;
            doSort(nums, start, startPoint - 1);
            doSort(nums, startPoint + 1, end);
        }

        private void sortColors2(int[] nums) {

        }

    }
}
