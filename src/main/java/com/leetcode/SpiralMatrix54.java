package com.leetcode;

import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix54 {
    public static void main(String[] args) {
        System.out.println(new Solution().spiralOrder(new int[][]{{2, 5, 8}, {4, 0, -1}}));
    }

    /**
     * 问题：
     * Given an m x n matrix, return all elements of the matrix in spiral order.
     * <p>
     * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [1,2,3,6,9,8,7,4,5]
     *
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix.
     * Memory Usage: 37 MB, less than 66.66% of Java online submissions for Spiral Matrix.
     */
    private static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> list = new LinkedList<>();
            doSpiralARound(matrix, 0, 0, matrix[0].length, matrix.length, list);
            return list;
        }

        private void doSpiralARound(int[][] matrix, int x, int y, int length, int height, List<Integer> res) {
            if (length <= 0 || height <= 0 || x > matrix.length - 1 || y > matrix[0].length - 1) return;
            // 上行
            for (int i = 0; i < length; i++) {
                res.add(matrix[x][y + i]);
            }
            // 右列
            for (int i = 1; i < height; i++) {
                res.add(matrix[x + i][y + length - 1]);
            }
            if (height > 1) {
                // 下行
                for (int i = length - 2; i >= 0; i--) {
                    res.add(matrix[x + height - 1][y + i]);
                }
                // 左列
                if (length > 1) for (int i = height - 2; i > 0; i--) {
                    res.add(matrix[x + i][y]);
                }
            }
            doSpiralARound(matrix, x + 1, y + 1, length - 2, height - 2, res);
        }
    }
}
