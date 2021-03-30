package com.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author tr
 * @date 2020/6/4 10:14
 * <p>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * Runtime: 2 ms, faster than 30.56% of Java online submissions for Valid Parentheses.
 * Memory Usage: 37.5 MB, less than 5.06% of Java online submissions for Valid Parentheses.
 *
 */
public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("()[{]}"));
    }

    private static class Solution {
        public boolean isValid(String s) {
            List<Character> leftParentheses = Arrays.asList('(', '{', '[');
            List<Character> rightParentheses = Arrays.asList(')', '}', ']');
            char c;
            Stack<Integer> stack = new Stack<>();
            try {
                for (int i = 0; i < s.length(); i++) {
                    c = s.charAt(i);
                    // 是否包含 包含就把下标存入
                    if (leftParentheses.contains(c)) stack.push(leftParentheses.indexOf(c));
                        // 不是左括号说明是右括号要匹配操作，判断下标是否一致，一致则是正反括号，匹配成功，不成功就返回false
                    else if (!(rightParentheses.indexOf(c) == stack.peek())) return false;
                    else stack.pop();
                }
            } catch (Exception e) {
                return false;
            }
            if (stack.empty()) return true;
            return false;
        }
    }
}
