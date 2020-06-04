package com.leetcode;

/**
 * @author tr
 * @date 2020/6/3 17:15
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Nth Node From End of List.
 * Memory Usage: 37.4 MB, less than 6.37% of Java online submissions for Remove Nth Node From End of List.
 *
 */

public class RemoveNthNodeFromEndofList {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        ListNode node = new ListNode(2);
        head.next = node;
        for (int i = 3; i < 6; i++) {
            ListNode listNode = new ListNode(i);
            node.next = listNode;
            node = listNode;
        }

        head = solution.removeNthFromEnd(head, 5);
        node = head;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
//        solution.doDelete();
    }

    private static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // 倒数第n个  递归返回时候增加计数，为n的时候删除
            return doDelete(head, head, new int[]{0}, n);
        }

        // 有个问题，n+1层能删除下一层的，但是n层里面删除倒数第n个就会出问题，因此，设定一个head，对比head看自己是否到第一层，如果和n匹配删除
        private ListNode doDelete(ListNode head, ListNode node, int[] count, int n) {
            if (node.next == null) {
                count[0]++;
            } else {
                doDelete(head, node.next, count, n);
                count[0]++;
                // 只有n+1层能够执行删除操作
                if (count[0] == n + 1) {
                    if (n == 1) node.next = null;
                    else node.next = node.next.next;
                }
            }
            if (head == node && count[0] == n) head = head.next;
            return head;
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
