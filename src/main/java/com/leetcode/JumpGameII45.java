package com.leetcode;

import java.util.Arrays;

public class JumpGameII45 {
    public static void main(String[] args) {
        System.out.println(new Solution2().jump(new int[]{2, 3, 1, 1, 4}));
    }

    /**
     * 问题：
     * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     * You can assume that you can always reach the last index.
     * <p>
     * 当前位置值为最大跳跃步数，如何移动最小次数跳到最后
     * <p>
     * 思路：先用动态规划的思想，从低到高，从最后开始算最优跳跃次数 -1表示无法到达终点
     * <p>
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Jump Game II.
     * Memory Usage: 36.3 MB, less than 85.37% of Java online submissions for Jump Game II.
     */
    private static class Solution {
        public int jump(int[] nums) {
            int[] jumpTimes = new int[nums.length];
            Arrays.fill(jumpTimes, -1);
            for (int i = nums.length - 1; i >= 0; i--) {
                if (i == nums.length - 1) {
                    jumpTimes[i] = 0;
                    continue;
                }
                // 每位再遍历后面的次数 选最小的
                int min = jumpTimes[i + 1];
                for (int j = 1; j <= nums[i]; j++) {
                    if (i + j < nums.length && min > jumpTimes[i + j] && jumpTimes[i + j] != -1) min = jumpTimes[i + j];
                }
                jumpTimes[i] = min + 1;
            }
            return jumpTimes[0];
        }
    }

    /**
     * 改造成贪心算法
     * <p>
     * 思路：选择能跳到的范围内的，最远下一次跳跃距离
     * 当前的currentJumpEnd记录能跳到的最远位置，在当前位置和最远位置里面挑出跳的最远的方案：max，i==currentPosi表明已经找到最大跳远距离了
     */
    private static class Solution2 {
        public int jump(int[] nums) {
            int jumpTimes = 0;
            int max = 0;
            int currentJumpEnd = 0;
            // 记录能跳的最远位置
            for (int i = 0; i < nums.length - 1; i++) {
                max = Math.max(max, i + nums[i]);
                if (i == currentJumpEnd) {
                    jumpTimes++;
                    currentJumpEnd = max;
                }
            }
            return jumpTimes;
        }
    }
}
