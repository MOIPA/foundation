package com.leetcode;

public class BestTimetoBuyandSellStockII122 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit2(new int[]{7, 1, 5, 3, 6, 7}));
    }

    /**
     * 这题不应该是easy  很难想到这个算法和规律
     * <p>
     * 第一个解法： 实际有个规律，就是所有的邻近高点-邻近低点的和 总是最大的！
     */
    private static class Solution {
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            int lowest;
            int highest;
            int i = 0;
            while (i < prices.length - 1) {
                // 找到最低点
                while (i < prices.length - 1 && prices[i] >= prices[i + 1]) i++;
                lowest = prices[i];
                while (i < prices.length - 1 && prices[i] <= prices[i + 1]) i++;
                highest = prices[i];
                maxProfit += highest - lowest;
            }
            return maxProfit;
        }

        /**
         * 牛逼算法  画成图实际所有的收益 不过是所有的上坡的和
         * @param prices
         * @return
         */
        public int maxProfit2(int[] prices) {
            int max = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i]<prices[i+1]) max += prices[i + 1] - prices[i];
            }
            return max;
        }
    }
}
