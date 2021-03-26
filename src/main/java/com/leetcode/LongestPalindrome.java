package com.leetcode;

public class LongestPalindrome {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("cbbd"));
    }

    //Runtime: 1012 ms, faster than 5.02% of Java online submissions for Longest Palindromic Substring.
    //Memory Usage: 38.1 MB, less than 43.15% of Java online submissions for Longest Palindromic Substring.
    private static class Solution {
        public String longestPalindrome(String s) {

            if (s.equals("")) return "";
            int len=1;
            int start=0,end=0;
            for (int i = 0; i < s.length(); i++) {
                if (len == s.length()) break;
                for (int j = i + 1; j < s.length(); j++) {
                    if (isPalindrome(s, i, j))
                        if (j - i + 1 > len) {
                            len = j - i + 1;
                            start = i;
                            end = j;
                        }
                }
            }
            return s.substring(start, end + 1);
        }

        private boolean isPalindrome(String s,int start,int end) {
//            for (int i = start; i < (end-start+1)/2; i++)
//                if (s.charAt(i) != s.charAt(end - i )) return false;
            for (int i = 0; i < (end - start + 1) / 2; i++) {
                if (s.charAt(start+i)!=s.charAt(end-i))return false;
            }
            return true;
        }
    }
}
