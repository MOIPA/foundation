package com.leetcode;

import java.util.Arrays;

public class ThreeSumClosest {
    public static void main(String[] args) {

        Solution solution = new Solution();
//        int[] test = {-1, 2, 1, -4};
        int[] test = {0,0,0};
        System.out.println(solution.threeSumClosest(test, 1));

    }

    /**
     * Runtime: 4 ms, faster than 90.13% of Java online submissions for 3Sum Closest.
     * Memory Usage: 38.5 MB, less than 6.67% of Java online submissions for 3Sum Closest
     */
    private static class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int margin = nums[0] + nums[1] + nums[2] - target;
            int ans = margin+target;
            for (int i = 0; i + 2 < nums.length; i++) {
                int result = twoSumClosest(nums, target - nums[i], i + 1) + nums[i];
                if (Math.abs(margin) > Math.abs(result - target)) {
                    margin = result - target;
                    ans = result;
                }
            }
            return ans;
        }

        private int twoSumClosest(int[] nums, int target, int posi) {
            int record = nums[posi] + nums[posi + 1] - target;
            int sum = nums[posi] + nums[posi + 1];
            int margin;
            int left = posi;
            int right = nums.length - 1;
            while (left < right) {
                margin = nums[left] + nums[right] - target;
//                if (Math.abs(record)>Math.abs(temp)) record = temp;
                if (margin == 0) return target;
                else if (margin > 0) {
                    if (Math.abs(record) > Math.abs(margin)) {
                        record = margin;
                        sum = margin + target;
                    }
                    // 右指针左走
                    while (left<right&&nums[right] == nums[right - 1]) right--;
                    right--;
                } else {
                    if (Math.abs(record) > Math.abs(margin)) {
                        record = margin;
                        sum = margin + target;
                    }
                    // 左指针右走
                    while (left<right&&nums[left] == nums[left + 1]) left++;
                    left++;
                }
            }

            return sum;
        }
    }
}
