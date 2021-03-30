package com.leetcode;

import java.util.Stack;

/**
 * @author tr
 * @Date 6/12/20
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapPairs {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list1 = new ListNode(5);
        list1 = new ListNode(4, list1);
        list1 = new ListNode(3, list1);
        list1 = new ListNode(2, list1);
        list1 = new ListNode(1, list1);
//        solution.reverseList(list1, end);
//        list1.travelList(end);
        ListNode listNode = solution.swapPairs(list1);
        listNode.travelList(listNode);

    }

    static class Solution {
        public ListNode swapPairs(ListNode head) {
            //head 第一次替换后变为第一次替换的end
            boolean isFirst = true;
            int swapNum = 2-1;
            int swapCount = 0;
            ListNode temp = head;
            ListNode startNode = null;
            ListNode endNode = null;
            // 链接器 保存的是上次的尾节点
            ListNode Linker = null;
            while (temp != null) {
                //为0 当前开始
                if (swapCount == 0) {
                    startNode = temp;
                }
                else if (swapCount == swapNum) {
                    //开始替换 如果第一次替换需要换掉head
                    endNode = temp;
                    //保存互换的下一个 防止丢失指针
                    temp = temp.next;
                    reverseList(startNode, endNode);
                    //第一次需要替换
                    if (isFirst) {
                        isFirst = false;
                        head = endNode;
                    }else{
                        //不是第一次需要将上次的尾巴节点指向为本次头结点
                        Linker.next = endNode;
                    }
                    //保存这次的尾节点
                    Linker = startNode;
                    //交换后start和end相反，交换后的end即交换前的start指向下一个
                    startNode.next = temp;
                    swapCount = 0;
                    continue;
                }
                temp = temp.next;
                swapCount++;
            }
            return head;
        }

        void reverseList(ListNode start, ListNode end) {
            // 盏实现逆转
            if (start == null || end == null) return;
            ListNode recordStar = start;
            ListNode recordEnd = end;
            Stack<ListNode> stack = new Stack<>();
            while (start != end) {
                stack.push(start);
                start = start.next;
            }
            stack.push(start);
            // 出盏逆转
            if (stack.empty()) return;
            ListNode temp = stack.pop();
            while (!stack.empty()) {
                temp.next = stack.pop();
                temp = temp.next;
            }
            temp.next = null;
        }
    }
}
