package com.ds.stack_queue.algorithm;

import com.ds.stack_queue.linked.LinkedStack;

/**
 * @author TR
 * @content 1.infix expression to postfix expression  中缀转后缀可以使用栈和二叉树（后续遍历）
 * 2. infix ex
 */
public class Infix2Postfix {

    public static void main(String[] args) {
        doInfix2postfix infix2postfix = new doInfix2postfix();
        String infixString = "1 + ((2 + 3) * 4) -5";
        System.out.println(infix2postfix.conversion(infixString));

    }
}

/**
 * 使用栈的实现方法
 */
class doInfix2postfix {

    // 获得每个符号的优先级别 数字越大越高
    private int getPriority(char symbol) {
        switch (symbol) {
            case '+':
                ;
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '(':
            case ')':
                return 3;
            default:
                return -1;
        }
    }

    // 判断符号优先级 + - ,* / ,()
    public boolean isPrior(char symbol1, char symbol2) {
        //相等视为大于
        if (symbol1==symbol2)return true;
        if (getPriority(symbol1) >= getPriority(symbol2)) return true;
        return false;
    }

    public String conversion(String infixExpression) {
        LinkedStack stack = new LinkedStack();
        String postExpression = "";
        // init
        for (int i = 0; i < infixExpression.length(); i++) {
            if (infixExpression.charAt(i) == ' ') continue;
            char currentChar = infixExpression.charAt(i);

            //1. number judge
            if (currentChar <= 57 && currentChar >= 48) {
                postExpression += currentChar;
                continue;
            }

            //2. push pop operation
            //2.1 入栈 空栈和优先级高可入  优先级： 同级优先级如-,+,最后进入的最低
            if (stack.getTop() == null) {
                stack.push(currentChar);
                continue;
            }
            if (isPrior(currentChar, (char) stack.getTop())&&currentChar!=')') {
                stack.push(currentChar);
                continue;
            }
            //2.2 出栈
            // ')'
            if (currentChar == ')') {
                // 一直弹栈 弹到 为'('结束
                do {
                    char pop = (char) stack.pop();
                    if (pop == '(') break;
                    postExpression += pop;
                } while (true);
                continue;
            }
            //优先级
            else {
                //当前操作符小于栈顶操作符，不停退栈直到大于后再把自己入栈
                //退栈时不退括号
                do {
                    postExpression += (char) stack.pop();
                    if (stack.getTop()==null){
                        stack.push(currentChar);
                        break;
                    }
                    if (isPrior(currentChar, (Character) stack.getTop())) {
                        stack.push(currentChar);
                        break;
                    }
                } while (true);
            }
        }

        // 3. 清栈
        while (!stack.isEmpty()) postExpression += stack.pop();

        return postExpression;
    }
}
