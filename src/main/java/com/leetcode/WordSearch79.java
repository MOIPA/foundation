package com.leetcode;

import java.util.Arrays;

public class WordSearch79 {
    public static void main(String[] args) {
        System.out.println(new Solution().exist(new char[][]{
                {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}
        }, "aaaaaaaaaaaaa"));
    }

    /**
     * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
     * <p>
     * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
     */
    private static class Solution {
        public boolean exist(char[][] board, String word) {
            // 1. 定位到字母开头
            int colLen = board[0].length;
            char character = word.charAt(0);
            boolean[][] usage = new boolean[board.length][colLen];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < colLen; j++) {
                    if (board[i][j] == character) {
                        // 2. 判断是否能找完
                        usage[i][j] = true;
                        if (doFind(board, i, j, -1, 1, word, usage)) return true;
                        else usage[i][j] = false;
                    }
                }
            }
            return false;
        }

        /**
         * @param board          矩阵
         * @param i              当前位置
         * @param j
         * @param previousDirect 从哪来
         * @param chPosi         要查找的字符位置
         * @return
         */
        private boolean doFind(char[][] board, int i, int j, int previousDirect, int chPosi, String word, boolean[][] usage) {
            if (chPosi == word.length()) return true;
            // 0 1 2 3  上 下 左 右
            if (previousDirect != 0 && i > 0 && !usage[i - 1][j] && board[i - 1][j] == word.charAt(chPosi)) {
                usage[i - 1][j] = true;
                if (doFind(board, i - 1, j, 1, chPosi + 1, word, usage)) return true;
                else usage[i - 1][j] = false;
            }
            if (previousDirect != 1 && i < board.length - 1 && !usage[i + 1][j] && board[i + 1][j] == word.charAt(chPosi)) {
                usage[i + 1][j] = true;
                if (doFind(board, i + 1, j, 0, chPosi + 1, word, usage)) return true;
                else usage[i + 1][j] = false;
            }
            if (previousDirect != 2 && j > 0 && !usage[i][j - 1] && board[i][j - 1] == word.charAt(chPosi)) {
                usage[i][j - 1] = true;
                if (doFind(board, i, j - 1, 3, chPosi + 1, word, usage)) return true;
                else usage[i][j - 1] = false;
            }
            if (previousDirect != 3 && j < board[0].length - 1 && !usage[i][j + 1] && board[i][j + 1] == word.charAt(chPosi)) {
                usage[i][j + 1] = true;
                if (doFind(board, i, j + 1, 2, chPosi + 1, word, usage)) return true;
                else usage[i][j + 1] = false;
            }
            return false;
        }
    }
}
