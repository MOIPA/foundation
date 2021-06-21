package com.leetcode;

public class ConstructBinaryTreefromPostorderandInorderTraversal106 {
    public static void main(String[] args) {
        TreeNode root = new Solution().buildTree(
                new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}
        );

    }

    /**
     * 同105 用中序和后序构造树
     */
    private static class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return constructTree(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
        }

        public TreeNode constructTree(int[] posOrder, int posS, int posE, int[] inOrder, int inS, int inE) {
            // 无遍历结果
            if (posS > posE || inS > inE) return null;
            else if (posS == posE) return new TreeNode(posOrder[posE]);
            // 找到根节点
            TreeNode root = new TreeNode(posOrder[posE]);
            // 找到中序遍历的左边和右边两个集合
            int rootPosi;
            for (rootPosi = inS; rootPosi <= inE; rootPosi++) {
                if (root.val == inOrder[rootPosi]) break;
            }
            int leftTreeLen = rootPosi - inS;
            root.left = constructTree(posOrder, posS, posS + leftTreeLen - 1, inOrder, inS, rootPosi - 1);
            root.right = constructTree(posOrder, posS + leftTreeLen, posE - 1, inOrder, rootPosi + 1, inE);
            return root;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
