package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;

/**
 * @author tr
 * @date 2020/6/9 16:42
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * Runtime: 427 ms, faster than 5.06% of Java online submissions for Merge k Sorted Lists.
 * Memory Usage: 45.3 MB, less than 5.04% of Java online submissions for Merge k Sorted Lists.
 *
 */
public class MergeKLists {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list1 = new ListNode(5);
        list1 = new ListNode(4, list1);
        list1 = new ListNode(1, list1);
        ListNode list2 = new ListNode(4);
        list2 = new ListNode(3, list2);
        list2 = new ListNode(1, list2);
        ListNode list3 = new ListNode(6);
        list3 = new ListNode(2, list3);
        ListNode[] listNodes = {list1,list2,list3};
        ListNode listNode = solution.mergeKLists(listNodes);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
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

    private static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length==0)return null;
            ListNode result = new ListNode(0);
            ListNode temp = result;
            Integer min = null;
            int min_index = 0; //最小数所在链表
            int nullCount;
            // 遍历链表，找出最小的，放入集合中
            while (true) {
                min=null;
                nullCount = 0;
                for (int i = 0; i < lists.length; i++) {
                    if (lists[i] == null) {
                        if (++nullCount == lists.length) return temp.next;
                        continue;
                    }
                    if (min==null || lists[i].val <= min) {
                        min = lists[i].val;
                        min_index = i;
                    }
                }
                result.next = new ListNode(min);
                result = result.next;
                lists[min_index] = lists[min_index].next;
            }
        }

    }
}
