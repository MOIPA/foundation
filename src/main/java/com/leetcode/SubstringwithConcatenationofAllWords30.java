package com.leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubstringwithConcatenationofAllWords30 {

    public static void main(String[] args) {

        System.out.println(new Solution().findSubstring3("lingmindraboofooowingdingbarrwingmonkeypoundcake",
                new String[]{"fooo","barr","wing","ding","wing"}));


    }

    /**
     * 单词都是一样长的，可以将s切割成word数组，再匹配
     * "lingmindraboofooowingdingbarrwingmonkeypoundcake"
     * ["fooo","barr","wing","ding","wing"]
     * <p>
     * "wordgoodgoodgoodbestword"
     * ["word","good","best","good"]
     *
     * Runtime: 759 ms, faster than 8.38% of Java online submissions for Substring with Concatenation of All Words.
     * Memory Usage: 40.1 MB, less than 24.63% of Java online submissions for Substring with Concatenation of All Words.
     * Next challenges:
     */
    private static class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            int[] mark = new int[words.length];
//            Map<String, Integer> map = new HashMap<>();
//            for (String word : words) map.merge(word, 1, Integer::sum);
            int subStringSize = words.length * words[0].length();
            int markSize;
            boolean deleted = false;
            List<Integer> res = new LinkedList<>();
            int len = words[0].length();
            for (int i = 0; i <= s.length()-subStringSize; i += 1) {
                // 重置软删除
                Arrays.fill(mark, 1);
                markSize = mark.length;
                for (int j = i; j + len <= s.length(); j += len) {
                    String word = s.substring(j, j + len);
                    // 软删除 遍历words和mark mark不为-1的里面才能进行 为了提高效率 每次删除 将被软删除的word换到最后
                    deleted = false;
                    for (int k = 0; k < markSize; k++) {
//                        if (mark[k] != -1) {
                            if (words[k].equals(word)) {
//                                mark[k] = -1;
                                swap(words,markSize-1,k);
                                markSize--;
                                deleted = true;
                                break;
                            }
//                        }
                    }
                    if (!deleted) break;
                    if (markSize == 0) {
                        res.add(i);
                    }
                }
            }
            return res;
        }

        private static void swap(String[] s, int a, int b) {
            String tmp = s[b];
            s[b] = s[a];
            s[a] = tmp;
        }

        /**
         * 这个是解题思路 但是太慢 上面的是速度优化版
         * @param s
         * @param words
         * @return
         */
        public static List<Integer> findSubstring2(String s, String[] words) {
            List<Integer> res = new LinkedList<>();
            int len = words[0].length();
            for (int i = 0; i < s.length(); i += 1) {
                List<String> leftWords = Arrays.stream(words).collect(Collectors.toList());
                for (int j = i; j + len <= s.length(); j += len) {
                    String word = s.substring(j, j + len);
                    if (!leftWords.remove(word)) break;
                    if (leftWords.size() == 0) {
                        res.add(i);
                        break;
                    }
                }
            }
            return res;
        }

        /**
         * 定位单词 返回下标 不使用java的indexof
         *
         * @return
         */
        private List<Integer> findWord(char[] sentence, char[] word) {
            List<Integer> res = new LinkedList<>();
            boolean success = true;
            for (int i = 0; i < sentence.length; i++) {
                if (sentence[i] == word[0]) {
                    success = true;
                    for (int j = 1; j < word.length; j++)
                        if (sentence[i + j] != word[j]) success = false;
                    if (success) res.add(i + word.length);
                }
            }
            return res;
        }
        // leetcode answer
        public static List<Integer> findSubstring3(String s, String[] words) {
            // add count of each word into HashMap
            Map<String, Integer> map = new HashMap<>();
            for (String word : words) map.merge(word, 1, Integer::sum);

            List<Integer> result = new ArrayList<>();
            // substring size = length of word * total number of words
            int subStringSize = words.length * words[0].length();

            // for each substring of size subStringSize
            // check if it is valid as per the problem statement
            for (int i = 0; i <= s.length() - subStringSize; i++) {
                String input = s.substring(i, Math.min(i + subStringSize, s.length()));
                if (isSubStringWithConcatenation(input, words[0].length(), map)) {
                    result.add(i);
                }
            }
            return result;
        }

        // split characters by word size and check if it's exists in map correct number of times
        // if not return false
        private static boolean isSubStringWithConcatenation(String s, int wordSize, Map<String, Integer> map) {
            int start = 0;
            int end = s.length() - wordSize;
            Map<String, Integer> wordCount = new HashMap<>();
            while (start <= end) {
                String word = s.substring(start, start + wordSize);
                if (!map.containsKey(word)) return false;
                wordCount.merge(word, 1, Integer::sum);
                if (wordCount.get(word) > map.get(word)) return false;
                start += wordSize;
            }
            return true;
        }
    }


}


