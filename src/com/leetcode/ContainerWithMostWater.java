package com.leetcode;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] array = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        Solution solution = new Solution();
        System.out.println(solution.maxArea(array));
    }

    /**
     * Runtime: 577 ms, faster than 5.03% of Java online submissions for Container With Most Water.
     * Memory Usage: 41.9 MB, less than 5.77% of Java online submissions for Container With Most Water.
     */
    private static class Solution {
        // 暴力算法
        public int maxArea(int[] height) {
            int area = 0;
            int result = 0;
            for (int i = 0; i < height.length; i++)
                for (int j = i + 1; j < height.length; j++) {
                    if (height[i] < height[j]) result = height[i] * (j - i);
                    else result = height[j] * (j - i);
                    if (result > area) area = result;
                }
            return area;
        }
    }
}
