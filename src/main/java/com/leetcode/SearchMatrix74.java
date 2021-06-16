package com.leetcode;

public class SearchMatrix74 {
    public static void main(String[] args) {
        System.out.println(new Solution().searchMatrix(new int[][]{
                {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}
        }, 11));

    }

    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * <p>
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     */
    private static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int startRow = 0;
            int endRow = matrix.length - 1;
            int startCol = 0;
            int endCol = matrix[0].length - 1;

            // 二分法查行
            while (startRow < endRow) {
                int middleRow = (startRow + endRow) / 2;
                if (matrix[middleRow][0] > target) {
                    endRow = middleRow - 1;
                } else if (matrix[middleRow][0] < target) {
                    if (matrix[middleRow][endCol] < target) startRow = middleRow + 1;
                    else startRow = endRow = middleRow;
                } else if (matrix[middleRow][0] == target) {
                    return true;
                }
            }
            // 二分查找列
            while (startCol <= endCol) {
                int middleCol = (startCol + endCol) / 2;
                if (matrix[startRow][middleCol] == target) {
                    return true;
                } else if (matrix[startRow][middleCol] > target) {
                    endCol = middleCol - 1;
                } else if (matrix[startRow][middleCol] < target) {
                    startCol = middleCol + 1;
                }
            }
            return false;
        }
    }
}
