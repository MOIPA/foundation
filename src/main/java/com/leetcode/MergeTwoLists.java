package com.leetcode;

import com.ds.list.linked.LinkedList;

/**
 * @author tr
 * @date 2020/6/4 14:11
 * <p>
 * Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoLists {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode headOne = new ListNode(4);
//        headOne = new ListNode(2, headOne);
//        headOne = new ListNode(1, headOne);
//        ListNode headTwo = new ListNode(4);
//        headTwo = new ListNode(3, headTwo);
//        headTwo = new ListNode(1, headTwo);
        ListNode node = solution.mergeTwoLists(headOne,null);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
    private static class Solution{
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(0);
            ListNode node = result;
            // 两个指针指向两个list，当遍历结束，将多的放入结束的指针后
            if (l1==null) return l2;
            if (l2==null) return l1;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    result.next = l1;
                    l1 = l1.next;
                } else {
                    result.next = l2;
                    l2 = l2.next;
                }
                result = result.next;
                // 遍历结束
                if (l1 == null) {
                    result.next = l2;
                    return node.next;
                } else if (l2 == null) {
                    result.next=l1;
                    return node.next;
                }
            }
            return node.next;
        }
    }
      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
}
