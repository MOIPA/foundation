package com.leetcode;

public class PalindromeNumber {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(1221));
    }

    /**
     * Runtime: 7 ms, faster than 82.44% of Java online submissions for Palindrome Number.
     * Memory Usage: 41 MB, less than 5.02% of Java online submissions for Palindrome Number.
     */
    private static class Solution {
        public boolean isPalindrome(int x) {
            String num = String.valueOf(x);
            for (int i = 0; i < num.length() / 2 ; i++) {
                if (num.charAt(i) != num.charAt(num.length() - 1 - i)) return false;

            }
            return true;
        }
    }
}
