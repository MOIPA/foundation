package com.leetcode;

public class UniquePathsII63 {
    public static void main(String[] args) {
        System.out.println(new Solution().uniquePathsWithObstacles(new int[][]{
                {0}
        }));
    }


    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * <p>
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * <p>
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * <p>
     * An obstacle and space is marked as 1 and 0 respectively in the grid.
     */
    private static class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            if (m == 1 && n == 1) {
                if (obstacleGrid[0][0] == 0) return 1;
                else return 0;
            }
            if (obstacleGrid[m - 1][n - 1] == 1) return 0;
            for (int i = n - 2; i >= 0; i--) {
                if (obstacleGrid[m - 1][i] == 1) obstacleGrid[m - 1][i] = 0;
                else {
                    if (i == n - 2) obstacleGrid[m - 1][i] = 1;
                    else obstacleGrid[m - 1][i] = obstacleGrid[m - 1][i + 1];
                }
            }
            for (int i = m - 2; i >= 0; i--) {
                if (obstacleGrid[i][n - 1] == 1) obstacleGrid[i][n - 1] = 0;
                else {
                    if (i == m - 2) obstacleGrid[i][n - 1] = 1;
                    else obstacleGrid[i][n - 1] = obstacleGrid[i + 1][n - 1];
                }
            }
            for (int i = m - 2; i >= 0; i--) {
                for (int j = n - 2; j >= 0; j--) {
                    if (obstacleGrid[i][j] == 1) obstacleGrid[i][j] = 0;
                    else {
                        obstacleGrid[i][j] = obstacleGrid[i + 1][j] + obstacleGrid[i][j + 1];
                    }
                }
            }
            return obstacleGrid[0][0];
        }
    }
}
