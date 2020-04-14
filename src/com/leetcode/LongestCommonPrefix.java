package com.leetcode;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        String[] test = {"flower", "flow", "flight"};
        String[] test = {"aa"};
        System.out.println(solution.longestCommonPrefix(test));
    }

    /**
     * Runtime: 9 ms, faster than 8.53% of Java online submissions for Longest Common Prefix.
     * Memory Usage: 38.2 MB, less than 80.36% of Java online submissions for Longest Common Prefix.
     */
    private static class Solution {
        public String longestCommonPrefix(String[] strs) {
//            if (strs.length == 1) return strs[0];
            String result = "";
            try {
                for (int i = 0; i < strs[0].length(); i++) {
                    ;
                    for (int j = 1; j < strs.length; j++) {
                        if (strs[0].charAt(i) != strs[j].charAt(i)) return result;
                    }
                    result += strs[0].charAt(i);
                }
            } catch (Exception e) {
            } finally {
                return result;
            }
        }
    }
}
