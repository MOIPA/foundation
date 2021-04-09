package com.leetcode;

public class MaximumSubarray53 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int[]{1}));
    }

    /**
     * 题目：
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * <p>
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     * <p>
     * 动态规划：将问题切割到小问题上，从第一个元素开始，dp[i]表示以i结尾的最大连续子序和大小
     * dp[i] = max{nums[i],dp[i-1]+nums[i]}
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Subarray.
     * Memory Usage: 38.4 MB, less than 99.75% of Java online submissions for Maximum Subarray.
     *
     */
    private static class Solution {
        public int maxSubArray(int[] nums) {
            // 记录上一个最大子序和
            int lastMaxSubArray = nums[0];
            // 全部最大子序和
            int max = lastMaxSubArray;
            for (int i = 1; i < nums.length; i++) {
                lastMaxSubArray = Math.max(lastMaxSubArray + nums[i], nums[i]);
                max = Math.max(max, lastMaxSubArray);
            }
            return max;
        }
    }
}
