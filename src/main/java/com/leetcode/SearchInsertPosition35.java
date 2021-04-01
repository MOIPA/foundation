package com.leetcode;

public class SearchInsertPosition35 {
    public static void main(String[] args) {
        System.out.println(new Solution().searchInsert(new int[]{1, 3, 5, 6}, 5));
    }

    /**
     * 问题：Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     * Input: nums = [1,3,5,6], target = 2
     * Output: 1
     * <p>
     * 思路：不仅得找到下标，如果没找到还得找到应该插入的下标，二分法，直到start==end的时候判断 target<=nums[start]的话 返回start，否则返回 start-1
     */
    static class Solution {
        public int searchInsert(int[] nums, int target) {
            return doBinarySearch(nums, 0, nums.length - 1, target);
        }

        public int doBinarySearch(int[] nums, int start, int end, int target) {
            if (start > end) return -1;
            if (start == end) {
                if (target <= nums[start]) return start;
                else return start + 1;
            }
            int mid = (start + end) / 2;
            if (nums[mid]==target) return mid;
            else if (nums[mid] > target) return doBinarySearch(nums, start, mid, target);
            else return doBinarySearch(nums, mid + 1, end, target);
        }
    }
}
