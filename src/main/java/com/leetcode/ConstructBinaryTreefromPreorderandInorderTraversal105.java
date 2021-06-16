package com.leetcode;

import java.util.Arrays;

public class ConstructBinaryTreefromPreorderandInorderTraversal105 {
    public static void main(String[] args) {
        TreeNode root = new Solution2().buildTree(
                new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}
        );
//        System.out.println(Arrays.toString());

    }

    private static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return constructTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        }

        /**
         * @param preOrder 前序遍历结果
         * @param preS     结果开始下标
         * @param preE     结果结束下标
         * @param inOrder
         * @param inS
         * @param inE
         * @return
         */
        public TreeNode constructTree(int[] preOrder, int preS, int preE, int[] inOrder, int inS, int inE) {
            // 无遍历结果
            if (preS > preE || inS > inE) return null;
            else if (preS == preE) return new TreeNode(preOrder[preS]);
            // 找到根节点
            TreeNode root = new TreeNode(preOrder[preS]);
            // 找到中序遍历的左边和右边两个集合
            int rootPosi;
            for (rootPosi = inS; rootPosi <= inE; rootPosi++) {
                if (root.val == inOrder[rootPosi]) break;
            }
            // 找到前序遍历的左右两个集合
//            int preEnd;
//            for (preEnd = preS + 1; preEnd <= preE; preEnd++) {
//                // 判断是否在左集合内
//                boolean ifContains = false;
//                for (int i = inS; i <= rootPosi - 1; i++) {
//                    if (inOrder[i] == preOrder[preEnd]) ifContains = true;
//                }
//                if (!ifContains) {
//                    break;
//                }
//            }
//            preEnd--;
            int leftTreeLen = rootPosi - inS;
            root.left = constructTree(preOrder, preS + 1, preS + leftTreeLen, inOrder, inS, rootPosi - 1);
            root.right = constructTree(preOrder, preS + leftTreeLen + 1, preE, inOrder, rootPosi + 1, inE);
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


    private static class Solution2 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            return buildTreeHelper(preorder, inorder, (long) Integer.MAX_VALUE + 1);
        }

        int pre = 0;
        int in = 0;

        private TreeNode buildTreeHelper(int[] preorder, int[] inorder, long stop) {
            //到达末尾返回 null
            if (pre == preorder.length) {
                return null;
            }
            //到达停止点返回 null
            //当前停止点已经用了，in 后移
            if (inorder[in] == stop) {
                in++;
                return null;
            }
            int root_val = preorder[pre++];
            TreeNode root = new TreeNode(root_val);
            //左子树的停止点是当前的根节点
            root.left = buildTreeHelper(preorder, inorder, root_val);
            //右子树的停止点是当前树的停止点
            root.right = buildTreeHelper(preorder, inorder, stop);
            return root;
        }
    }

}
