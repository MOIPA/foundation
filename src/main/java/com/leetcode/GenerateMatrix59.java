package com.leetcode;

import java.util.Arrays;

public class GenerateMatrix59 {
    public static void main(String[] args) {
        int[][] ints = new Solution().generateMatrix(100);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    /**
     * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
     * <p>
     * Input: n = 3
     * Output: [[1,2,3],[8,9,4],[7,6,5]]
     */
    private static class Solution {
        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];
            doGenerate(matrix, 0, n, 1);
            return matrix;
        }

        /**
         * @param matrix 要生产的矩阵
         * @param start  起始位置
         * @param nums   填充数量
         * @param num    填充的数字
         */
        public void doGenerate(int[][] matrix, int start, int nums, int num) {
            // nums <=0 结束
            if (nums <= 0) return;
            // 填充第一行
            for (int i = 0; i < nums; i++) {
                matrix[start][start + i] = num++;
            }
            for (int i = 0; i < nums - 1; i++) {
                matrix[start + i + 1][start + nums - 1] = num++;
            }
            for (int i = 0; i < nums - 1; i++) {
                matrix[start + nums - 1][start + nums - 2 - i] = num++;
            }
            for (int i = 0; i < nums - 2; i++) {
                matrix[start + nums - 2 - i][start] = num++;
            }
            doGenerate(matrix, ++start, nums - 2, num);
        }
    }
}
