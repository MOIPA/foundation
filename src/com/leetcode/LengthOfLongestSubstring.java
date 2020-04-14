package com.leetcode;

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String testString = "pwwkew";
        solution solution = new solution();
        System.out.println(solution.lengthOfLongestSubstring(testString));
    }

    private static class solution {
        //遍历
        public int lengthOfLongestSubstring(String s) {
            if (s.equals(""))return 0;
            int count = 1;
            int record = 1;
            boolean continueFlag = true;
            for (int i = 0; i < s.length(); i++) {
                continueFlag = true;
                count = 1;
                for (int j = i + 1; j < s.length(); j++) {
                    // i~j 为集合 j指向的为未添加字符，将未添加字符同集合遍历
                    for (int k = i; k < j; k++) {
                        if (s.charAt(k) == s.charAt(j)) {
                            continueFlag = false;
                            break;
                        }
                    }
                    if (!continueFlag) break;
                    count++;
                    if (count > record) record = count;
                }
            }
            return record;
        }
    }
}
