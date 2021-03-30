package com.leetcode;

import java.lang.reflect.Array;
import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {

//        int[] test = {-1, 0, 1, 2, -1, -4};
        int[] test = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
//        int[] test = {0,0,0,0};
        Solution2 solution = new Solution2();
        List<List<Integer>> lists = solution.threeSum(test);
        Iterator<List<Integer>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            List<Integer> next = iterator.next();
            Iterator<Integer> subIterator = next.iterator();
            while (subIterator.hasNext())
                System.out.print(subIterator.next() + "  ");
            System.out.println();
        }

    }

    /**
     * too slow abandoned
     */
    private static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            LinkedList<List<Integer>> results = new LinkedList<>();
            LinkedList<Integer> set = new LinkedList<>();
            for (int i = 0; i < nums.length; i++)
                for (int j = i + 1; j < nums.length; j++) {
                    if (ifContains(nums[i], nums[j], results)) continue;
                    for (int k = j + 1; k < nums.length; k++)
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            set.add(nums[i]);
                            set.add(nums[j]);
                            set.add(nums[k]);
                            results.add(set);
                            set = new LinkedList<>();
                            break;
                        }
                }
            return results;
        }

        private boolean ifContains(int a, int b, LinkedList<List<Integer>> results) {
            Iterator<List<Integer>> iterator = results.iterator();
            while (iterator.hasNext()) {
                List<Integer> next = iterator.next();
                // point a和b一样 且next里只存在一个
                if (a == b && next.contains(a)) {
                    next.remove((Integer) a);
                    //存在两个
                    if (next.remove((Integer) a)) {
                        next.add(a);
                        next.add(a);
                        return true;
                    }
                    next.add(a);
                }
                if (next.indexOf(a) != next.indexOf(b) && next.indexOf(a) != -1 && next.indexOf(b) != -1) return true;

            }
            return false;
        }
    }

    /**
     * 使用 twoSum2就快很多
     *
     * Runtime: 20 ms, faster than 87.90% of Java online submissions for 3Sum.
     * Memory Usage: 46 MB, less than 94.35% of Java online submissions for 3Sum.
     */
    private static class Solution2 {

        /**
         * @param nums
         * @return
         */
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> results = new LinkedList<>();
            Arrays.sort(nums);
            for (int i = 0; i + 2 < nums.length; i++) {
                if (nums[i] > 0) break; //skip
                //two sum 只能往后找 否则重复
                twoSum2(nums, -nums[i], i + 1, nums[i], results);
                // 下一个不重复数之前
                while (nums[i] == nums[i + 1] && i + 2 < nums.length) i++;

            }
            return results;
        }

        /**
         * @param nums
         * @param target
         * @param posi    开始找的位置
         * @param results
         * @return 所有满足的数
         */
        private void twoSum(int[] nums, int target, int posi, int thirdNum, List<List<Integer>> results) {
//            List<int[]> results = new LinkedList<>();
            List<Integer> set = new ArrayList<>();
            // 数组顺序，从第一个数开始找，target变为target-nums[i]
            for (int i = posi; i + 1 < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (target - nums[i] == nums[j]) {
                        set.add(nums[i]);
                        set.add(nums[j]);
                        set.add(thirdNum);
                        results.add(set);
                        set = new ArrayList<>();
                        break;
                    }
                }
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;

            }
        }

        /**
         * 上个太慢 使用双指针改善，一个指向头 一个指向尾，值满足则进集合
         *
         * @param nums
         * @param target
         * @param posi
         * @param thirdNum
         * @param results
         */
        private void twoSum2(int[] nums, int target, int posi, int thirdNum, List<List<Integer>> results) {
            int left = posi;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    results.add(Arrays.asList(nums[left], nums[right], thirdNum));
                    while(left<right&&nums[left]==nums[left]+1)left++;
                    while(left<right&&nums[right-1]==nums[right])right--;
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < target) {
                    while(left<right&&nums[left]==nums[left]+1)left++;
                    left++;
                } else {
                    while(left<right&&nums[right-1]==nums[right])right--;
                    right--;
                }
            }
        }

    }
}
