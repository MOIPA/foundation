package com.leetcode;

import java.util.Arrays;

public class FindFirstandLastPositionofElementinSortedArray34 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().searchRange(new int[]{5, 7, 7, 8, 10}, 8)));
    }

    /**
     * 题目
     * 给定一个排序数组，找出目标的起始位置和终点位置下标
     * <p>
     * 思路：先定位到目标，然后从目标往前和往后看是否连续，记录最后一次连续下标
     * <p>
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     * If target is not found in the array, return [-1, -1].
     * Follow up: Could you write an algorithm with O(log n) runtime complexity?
     */
    static class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums.length == 0) return new int[]{-1, -1};
            int res = binarySearch(nums, 0, nums.length - 1, target);
            if (res == -1) return new int[]{-1, -1};
            int s = res, e = res;
            // 往前查看连续
            while (s > 0 && nums[s] == nums[s - 1]) s--;
            // 往后查看
            while (e < nums.length - 1 && nums[e] == nums[e + 1]) e++;
            return new int[]{s, e};
        }

        private int binarySearch(int[] nums, int start, int end, int target) {
            if (start > end || (start == end && nums[start] != target)) return -1;
            int mid = (start + end) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) return binarySearch(nums, start, mid, target);
            else return binarySearch(nums, mid + 1, end, target);
        }
    }
}
