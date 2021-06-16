package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Subsets78 {
    public static void main(String[] args) {
        List<List<Integer>> subsets = new Solution3().subsets(new int[]{1,2,3});
        subsets.forEach(x -> {
            x.forEach(System.out::print);
            System.out.println();
        });
    }

    /**
     * Given an integer array nums of unique elements, return all possible subsets (the power set).
     * <p>
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     */
    private static class Solution {
        public List<List<Integer>> subsets(int[] nums) {

            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            List<List<Integer>> lists = null;
            if (nums.length < 2) {
                lists = new ArrayList<>();
            } else {
                lists = doSplit(list);
            }
            for (int num : nums) {
                lists.add(Arrays.asList(num));
            }
            lists.add(new ArrayList<>());
            return lists;
        }

        List<List<Integer>> doSplit(List<Integer> nums) {
            List<List<Integer>> res = new LinkedList<>();
            if (nums.size() == 2) {
                res.add(Arrays.asList(nums.get(0), nums.get(1)));
                return res;
            }
            int startNum = nums.get(0);
            nums.remove(0);
            nums.forEach(x -> {
                res.add(Arrays.asList(startNum, x));
            });
            List<List<Integer>> subs = doSplit(nums);
            res.addAll(subs);
            subs.forEach(x -> {
                ArrayList<Integer> tmp = new ArrayList<>(x);
                tmp.add(0, startNum);
                res.add(tmp);
            });

            return res;
        }

    }

    /**
     * 回溯法
     */
    private static class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            getAns(nums, 0, new ArrayList<>(), ans);
            return ans;
        }

        private void getAns(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
            ans.add(new ArrayList<>(temp));
            for (int i = start; i < nums.length; i++) {
                temp.add(nums[i]);
                getAns(nums, i + 1, temp, ans);
                temp.remove(temp.size() - 1);
            }
        }
    }
    /**
     * 最牛逼的算法
     */
    private static class Solution3 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            int bit_nums = nums.length;
            int ans_nums = 1 << bit_nums; //执行 2 的 n 次方
            for (int i = 0; i < ans_nums; i++) {
                List<Integer> tmp = new ArrayList<>();
                int count = 0; //记录当前对应数组的哪一位
                int i_copy = i; //用来移位
                while (i_copy != 0) {
                    if ((i_copy & 1) == 1) { //判断当前位是否是 1
                        tmp.add(nums[count]);
                    }
                    count++;
                    i_copy = i_copy >> 1;//右移一位
                }
                ans.add(tmp);

            }
            return ans;
        }
    }
}
