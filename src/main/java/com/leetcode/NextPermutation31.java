package com.leetcode;

import java.util.Arrays;

public class NextPermutation31 {
    public static void main(String[] args) {
        new Solution().nextPermutation2(new int[]{1, 3, 2});
    }

    /**
     * 全排列 找出比当前排列大的字典序  如 1 2 3 的全排列按照顺序是下面这样的
     * <p>
     * 1，2，3
     * 1，3，2
     * 2，1，3
     * 2，3，1
     * 3，1，2
     * 3，2，1
     * <p>
     * 找出比当前大的，如果当前最大了 则找出最小的，思路：
     * 从最右侧开始往前找比自己小的，找到了就交换，交换后，所有后面的位数要从小到大排序(逆序就可以了  写草稿的时候发现的规律)
     * 如果找不到比自己小的数说明已经是最大情况了，逆序输出
     * <p>
     * Runtime: 5 ms, faster than 5.61% of Java online submissions for Next Permutation.
     * Memory Usage: 39.2 MB, less than 58.74% of Java online submissions for Next Permutation.
     * <p>
     * 8太行  有点慢 毕竟 O(n^2)
     */
    static class Solution {
        public void nextPermutation(int[] nums) {
            // 记录交换位置，交换位置之后的只需逆序输出
            int recordJ = -1;
            int recordI = -1;
            for (int i = nums.length - 1; i >= 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] > nums[j] && j > recordJ) {
                        recordJ = j;
                        recordI = i;
                        break;
                    }
                }
            }
            if (recordJ != -1) {
                swap(nums, recordI, recordJ);
                reverse(nums, recordJ);
            } else reverse(nums, -1);
            System.out.println(Arrays.toString(nums));
        }

        /**
         * leetcode 答案： O(N)
         * 和我的比起来好在可以一次性快速找到要替换的位置 思路：
         * 有规律：如果组是最大的了，那么数据一定是降序的，排的越高，后面几位越是降序，可以在草稿纸上试下，所以有如下方法
         * 从右往左数，如果一个数不是升序的，那么这个数就是要被替换的数，找到要被替换的数后，再从右往左找到比这个数大的数，替换后将替换位置后面的数逆序。
         *
         * @param nums
         */
        public void nextPermutation2(int[] nums) {
            int recordI;
            int recordJ;
            int tempNum = nums[nums.length - 1];
            // 找到要替换的数
            for (recordI = nums.length - 2; recordI >= 0; recordI--) {
                if (nums[recordI] < tempNum) break;
                tempNum = nums[recordI];
            }
            // 找到比这个数大的数
            if (recordI != -1) {
                for (recordJ = nums.length - 1; recordJ > recordI; recordJ--) {
                    if (nums[recordJ] > nums[recordI]) break;
                }
                swap(nums, recordI, recordJ);
                reverse(nums, recordI);
            } else reverse(nums, -1);
            System.out.println(Arrays.toString(nums));
        }

        // 逆序函数 在 posi后逆序
        private void reverse(int[] nums, int posi) {
            int max = (nums.length - posi - 1) / 2;
            for (int i = 1; i <= max; i++) {
                swap(nums, posi + i, nums.length - i);
            }
        }

        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}
