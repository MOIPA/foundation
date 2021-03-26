package com.leetcode;

import java.util.Arrays;

/**
 * @author tr
 * @date 2020/8/24 17:44
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicates(new int[]{1, 1, 2}));
    }

    private static class Solution {
        public int removeDuplicates(int[] nums) {
            int[] newNums = Arrays.stream(nums).sorted().distinct().toArray();
            for (int i = 0; i < newNums.length; i++) {
                nums[i] = newNums[i];
            }
            return newNums.length;
        }
    }
}
