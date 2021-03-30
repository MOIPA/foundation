package com.leetcode;

/**
 * @author tr
 * @date 2020/8/25 14:45
 */
public class StrStr {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.strStr("mississippi", "sipp"));
    }

    private static class Solution {
        public int strStr(String haystack, String needle) {
            if (needle.equals("")) return 0;
            int count = 0;
            for (; count < haystack.length(); count++) {
                if (needle.charAt(0) == haystack.charAt(count)) {
                    //开始匹配
                    for (int i = 0, j = count; i < needle.length() && j < haystack.length(); i++, j++) {
                        if (needle.charAt(i) == haystack.charAt(j)) {
                            if (i == needle.length() - 1) return count;
                        } else break;
                    }
                }
            }
            return -1;
        }
    }
}
