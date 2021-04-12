package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class MergeIntervals56 {
    public static void main(String[] args) {
        new Solution().merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
    }

    /**
     * 题目：合并有交集的集合
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     */
    private static class Solution {
        public int[][] merge(int[][] intervals) {
            Integer[] objects = (Integer[]) Arrays.stream(intervals).sorted(Comparator.comparingInt(x -> x[0])).toArray();
            return intervals;
        }
    }
}
