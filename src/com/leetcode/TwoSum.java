package com.leetcode;

public class TwoSum {

    public static void main(String[] args) {

        //test two sum
        int[] nums = {2, 7, 11, 15};
        for (int i :
                towSum(nums, 9)) {
            System.out.println(i);
        }
    }

    /**
     * Given nums = [2, 7, 11, 15], target = 9,
     *
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     * @param nums
     * @param target
     * @return
     */
    public static int[] towSum(int[] nums, int target) {
        int left = -1;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            left = target - nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (left == nums[j]) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
    public static int[] towSum2(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0]=i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}


