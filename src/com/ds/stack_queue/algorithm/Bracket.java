package com.ds.stack_queue.algorithm;

import com.ds.stack_queue.linked.LinkedStack;

/**
 * @author TR
 * @content bracket match
 */
public class Bracket {

    public static void main(String[] args) {
        String testString = "((a+b)-c)*d";
        solution solution = new solution();
        boolean b = solution.match2(testString);
        System.out.println(b);
    }
}

class solution {

    // linked stack
    public boolean match1(String exp) {
        LinkedStack linkedStack = new LinkedStack();
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') linkedStack.push('(');
            if (exp.charAt(i) == ')') {
                if (linkedStack.isEmpty()) return false;
                else linkedStack.pop();
            }
        }
        if (linkedStack.isEmpty()) return true;
        return false;
    }

    public boolean match2(String exp) {
        char[] stack = new char[exp.length()];
        int top = -1;
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') stack[++top] = '(';
            if (exp.charAt(i) == ')') {
                if(top==-1)return false;
                top--;
            }
        }
        if (top == -1) return true;
        return false;
    }

}
