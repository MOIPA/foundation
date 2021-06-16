package com.leetcode;

import java.util.Arrays;

public class MaximalRectangle85 {
    public static void main(String[] args) {
        System.out.println(new Solution().maximalRectangle2(new char[][]{
//                {'1', '1', '1', '1', '1', '1', '1', '1'}, {'1', '1', '1', '1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1', '0', '0', '0'}, {'0', '1', '1', '1', '1', '0', '0', '0'}
//                {'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}
                {'1', '0', '1', '1', '0', '1'}, {'1', '1', '1', '1', '1', '1'}, {'0', '1', '1', '0', '1', '1'}, {'1', '1', '1', '0', '1', '0'}, {'0', '1', '1', '1', '1', '1'}, {'1', '1', '0', '1', '1', '1'}
        }));
    }

    /**
     * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     * <p>
     * 思路：暴力 遍历每个1 只往右边和下边找宽高算面积，二维数组存每个1的最大面积，
     */
    private static class Solution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) return 0;
            int max = 0;
            boolean flag;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] != '0') {
                        int len = 0, height = 0;
                        for (int z = j; z < matrix[0].length; z++) {
                            height = 0;
                            if (matrix[i][z] != '0') {
                                len++;
                                // 有了最大宽度找 最大高度
                                flag = false;
                                for (int l = i; l < matrix.length; l++) {
                                    for (int k = 0; k < len; k++)
                                        if (matrix[l][j + k] == '0') {
                                            flag = true;
                                            break;
                                        }
                                    if (flag) break;
                                    height++;
                                }
                                // 计算这时候的面积 存入面积
                                int area = len * height;
                                max = Math.max(max, area);
                            } else break;
                        }

                    }

                }
            }
            return max;
        }

        public int maximalRectangle2(char[][] matrix) {
            // 思路： 和84题一样
            if (matrix.length == 0) return 0;
            int m = matrix.length;
            int n = matrix[0].length;
            int[] left = new int[n];
            int[] right = new int[n];
            int[] height = new int[n];
            Arrays.fill(left, 0);
            Arrays.fill(right, 0);
            Arrays.fill(height, 0);
            int maxA = 0;
            for (int i = 0; i < m; i++) {
                int cur_left = 0, cur_right = n;
                for (int j = 0; j < n; j++) { // compute height (can do this from either side)
                    if (matrix[i][j] == '1') height[j]++;
                    else height[j] = 0;
                }
                for (int j = 0; j < n; j++) { // compute left (from left to right)
                    if (matrix[i][j] == '1') left[j] = Math.max(left[j], cur_left);
                    else {
                        left[j] = 0;
                        cur_left = j + 1;
                    }
                }
                // compute right (from right to left)
                for (int j = n - 1; j >= 0; j--) {
                    if (matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                    else {
                        right[j] = n;
                        cur_right = j;
                    }
                }
                // compute the area of rectangle (can do this from either side)
                for (int j = 0; j < n; j++)
                    maxA = Math.max(maxA, (right[j] - left[j]) * height[j]);
            }
            return maxA;
        }
    }
}
