package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PascalTriangleII119 {
    public static void main(String[] args){
        System.out.println(new Solution().generate(0));
    }

    /**
     * 帕斯卡三角形 只显示最上层
     *      1
     *    1   1
     *   1  2  1
     *  1 3   3  1
     * 1 4  6   4  1
     */
    private static class Solution{
        public List<Integer> generate(int rowIndex) {
            List<Integer> tmp = Arrays.asList(1);
            for (int i = 1; i <= rowIndex; i++) {
                List<Integer> curr = new LinkedList();
                curr.add(1);
                for (int j = 0; j < tmp.size() - 1; j++) {
                    curr.add(tmp.get(j)+tmp.get(j+1));
                }
                curr.add(1);
                tmp = curr;
            }
            return tmp;
        }
    }
}
