package com.leetcode;

import java.util.Arrays;

public class MergeSortedArray88 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 4, 5, 6, 0};
        int[] b = new int[]{3};
        new Solution().merge(a, 5, b, 1);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 思路：把前m个数放到nums1后面m个上，前面m个当作空的用来存放结果
     */
    private static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (n == 0) return;
            if (m == 0) {
                for (int i = 0; i < nums1.length; i++) {
                    nums1[i] = nums2[i];
                }
                nums1[0] = nums2[0];
                return;
            }
            // 不开辟多余空间的写法且速度为 o(m+n)
            for (int i = m - 1; i >= 0; i--) {
                nums1[i + nums1.length - m] = nums1[i];
            }
            int p1 = nums1.length - m;
            int p2 = 0;
            int pres = 0;
            while (pres < nums1.length) {
                if (p1 == nums1.length) {
                    for (; p2 < n; p2++) {
                        nums1[pres++] = nums2[p2];
                    }
                } else if (p2 == n) {
                    return;
                } else {
                    if (nums1[p1] < nums2[p2]) nums1[pres++] = nums1[p1++];
                    else nums1[pres++] = nums2[p2++];
                }
            }
        }

        public void merge2(int[] nums1, int m, int[] nums2, int n) {
            int p1 = 0;
            int p2 = 0;
            int pres = 0;
            int[] res = new int[nums1.length];
            while (p1 < m || p2 < n) {
                if (p1 == m) {
                    for (; p2 < n; p2++) {
                        res[pres++] = nums2[p2];
                    }
                } else if (p2 == n) {
                    for (; p1 < m; p1++) {
                        res[pres++] = nums1[p1];
                    }
                } else {
                    if (nums1[p1] < nums2[p2]) res[pres++] = nums1[p1++];
                    else res[pres++] = nums2[p2++];
                }
            }
            nums1 = res;
        }
    }
}
