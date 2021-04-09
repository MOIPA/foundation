package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSumII40 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution().combinationSum(new int[]{1, 1}, 2);
        lists.forEach(System.out::println);
        System.out.println(lists.size());

    }


    /**
     * 题目：用给的数组 拼成目标数，数组的数可以使用一次  39题的加强版
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     * <p>
     * 思路：留一个list用于记录使用过的数，递归，每次遍历数组的数，用target减去，如果为0说明路径可行，添加
     * 但是只这样的话，结果会有重复，为了不重复，
     *
     * 和 39 题对比 ，为了防止重复 先排序，后再在循环中跳过重复字符。
     * 为了每个字符只使用一次，排序后下一层传递的开始值为当前值+1
     *
     * Runtime: 6 ms, faster than 28.62% of Java online submissions for Combination Sum II.
     * Memory Usage: 39.5 MB, less than 22.30% of Java online submissions for Combination Sum II.
     */
    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new LinkedList<>();
            generatePath(Arrays.stream(candidates).sorted().toArray(), 0, target, new LinkedList<>(), res);
            return res;
        }

        private void generatePath(int[] candidates, int start, int target, List<Integer> path, List<List<Integer>> allPath) {
            for (int i = start; i < candidates.length; i++) {
                if (target - candidates[i] == 0) {
                    path.add(candidates[i]);
                    allPath.add(new LinkedList<>(path));
                    path.remove(path.size() - 1);
                    break;
                } else if (target - candidates[i] > 0) {
                    path.add(candidates[i]);
                    generatePath(candidates, i + 1, target - candidates[i], path, allPath);
                    path.remove(path.size() - 1);
                }
                // 为了防止重复 跳到最后一个不一致的
                while (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) i++;
            }
        }

    }

}
