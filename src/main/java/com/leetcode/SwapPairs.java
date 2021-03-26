package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tr
 * @date 2020/8/24 14:30
 *
 * Runtime: 3 ms, faster than 6.43% of Java online submissions for Reverse Nodes in k-Group.
 * Memory Usage: 39.6 MB, less than 75.28% of Java online submissions for Reverse Nodes in k-Group.
 * Next challenges:
 *
 */
public class SwapPairs {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode root = new ListNode(5);
        root = new ListNode(4, root);
        root = new ListNode(3, root);
        root = new ListNode(2, root);
        root = new ListNode(1, root);
        root = solution.swapPairs(root, 1);
        ListNode temp = root;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    private static class Solution {
        public ListNode swapPairs(ListNode head, int k) {
            if (k==1)return head;
            if (head == null) return null;
            // 切割每k个节点，重写整合替换后的节点
            List<ListNode> splitList = new LinkedList<>();
            ListNode tmp = head;
            int count = 0;
            while (tmp != null) {
                if (++count == 1) splitList.add(tmp);
                if (count == k) count = 0;
                tmp = tmp.next;
            }
            // 交换切割后的
            splitList = splitList.stream().map(x -> {
                return swapKNode(x, k);
            }).collect(Collectors.toList());
            // 链接所有切割后的
            for (int i = 0; i < splitList.size(); i++) {
                ListNode node = splitList.get(i);
                try {
                    if (node.next == null) break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (i + 2 <= splitList.size()) {
                    // node last = next level's first
                    while (node.next != null) node = node.next;
                    node.next = splitList.get(i + 1);
                }
            }
            // 返回
            return splitList.get(0);
        }

        private ListNode swapKNode(ListNode start, int k) {
            if (start == null) return null;
            // 交换k个节点，返回头节点
            // 1. 计算总实际节点数，如果大于k则从k个开始，小于k则从最大数开始
            ListNode tmp = start;
            int count = 1;
            while (tmp.next != null) {
                count++;
                tmp = tmp.next;
            }
            if (count >= k) count = k;
            else return start;
            // 2. 从count数开始内部进行倒换,tmp这时处于最后一个
            return doSwap(null, start, count);
        }

        /**
         * @param upnode 上级node
         * @param node   下级node
         * @param k      个数
         * @return
         */
        private ListNode doSwap(ListNode upnode, ListNode node, int k) {
            if (k <= 1) {
                node.next = upnode;
                return node;
            }
            ListNode listNode = doSwap(node, node.next, k - 1);
            if (upnode != null) node.next = upnode;
            else node.next = null;
            return listNode;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
