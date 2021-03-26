package com.leetcode;

public class MedianofTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array1 = {1, 3};
        int[] array2 = {2};
        double medianSortedArrays = solution.findMedianSortedArrays(array1, array2);
        System.out.println(medianSortedArrays);

    }

    //Runtime: 2 ms, faster than 99.96% of Java online submissions for Median of Two Sorted Arrays.
    //Memory Usage: 42.2 MB, less than 97.92% of Java online submissions for Median of Two Sorted Arrays.
    private static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {

            int[] target = new int[nums1.length + nums2.length];
            // i与j的和为最终数组的位置
            int i = 0, j = 0;
            while (i + j <= nums1.length + nums2.length) {
                // nums1结束，剩下的nums2进入
                if (i >= nums1.length) {
                    for (; j < nums2.length; j++) target[i + j] = nums2[j];
                    break;
                }
                if (j >= nums2.length) {
                    for (; i < nums1.length; i++) target[i + j] = nums1[i];
                    break;
                }
                if (nums1[i] < nums2[j]) target[i + j] = nums1[i++];
                else target[i + j] = nums2[j++];
            }
//            for (int k = 0; k < target.length; k++) {
//                System.out.println(target[k]);
//            }
            if (target.length%2==0) return (target[target.length / 2] + (float)target[target.length / 2 - 1]) / 2;
            else return target[target.length / 2];

        }
    }
}
