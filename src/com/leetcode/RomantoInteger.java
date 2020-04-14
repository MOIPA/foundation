package com.leetcode;

public class RomantoInteger {
    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.romanToInt("I"));

    }

    /**
     * Runtime: 3 ms, faster than 99.99% of Java online submissions for Roman to Integer.
     * Memory Usage: 41.5 MB, less than 5.48% of Java online submissions for Roman to Integer.
     */
    private static class Solution {
        public int romanToInt(String s) {
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case 'I':
                        if (i < s.length() - 1 && s.charAt(i + 1) == 'V') {
                            result += 4;
                            i++;
                        } else if (i < s.length() - 1 && s.charAt(i + 1) == 'X') {
                            result += 9;
                            i++;
                        } else result += 1;
                        break;
                    case 'V':
                        result += 5;
                        break;
                    case 'X':
                        if (i < s.length() - 1 && s.charAt(i + 1) == 'L') {
                            result += 40;
                            i++;
                        }else if (i < s.length() - 1 && s.charAt(i + 1) == 'C') {
                           result += 90;
                            i++;
                        }else result += 10;
                        break;
                    case 'L':
                        result += 50;
                        break;
                    case 'C':
                        if (i < s.length() - 1 && s.charAt(i + 1) == 'D') {
                            result += 400;
                            i++;
                        } else if (i < s.length() - 1 && s.charAt(i + 1) == 'M') {
                            result += 900;
                            i++;
                        } else result += 100;
                        break;
                    case 'D':
                        result += 500;
                        break;
                    case 'M':
                        result += 1000;
                        break;
                }

            }
            return result;
        }
    }

}
