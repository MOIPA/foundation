package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author tr
 * @date 2020/6/4 14:32
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 组合问题：进进进出出出，进进出进出出，进进出出进出，进出进进出出，进出进出进出
 * 可以将这个看为一棵树，左分支：(  右分支:)
 * 最后不就是树的遍历问题么
 *
 *
 * Runtime: 1 ms, faster than 84.10% of Java online submissions for Generate Parentheses.
 * Memory Usage: 39.5 MB, less than 20.00% of Java online submissions for Generate Parentheses.
 *
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = solution.generateParenthesis(9);
        list.stream().forEach(System.out::println);
    }

    private static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> result = new LinkedList<>();
            doGenerate(0, result, "", n, true);
            return result;
        }

        /**
         * @param result
         * @param temp   正在拼接的字符串
         * @param n      树的层数限制
         * @param flag   出栈还是入栈 1为入栈
         */
        private void doGenerate(int elementRecord, List<String> result, String temp, int n, boolean flag) {
            if (flag) {
                n--;
                if (n >= 0) {
                    elementRecord++;
                    temp += "(";
                }
                else return;
            } else {
                if (elementRecord == 0 && n <= 0) {
                    result.add(temp);
                    return;
                } else if (elementRecord == 0 && n > 0) return;
                else if (elementRecord != 0) {
                    elementRecord--;
                    temp += ")";
                }
            }
            doGenerate(elementRecord, result, temp, n, true);
            doGenerate(elementRecord, result, temp, n, false);
        }
    }
}
