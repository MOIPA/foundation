package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetZeroes73 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}
        };
        new Solution().setZeroes2(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private static class Solution {
        public void setZeroes(int[][] matrix) {
            Set<Integer> cols = new HashSet();
            Set<Integer> rows = new HashSet();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0) {
                        cols.add(j);
                        rows.add(i);
                    }
                }
            }
            for (Integer col : cols) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][col] = 0;
                }
            }
            for (Integer row : rows) {
                for (int i = 0; i < matrix[row].length; i++) {
                    matrix[row][i] = 0;
                }
            }
        }

        // O(1) space的算法 遍历 如果一个数为0 将对应第一行和第一列的值置为0a
        public void setZeroes2(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
            // set to zero
            for (int i = 1; i < m; i++)
                for (int j = 1; j < n; j++)
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            // 看第一行 和 第一列是否需要置空
            boolean isRow = false;
            boolean isCol = false;
            for (int i = 0; i < m; i++) {
                if (matrix[i][0] == 0) isRow = true;
            }
            for (int i = 0; i < n; i++) {
                if (matrix[0][i] == 0) isCol = true;
            }
            if (isRow)
                for (int i = 0; i < m; i++) {
                    matrix[i][0] = 0;
                }
            if (isCol)
                for (int i = 0; i < n; i++) {
                    matrix[0][i] = 0;
                }
        }

    }
}
