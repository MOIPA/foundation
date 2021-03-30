package com.leetcode;

public class ZigZagConversion {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.convert("PAYPALISHIRING", 4));
    }

    /**
     * Runtime: 56 ms, faster than 7.15% of Java online submissions for ZigZag Conversion.
     * Memory Usage: 45.5 MB, less than 7.45% of Java online submissions for ZigZag Conversion.
     */
    private static class Solution {
        public String convert(String s, int numRows) {
            if (numRows==1)return s;

            char[][] result = new char[numRows][s.length()];
            String resultS = "";
            // 0:down  1:right up
            byte direction = 0;
            int[] position = {-1, 0};
            for (int i = 0; i < s.length(); i++) {
                //向下走
                if (0 == direction) {
                    position[0]++;
                    result[position[0]][position[1]] = s.charAt(i);
                    //变向判定
                    if (position[0] + 1 >= numRows) direction = 1;
                } else {
                    //向右上角走
                    position[0]--;
                    position[1]++;
                    result[position[0]][position[1]] = s.charAt(i);
                    //变向判定
                    if (position[0] - 1 < 0) direction = 0;
                }
            }
            for (int i = 0; i < numRows; i++)
                for (int j = 0; j < result[0].length; j++) {
                   if (result[i][j]!='\u0000')
                       resultS += result[i][j];
                }
            return resultS;
        }
    }

}
