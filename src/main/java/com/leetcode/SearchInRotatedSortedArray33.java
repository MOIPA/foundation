package com.leetcode;

import java.util.Arrays;

public class SearchInRotatedSortedArray33 {
    public static void main(String[] args) {
        System.out.println(new Solution().search(new int[]{ 2}, 1));
    }

    /**
     * 题目是给定一个未知的数，数组会根据这个数旋转，
     * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
     * <p>
     * 思路：二分法，反正第一个数肯定是比最后一位大，数组二分，筛选出目标数组，第一个数比分组后的最后一个大就是目标数组，
     * 将目标数组再次二分，这个数再比对两个数组的最后一个数，找出最小的所在的数组，直到目标数组为1 记录下标，
     * <p>
     * 找到记录下标后说明确定了分割位置，将数组分割成两个有序数组，然后二分查找即可
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
     * Memory Usage: 38.3 MB, less than 52.83% of Java online submissions for Search in Rotated Sorted Array.
     *
     */
    static class Solution {
        public int search(int[] nums, int target) {
            // 定位到分割位置
            int posi = doSplitArray(nums, nums[0], 0, nums.length - 1);
            // 二分查找即可
            int res = doBinarySearch(nums, 0, posi - 1, target);
            if (res != -1) return res;
            else return doBinarySearch(nums, posi, nums.length - 1, target);
        }

        // 找出分割位置
        private int doSplitArray(int[] nums, int n, int start, int end) {
            if (start > end) return 0;
            if (start == end) return start;
            int mid = (start + end) / 2;
            // 两个数组 start-mid & mid-end 找出其中最后一个数比n小的
            if (n <= nums[start] && n < nums[end]) return 0;
            else if (n > nums[start] && n > nums[end]) return start;
            else if (n <= nums[start] && n > nums[end]) {
                // 判断分割点在左右哪个数组
                if (n <= nums[mid]) return doSplitArray(nums, n, mid + 1, end);
                else return doSplitArray(nums, n, start, mid);
            } else return -1;
        }

        /**
         * @param nums
         * @param start
         * @param end
         * @return
         */
        private int doBinarySearch(int[] nums, int start, int end, int target) {
            if (start > end || (start == end && nums[start] != target)) return -1;
            int mid = (start + end) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) return doBinarySearch(nums, start, mid, target);
            else return doBinarySearch(nums, mid + 1, end, target);
        }
    }
}

