package com.leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MergeIntervals56 {
    public static void main(String[] args) {
        int[][] merge = new Solution().merge(new int[][]{{1, 4}, {4, 5}});
        Arrays.stream(merge).forEach(x -> {
            System.out.println(Arrays.toString(x));
        });
    }

    /**
     * 题目：合并有交集的集合
     * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     */
    private static class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals.length == 1) return intervals;
//            Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
            List<int[]> sorted = Arrays.stream(intervals).sorted(Comparator.comparingInt(x -> x[0])).collect(Collectors.toList());
            for (int i = 0; i < sorted.size() - 1; i++) {
                if (sorted.get(i)[1] >= sorted.get(i + 1)[0]) {
                    int[] ints = {sorted.get(i)[0], Math.max(sorted.get(i + 1)[1], sorted.get(i)[1])};
                    sorted.set(i, ints);
                    sorted.remove(i + 1);
                    i--;
                }
            }
            return sorted.toArray(new int[0][]);
        }
    }
}
