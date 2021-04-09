package com.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RotateImage48 {
    public static void main(String[] args) {
        new Solution().rotater2(new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        });
    }

    /**
     * Runtime: 3 ms, faster than 100.00% of Java online submissions for Rotate Image.
     * Memory Usage: 39 MB, less than 48.87% of Java online submissions for Rotate Image.
     */
    private static class Solution {
        public void rotate(int[][] matrix) {
            // 从第一列开始
            for (int i = 0; i < matrix.length; i++) {
                for (int j = i; j < matrix.length; j++) {
                    swap(matrix, i, j, j, i);
                }
                for (int j = 0; j < matrix.length / 2; j++) {
                    swap(matrix, i, j, i, matrix.length - 1 - j);
                }
            }
            Arrays.stream(matrix).forEach(x -> System.out.println(Arrays.toString(x)));
        }

        private void swap(int[][] matrix, int i, int j, int newI, int newJ) {
            int tmp = matrix[newI][newJ];
            matrix[newI][newJ] = matrix[i][j];
            matrix[i][j] = tmp;
        }

        public void rotater2(int[][] matrix) {
            // 从第一列开始
            StringBuffer out = new StringBuffer();
            out.append("[");
            // 列
            for (int i = 0; i < matrix.length; i++) {
                out.append("[");
                for (int j = matrix.length - 1; j >= 0; j--) {
                    out.append(matrix[j][i]);
                    if (j!=0) out.append(",");
                }
                out.append("]");
                if (i != matrix.length-1) out.append(",");
            }
            out.append("]");
            System.out.println(out.toString());
        }
    }
}
