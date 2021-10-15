package com.leetcode;

public class BestTimetoBuyandSellStock121 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
    }

    private static class Solution {
        /**
         * 暴力算法  o(n^2)
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int max = 0;
            for (int i = 0; i < prices.length; i++) {
                for (int j = i; j <prices.length; j++) {
                    max = Math.max(prices[j] - prices[i], max);
                }
            }
            return max;
        }
        /**
         * 牛逼算法  o(n)
         */
        public int maxProfit2(int[] prices) {
            int max = 0;
            int lowest = prices[0];
            for (int i = 1; i < prices.length; i++) {
                if (prices[i]>lowest) max = Math.max(max, prices[i] - lowest);
                else lowest = prices[i];
            }
            return max;
        }
    }
}
