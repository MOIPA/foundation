package com.leetcode;

public class FirstMissingPositive41 {
    public static void main(String[] args) {
        System.out.println(new Solution().firstMissingPositive(new int[]{1}));
    }

    /**
     * 问题：Given an unsorted integer array nums, find the smallest missing positive integer.
     * Input: nums = [3,4,-1,1]
     * Output: 2
     * <p>
     * hard 题目 ， 如果直接排序从右往左找不连续的数 则很简单，但是难点是要求时间复杂度 o(n)
     * <p>
     * 思路： 又是一堆抄作业的，说的不明不白，这里使用桶排序即nums[i]=i+1,但是不是直接开辟大数组，而是原地交换，当前的1-n的值放到对应地址，直到当前值超出边界，则到下一个值
     * ，如果数据小于0或者大于length 都不排，如果一个都没得排即nums[i]=i+1，说明0-n都没有 返回length+1即可
     * 如果要交换的数相同，则不能交换，否则死循环。
     */
    private static class Solution {
        public int firstMissingPositive(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                // 换
                if (nums[i] != i + 1 && nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1);
                    i--;
                }
            }
            // 桶排序后的数组顺序遍历不满足nums[i]= i+1的就是
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) return i + 1;
            }
            return nums.length + 1;
        }

        private void swap(int[] nums, int a, int b) {
            int tmp = nums[a];
            nums[a] = nums[b];
            nums[b] = tmp;
        }
    }
}
