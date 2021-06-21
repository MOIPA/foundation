package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PascalTriangle118 {
    public static void main(String[] args){
        System.out.println(new Solution().generate(5));
    }

    /**
     * 帕斯卡三角形
     *      1
     *    1   1
     *   1  2  1
     *  1 3   3  1
     * 1 4  6   4  1
     */
    private static class Solution{
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new LinkedList<>();
            List<Integer> tmp = Arrays.asList(1);
            res.add(tmp);
            for (int i = 1; i < numRows; i++) {
                List<Integer> curr = new LinkedList();
                curr.add(1);
                for (int j = 0; j < tmp.size() - 1; j++) {
                    curr.add(tmp.get(j)+tmp.get(j+1));
                }
                curr.add(1);
                tmp = curr;
                res.add(tmp);
            }
            return res;
        }
    }
}
