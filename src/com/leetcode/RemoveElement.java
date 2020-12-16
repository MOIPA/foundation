package com.leetcode;

/**
 * @author tr
 * @date 2020/8/25 14:20
 */
public class RemoveElement {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = {1, 2, 2, 3, 4};
        int len = solution.removeElement(ints, 2);
        for (int i = 0; i < len; i++) {
            System.out.println(ints[i]);
        }

    }
    private static class Solution{
        public int removeElement(int[] nums, int val) {
            int count = 0;
            for (int i = 0; i+count < nums.length; i++) {
                if (nums[i]==val){
                    // 所有剩下数往前移动
                    for (int j = i; j < nums.length-count-1; j++) {
                        nums[j] = nums[j + 1];
                    }
                    i--;
                    count++;
                }
            }
            return nums.length-count;
        }
    }
}
