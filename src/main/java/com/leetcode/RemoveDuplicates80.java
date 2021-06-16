package com.leetcode;

import java.util.Arrays;

public class RemoveDuplicates80 {
    public static void main(String[] args) {
        int[] tmp = new int[]{1, 2, 2, 2, 3, 3};
        System.out.println(new Solution().removeDuplicatesEasy(tmp));
        System.out.println(Arrays.toString(tmp));
    }

    private static class Solution {
        public int removeDuplicates(int[] nums) {

            int point = 1;
            int same = nums[0];
            int sameTimes = 0;
            int len = nums.length;
            while (point < len) {
                if (nums[point] == same) sameTimes++;
                else {
                    same = nums[point];
                    sameTimes = 0;
                }
                if (sameTimes >= 2) {
                    for (int i = point + 1; i < len; i++) {
                        nums[i - 1] = nums[i];
                    }
                    len--;
                    point--;
                }
                point++;
            }
            return len;
        }

        /**
         * Success
         * Details
         * Runtime: 1 ms, faster than 16.69% of Java online submissions for Remove Duplicates from Sorted Array II.
         * Memory Usage: 38.6 MB, less than 99.46% of Java online submissions for Remove Duplicates from Sorted Array II.
         * Next challenges:
         *
         * @param nums
         * @return
         */
        public int removeDuplicatesFaster(int[] nums) {
            // 差距 默认为-1，如果这一次和上一次相等 差值为0 如果这一次和上一次再相等差值++ 如果这一次和上一次不等，判断差距，移动，然后重设为-1；
            int range = -1;
            int point = nums.length - 2;
            int len = nums.length;
            while (point >= 0) {
                if (nums[point] == nums[point + 1]) range++;
                if (nums[point] != nums[point + 1] || point == 0) {
                    if (range >= 1) {
                        len -= range;
                        if (point == 0 && nums[0] == nums[1]) point--;
                        for (int i = point + 1; i < len; i++) {
                            nums[i] = nums[i + range];
                        }
                    }
                    range = -1;
                }
                point--;
            }
            return len;
        }

        public int removeDuplicatesEasy(int[] nums) {
                int i = 0;
                for (int n : nums)
                    if (i < 2 || n > nums[i-2])
                        nums[i++] = n;
                return i;
        }
    }
}
