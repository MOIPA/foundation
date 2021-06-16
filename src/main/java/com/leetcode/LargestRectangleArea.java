package com.leetcode;

import java.util.Arrays;
import java.util.Map;

public class LargestRectangleArea {
    public static void main(String[] args) {
        System.out.println(new Solution().largestRectangleArea(new int[]{
               3,6,5,7,4,8,1,0
        }));
    }

    /**
     * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
     * <p>
     * 思路：很简单，先准备一个数组用来存放每个数的最大面积，然后从第一个开始，先看数组有没有没有再动，如果右边的小于左边，那么第一个的最大面积为右边的数的最大面积，
     * 去算右边的最大面积且存储在数组中，
     */
    private static class Solution {
        public int largestRectangleArea(int[] heights) {
            int[] areas = new int[heights.length];
            Arrays.fill(areas, 0);
            int max = 0;
            for (int i = 0; i < heights.length; i++) {
                max = Math.max(max, doFindArea(heights, i, areas));
            }
            return max;
        }

        /**
         * 找到这个位置的最大面积
         *
         * @param heights
         * @param posi
         * @param areas
         * @return
         */
        private int doFindArea(int[] heights, int posi, int[] areas) {
            // 当前最大面积不存在，开始查
            if (areas[posi] == 0) {
                areas[posi] = heights[posi];
                int i = posi, j = posi;
                int leftMax = 0;
                int rightMax = 0;
                // 往左走，找到左边比自己小的最大值，本身区域也增加
                while (i > 0) {
                    i--;
                    // 左边的比当前的高
                    if (heights[i] > heights[posi]) areas[posi] += heights[posi];
                    // 往左走的时候如果左边和自己一样 那么值也一样
                    else if (heights[i] == heights[posi]) {
                        areas[posi] = areas[i];
                        return areas[posi];
                    }
                    else {
                        // 左边的比当前低
                        // 先看左边是否有最大面积，没有就算
                        if (areas[i] == 0) doFindArea(heights, i, areas);
                        leftMax = areas[i];
                        break;
                    }
                }
                // 往右走，找到右边比自己小的最大值，本身区域也增加
                while (j < heights.length - 1) {
                    j++;
                    if (heights[j] >= heights[posi]) areas[posi] += heights[posi];
                    else {
                        if (areas[j] == 0) doFindArea(heights, j, areas);
                        rightMax = areas[j];
                        break;
                    }
                }
                // 现在有了本身的大小，有了遇到的两个比自身小的数的区域最大值
                areas[posi] = Math.max(Math.max(areas[posi], leftMax), rightMax);
            }
            return areas[posi];
        }
    }
}
