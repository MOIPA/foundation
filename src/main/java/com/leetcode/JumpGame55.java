package com.leetcode;

public class JumpGame55 {
    public static void main(String[] args) {
        System.out.println(new Solution().canJump(new int[]{2, 1, 1, 1, 4}));
    }

    /**
     * 题目：判断是否能到终点，和JumpGameII54差不多 这个是简单版本 都用贪心算法即可
     * <p>
     * Runtime: 1 ms, faster than 83.87% of Java online submissions for Jump Game.
     * Memory Usage: 40.8 MB, less than 75.58% of Java online submissions for Jump Game.
     */
    private static class Solution {
        public boolean canJump(int[] nums) {
            // 当前跳跃的最远点
            int currentMaxJump = 0;
            // 可以跳的最远步数 对比取最大值
            int canMaxJump = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                canMaxJump = Math.max(nums[i] + i, canMaxJump);
                // 遍历完成 开始跳
                if (i == currentMaxJump) {
                    currentMaxJump = canMaxJump;
                    canMaxJump = 0;
                }
            }
            return currentMaxJump >= nums.length - 1;

        }
    }
}
