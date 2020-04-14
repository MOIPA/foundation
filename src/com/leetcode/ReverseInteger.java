package com.leetcode;

public class ReverseInteger {
    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.reverse(1534236469));

    }

    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Reverse Integer.
     * Memory Usage: 36.9 MB, less than 5.55% of Java online submissions for Reverse Integer.
     */
    private static class Solution{
        public int reverse(int x) {
            int num = Math.abs(x);
            long result=0;
            while (num > 0) {
                result = result * 10 + num % 10;
                num /= 10;
            }
            if (result!=(int)result)return 0;
            if (x<0)return -(int)result;
            return (int)result;
        }
    }
}
