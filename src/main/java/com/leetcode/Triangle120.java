package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Triangle120 {
    public static void main(String[] args) {
        System.out.println(new Solution().minimumTotal(Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        )));
    }

    /**
     * 计算三角形中每行最小值组成的路径 实际也是个经典的dp问题，从低到上
     *    2
     *   3 4
     *  6 5 7
     * 4 1 8 3
     *
     * 从最底层开始：4，1，8，3 往上分别为 （10，7），（6，13），（15，10） 选出最小的7，6，10
     * 继续：9，10  最后：11
     */
    private static class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
//            return triangle.stream().map(x -> x.stream().min(Comparator.comparingInt(n -> n))).mapToInt(Optional::get).sum();
            int[] costs = new int[triangle.get(triangle.size() - 1).size()];
            for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++)
                costs[i] = triangle.get(triangle.size() - 1).get(i);
            for (int i = triangle.size() - 2; i >= 0; i--)
                for (int j = 0; j < triangle.get(i).size(); j++)
                    costs[j] = Math.min(costs[j] + triangle.get(i).get(j), costs[j + 1] + triangle.get(i).get(j));
            return costs[0];
        }
    }
}
