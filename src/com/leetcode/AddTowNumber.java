package com.leetcode;

import java.util.Stack;

public class AddTowNumber {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(9);

        ListNode n2 = new ListNode(1);
        n2.next = new ListNode(9);
        n2.next.next = new ListNode(9);

        solution2 solution = new solution2();
        ListNode listNode = solution.addTwoNumbers(n1, n2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 未成功答案：位数过长的话会溢出
     */
    private static class solution {

        private void reverseList(ListNode head) {
            if (head==null)return;
            reverseList(head.next);
            if (head.next!=null) head.next.next = head;
        }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            long num1 = 0, num2 = 0;
            Stack<Integer> stack1 = new Stack();
            Stack<Integer> stack2 = new Stack();
            while (l1 != null) {
                stack1.push(l1.val);
                l1 = l1.next;
            }
            while (l2 != null) {
                stack2.push(l2.val);
                l2 = l2.next;
            }
            while (!stack1.empty()) {
                num1 = num1*10+stack1.pop();
            }
            while (!stack2.empty()) {
                num2 = num2*10+stack2.pop();
            }
            long result = num1 + num2;
            ListNode resultList = null;
            ListNode head = null;
            do {
                ListNode node = new ListNode((int) (result % 10));
                result /= 10;
                if (resultList==null) {
                    resultList = node;
                    head = node;
                }
                else{
                    //node.next = resultList;
                    //resultList = node;
                    resultList.next = node;
                    resultList = resultList.next;
                }
            } while (result != 0);
            return head;
        }
    }

    /**
     * GOOD answer 75%
     */
    private static class solution2{
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode nodeA = l1;
            ListNode nodeB = l2;
            ListNode longList = l2;
            ListNode shortList = l1;
            // 看A能否追上B，A，B同时往下，若A存在，B空则用A，否则用B
            while (nodeA != null) {
                //B追不上A
                if (nodeB == null){
                    longList = l1;
                    shortList = l2;
                    break;
                }
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
            //把两个链表值加到目标链表
            nodeA = longList;
            nodeB = shortList;
            while (nodeB != null) {
                nodeA.val += nodeB.val;
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
            //处理目标链表  处理进位  参数longList
            nodeA = longList;
            while (nodeA != null) {
                if (nodeA.val >= 10) {
                    //进位
                    nodeA.val-=10;
                    if (nodeA.next!=null) nodeA.next.val++;
                    else nodeA.next = new ListNode(1);
                }
                nodeA = nodeA.next;
            }

            return longList;
        }

    }

}


