package com.leetcode;

public class StringtoInteger {
    public static void main(String[] args) {

        Solution solution = new Solution();
        System.out.println(solution.myAtoi("18446744073709551617"));
    }

    /**
     * shitty problem 64% time
     */
    private static class Solution{
        public int myAtoi(String str) {
            str = str.trim();
//            if (str.equals("")||str.equals("-")||str.equals("+")) return 0;
            long num = 0;
            int result = 0;
            boolean minus = false;
            try {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(0) == '-') {
                        if (str.charAt(1) == '+'||str.charAt(1)=='-') return 0;
                        minus = true;
                        str = str.substring(1, str.length());
                    } else if (str.charAt(0) == '+') {
                        if (str.charAt(1) == '+'||str.charAt(1)=='-') return 0;
                        str = str.substring(1, str.length());
                    } else break;
                }
                if (!Character.isDigit(str.charAt(0)))return 0;
            } catch (Exception e) {
                return 0;
            }
            //是否是非数字开头，是则退0
            //数字则提取后面数字
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i)))break;
                num = num * 10 + Integer.parseInt(String.valueOf(str.charAt(i)));
                if (num!=(int)num){
                    if (minus) return Integer.MIN_VALUE;
                    return Integer.MAX_VALUE;
                }
            }
            result = (int) num;
            if (num!=result){
                if (minus) return Integer.MIN_VALUE;
                return Integer.MAX_VALUE;
            }
            if (minus) return -result;
            return result;
        }
    }
}
