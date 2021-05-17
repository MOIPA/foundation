package com.leetcode;

public class UniquePaths62 {
    public static void main(String[] args) {
        System.out.println(new Solution().uniquePaths(7, 3));
    }

    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * <p>
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * <p>
     * How many possible unique paths are there?
     * <p>
     * Input: m = 3, n = 2
     * Output: 3
     * Explanation:
     * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
     * 1. Right -> Down -> Down
     * 2. Down -> Down -> Right
     * 3. Down -> Right -> Down
     */
    private static class Solution {
        public int uniquePaths(int m, int n) {
            int[][] matrix = new int[m][n];
            for (int i = 0; i < n; i++) {
                matrix[m - 1][i] = 1;
            }
            for (int i = 0; i < m; i++) {
                matrix[i][n - 1] = 1;
            }
            for (int i = m - 2; i >= 0; i--) {
                for (int j = n - 2; j >= 0; j--) {
                    matrix[i][j] = matrix[i + 1][j] + matrix[i][j + 1];
                }
            }
            return matrix[0][0];
        }
    }
}
