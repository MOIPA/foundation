package com.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class SubsetsII90 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution().subsetsWithDup(new int[]{
                1,1, 2, 3
        });
        lists.forEach(x -> {
            x.forEach(System.out::print);
            System.out.println();
        });
    }

    private static class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> res = new LinkedList<>();
            int[] sorted = Arrays.stream(nums).sorted().toArray();
            doSub(sorted, 0, new LinkedList<>(), res);
            return res;
        }

        private void doSub(int[] nums, int posi, List<Integer> current, List<List<Integer>> res) {
            res.add(new LinkedList<>(current));
            for (int i = posi; i < nums.length; i++) {
                if (i > posi && nums[i] == nums[i - 1]) continue;
                current.add(nums[i]);
                doSub(nums, i + 1, current, res);
                current.remove(current.size() - 1);
            }
        }
    }


}
