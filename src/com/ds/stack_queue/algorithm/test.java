package com.ds.stack_queue.algorithm;

public class test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(ints[0]);
    }

}
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++) {
            for (int j = i + 1; j < nums.length; j++)
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
        }
        return null;
    }
}
