package com.leetcode;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        List<String> strings = solution.letterCombinations("23");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }


    /**
     * Runtime: 5 ms, faster than 26.23% of Java online submissions for Letter Combinations of a Phone Number.
     * Memory Usage: 38.8 MB, less than 6.16% of Java online submissions for Letter Combinations of a Phone Number.
     */
    private static class Solution3{
        public List<String> letterCombinations(String digits) {
            List<String> result = new LinkedList<>();
            if (digits.length()==0) return result;
            char[][] map = new char[8][];
            map[0] = "abc".toCharArray();
            map[1] = "def".toCharArray();
            map[2] = "ghi".toCharArray();
            map[3] = "jkl".toCharArray();
            map[4] = "mno".toCharArray();
            map[5] = "pqrs".toCharArray();
            map[6] = "tuv".toCharArray();
            map[7] = "wxyz".toCharArray();
            result.add("");
            for (char c :
                    digits.toCharArray()) {
                result = expand(result, map[c-'2']);
            }
            return result;
        }

        private List<String> expand(List<String> result, char[] posi) {
            List<String> newResult = new LinkedList<>();
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < posi.length; j++) {
                    newResult.add(result.get(i) + posi[j]);
                }
            }
            return newResult;
        }
    }
    /**
     * Runtime: 6 ms, faster than 11.75% of Java online submissions for Letter Combinations of a Phone Number.
     * Memory Usage: 38.9 MB, less than 6.16% of Java online submissions for Letter Combinations of a Phone Number.
     */
    private static class Solution2{
        public List<String> letterCombinations(String digits) {
            if (digits=="") return new LinkedList<String>();
            char[][] group = {
                    {'a', 'b', 'c'},
                    {'d', 'e', 'f'},
                    {'g', 'h', 'i'},
                    {'j', 'k', 'l'},
                    {'m', 'n', 'o'},
                    {'p', 'q', 'r', 's'},
                    {'t', 'u', 'v'},
                    {'w', 'x', 'y', 'z'}
            };
            List<String> result = new LinkedList<>();
            doMixUp(result, group, digits.toCharArray(), 0, "");

            return result;
        }

        private void doMixUp(List<String> result, char[][] group, char[] digits, int posi,String currentString) {
            // 没有集合了，装载当前串
            if (posi == digits.length) {
                result.add(currentString);
                return;
            }
            // 装载集合
            char[] currentSet = group[Integer.parseInt(digits[posi]+"")-2];
            for (int i = 0; i < currentSet.length; i++) {
                doMixUp(result, group, digits, posi + 1, currentString + currentSet[i]);
            }
        }
    }

    /**
     * list方法 迭代器有问题
     */
    private static class Solution{
        public List<String> letterCombinations(String digits) {
            char[][] group = {
                    {'a', 'b', 'c'},
                    {'d', 'e', 'f'},
                    {'g', 'h', 'i'},
                    {'j', 'k', 'l'},
                    {'m', 'n', 'o'},
                    {'p', 'q', 'r', 's'},
                    {'t', 'u', 'v'},
                    {'w', 'x', 'y', 'z'}
            };
            List<char[]> set = new ArrayList<>();
            char[] chars = digits.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                set.add(group[Integer.parseInt(chars[i]+"")-2]);
            }
            List<String> result = new LinkedList<>();
            doMixUp(result, set.iterator(), "");
            //mix up set
            return result;
        }

    /**
     *  此算法错误：问题在于迭代器分发后非拷贝，递归子方法都使用同一迭代器
     * @param result 结果集合
     * @param set 要混合的集合的迭代器
     * @param currentString 当前拼接的串
     */
    private void doMixUp(List<String> result, Iterator set, String currentString) {
        // 没有集合了，装载当前串
        if (!set.hasNext() || set == null) {
            result.add(currentString);
            return;
        }
        // 装载集合
//        doMixUp(result,set.next(),currentString);
        char[] nextSets = (char[]) set.next();
        for (int i = 0; i < nextSets.length; i++) {
            doMixUp(result, set, currentString + nextSets[i]);
        }
    }
    }
}

