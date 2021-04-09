package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CombinationSum39 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution().combinationSum(new int[]{2, 7, 6, 3, 5, 1}, 9);
        lists.forEach(System.out::println);
        System.out.println(lists.size());

    }


    /**
     * 题目：用给的数组 拼成目标数，数组的数可以被使用多次
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]]
     * <p>
     * 思路：留一个list用于记录使用过的数，递归，每次遍历数组的数，用target减去，如果为0说明路径可行，添加
     * 但是只这样的话，结果会有重复，为了不重复，
     */
    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new LinkedList<>();
            generatePath(Arrays.stream(candidates).sorted().toArray(), 0, target, new LinkedList<>(), res);
//            res.stream().forEach(x -> x.sort(Comparator.comparingInt(x2 -> x2)));
//            return res.stream().distinct().collect(Collectors.toList());
            return res;
        }

        private void generatePath(int[] candidates, int start, int target, List<Integer> path, List<List<Integer>> allPath) {
            boolean deletedLastPath = false;
            for (int i = start; i < candidates.length; i++) {
                if (target - candidates[i] == 0) {
                    path.add(candidates[i]);
                    allPath.add(new LinkedList<>(path));
                    path.remove(path.size() - 1);
                    path.remove(path.size() - 1);
                    break;
                } else if (target - candidates[i] > 0) {
                    path.add(candidates[i]);
                    generatePath(candidates, i, target - candidates[i], path, allPath);
                }else{
                    if (!deletedLastPath) {
                        // 本层结束后删除本层添加的节点
                        path.remove(path.size() - 1);
                        deletedLastPath = true;
                    }
                }
            }
        }

    }

}
