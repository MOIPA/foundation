package com.leetcode;

public class SearchinRotatedSortedArrayII81 {
    public static void main(String[] args) {
        System.out.println(new Solution().search2(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
    }

    /**
     * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
     * <p>
     * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
     * <p>
     * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
     */
    private static class Solution {
        public boolean search(int[] nums, int target) {
            int posi = findSplitPosi(nums, 0, nums.length - 1);
            System.out.println(posi);
            return false;
        }

        int findSplitPosi(int[] nums, int start, int end) {
            if (start > end - 1) return 0;
            int mid = (start + end) / 2;
            if (nums[mid] > nums[mid + 1]) return mid + 1;
            else {
                if (nums[mid] > nums[nums.length - 1]) return findSplitPosi(nums, mid + 1, end);
                else return findSplitPosi(nums, start, mid);
            }
        }

        /**
         * 一次性找出 就不再像上一个一样先找出两个顺序数组了
         *
         * @param nums
         * @param target
         * @return
         */
        public boolean search2(int[] nums, int target) {
            return doSearch(nums, target, 0, nums.length - 1);
        }

        private boolean doSearch(int[] nums, int target, int start, int end) {
            if (start > end) return false;
            boolean isSorted = nums[start] < nums[end];
            if (isSorted && target > nums[start] && target > nums[end]) return false;
            int mid = (start + end) / 2;
            if (nums[mid] == target) return true;
                // 在左区间？ 如果区间非顺序，那么也可能在右区间
            else if (nums[mid] > target) {
                if (isSorted) return doSearch(nums, target, start, mid - 1);
                else return doSearch(nums, target, start, mid - 1) || doSearch(nums, target, mid + 1, end);
            }
            // 右区间？
            else {
                if (isSorted) return doSearch(nums, target, mid + 1, end);
                else return doSearch(nums, target, start, mid - 1) || doSearch(nums, target, mid + 1, end);
            }
        }
    }
}
