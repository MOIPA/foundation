package com.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class BestTimetoBuyandSellStockIII123 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit4(new int[]{
                3,3,50,0,3,8,100
        }));
    }

    /**
     * dp  从后往前 记录当日的所有可能的操作次数和赚钱数 (2,6) 当日操作两次赚6元
     * 例子：3,3,50,0,0,3,1,4
     * 最后一天看res[7]:(0,0)
     * res[6]:(0,0),(1,3)
     * res[5]:(0,0),(1,1),(1,3) 合并 为 (0,0),(1,3)
     * res[4]:(0,0),(1,3),(1,1),(1,4),[(1,3)+res[6] : (0,0),(1,4),(2,6)
     * res[3]:(0,0),(1,4),
     */
    private static class Solution {
        public int maxProfit(int[] prices) {
            List<List<int[]>> res = new LinkedList<>();
            for (int i = 0; i < prices.length; i++) {
                res.add(new LinkedList<>());
            }
            res.get(prices.length - 1).add(new int[]{0, 0});
            // 处理
            for (int i = prices.length - 2; i >= 0; i--) {
                // 后一个的记录
                List<int[]> current = res.get(i);
                List<int[]> latter = res.get(i + 1);
                current.addAll(latter);
                for (int j = i + 1; j < prices.length; j++) {
                    if (prices[j] - prices[i] > 0) {
                        // 这一次操作产生了收益 和后面的数的收益结合起来
                        int profit = prices[j] - prices[i];
                        if (j == prices.length - 1) res.get(i).add(new int[]{1, profit});
                        else {
                            for (int[] item : res.get(j + 1)) {
                                // 本次产生的利润和操作数+后面的
                                current.add(new int[]{1 + item[0], profit + item[1]});
                            }
                        }
                    }
                }
                // 计算完毕 本次全部收益结果后融合 剔除操作数大于2的
                current.removeIf(next -> next[0] > 2);
            }
            return res.get(0).stream().max(Comparator.comparingInt(x -> x[1])).get()[1];
        }

        /**
         * 实际只要记录一次操作和二次操作的最大收益即可
         *
         * @param prices
         * @return
         */
        public int maxProfit2(int[] prices) {
            int[][] record = new int[2][prices.length];
            for (int i = prices.length - 2; i >= 0; i--) {
                int j = i + 1;
                while (j < prices.length) {
                    while (j < prices.length - 1 && prices[j] < prices[j + 1]) j++;
                    if (prices[j] - prices[i] > record[0][i]) {
                        record[0][i] = prices[j] - prices[i];
                        // 算第二次操作
                        if (j < prices.length - 1) {
                            record[1][i] = Math.max(record[1][i], record[0][j + 1] + record[0][i]);
                        }
                    }
                    j++;
                }
                record[0][i] = Math.max(record[0][i], record[0][i + 1]);
                record[1][i] = Math.max(record[1][i], record[1][i + 1]);
            }
            return Math.max(record[0][0], record[1][0]);
        }


        public int maxProfit3(int[] prices) {
            if (prices.length == 0) {
                return 0;
            }
            int K = 2;
            int[][] dp = new int[prices.length][K + 1];
            for (int k = 1; k <= K; k++) {
                for (int i = 1; i < prices.length; i++) {
                    int min = Integer.MAX_VALUE;
                    //找出第 0 天到第 i 天 prices[buy] - dp[buy][k - 1] 的最小值
                    for (int buy = 0; buy <= i; buy++) {
                        min = Math.min(prices[buy] - dp[buy][k - 1], min);
                    }
                    //比较不操作和选择一天买入的哪个值更大
                    dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min);
                }
            }
            return dp[prices.length - 1][K];
        }

        public int maxProfit4(int[] prices) {
            if (prices.length == 0) return 0;
            //进行初始化，第一天 s1 将股票买入，其他状态全部初始化为最小值
            int s1 = -prices[0], s2 = Integer.MIN_VALUE, s3 = Integer.MIN_VALUE, s4 = Integer.MIN_VALUE;

            for (int i = 1; i < prices.length; ++i) {
                s1 = Math.max(s1, -prices[i]); //买入价格更低的股
                s2 = Math.max(s2, s1 + prices[i]); //卖出当前股，或者不操作
                s3 = Math.max(s3, s2 - prices[i]); //第二次买入，或者不操作
                s4 = Math.max(s4, s3 + prices[i]); //第二次卖出，或者不操作
            }
            return Math.max(0, s4);
        }
    }
}
