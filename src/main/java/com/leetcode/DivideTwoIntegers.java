package com.leetcode;

import java.util.Arrays;

public class DivideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(solution.divideTwo(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    /**
     * 问题：不使用 * / % 符号完成除法
     * <p>
     * 思路：1. 暴力算法，除法可以看作减法，每次减去被除数，当结果<0说明结束，但是这样会导致被除数为1的时候循环次数为被除数，会超时的
     * <p>
     * 思路：2. 首先位运算 除2：`>>1` 乘2：`<<1`。然后我们看整个除法表达式，比如 `13/4 = 3 ==> 13 = 4 * 3`
     * 换算成二进制：`1101 = 4 * 0010+ 4* 0001` 也就是4个 `0010`和`0001`，所以可以看出规律，那就是除法的结果必定为除数的倍数的二进制位。
     * <p>
     * 由此可以遍历二进制数（从 1000.. 遍历到 000..001），题目的最大是32位，现在假如最高8位，那么从最高位开始 `1000 0000 * 4 > 1010`
     * 循环直到发现`0010(2) * 4 < 1101`,记录下`0010`, 那么剩下是数就是 13-8=5 ,继续遍历`0001 * 4 < 5` 记录下`0001`，遍历结束，
     * 结果为 `0001 *4 + 0010 * 4` 即 `0001 + 0010 `（3） * 4 ，最后结果为 3。
     * <p>
     * 具体实现中，为了不使用乘法符号完成乘法，使用`<<`  比如 `0001`和`0010`分别乘4就是 4 和 4<<1，所以可以定义一个初始数组arr，
     * `arr[0]`放被除数，1,2,3 分别是前一位的乘2即<<1。
     */
    public static class solution {
        public static int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == 1) return dividend;
            if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
            boolean isPos = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
//            boolean isMin = dividend == Integer.MIN_VALUE;
            long array[] = new long[32];
            long result = 0;
            divisor = Math.abs(divisor);
            long dividentInput = dividend;
            dividentInput = Math.abs(dividentInput);
            array[0] = divisor;
            int truePosi = 0;
            for (int i = 1; i < array.length; i++) {
                // 当前位都是前一位的*2 但是当tmp溢出的时候就可以停止了，两个正数的相除结果肯定是正数
                array[i] = array[i - 1] << 1;
                if (array[i] < 0) {
                    break;
                }
                truePosi = i;
            }
            // 从最高位开始遍历，做减法 1. 如果两个数为正 被除数减法，结果加法 2. 被除数为负， 结果加法，被除数加法
            for (int i = truePosi; i >= 0; i--) {
                if (array[i] <= dividentInput) {
                    // 假如 27位是结果 那么 27位应该是 array[27]/divisor 也就是 2的27次方
                    result += 1 << i;
                    dividentInput -= array[i];
                }
            }
            if (result > Integer.MAX_VALUE) result = Integer.MAX_VALUE;
            if (result < Integer.MIN_VALUE) result = Integer.MIN_VALUE;
            if (isPos) return (int) result;
            else return (int) -result;
        }

        /**
         * 除数 被除数 0：同为正，1：同为负号，异号（2：被除数正，除数负 3：被除数负，除数正）
         *
         * Runtime: 1 ms, faster than 99.98% of Java online submissions for Divide Two Integers.
         * Memory Usage: 36.3 MB, less than 27.88% of Java online submissions for Divide Two Integers.
         *
         * @param dividend
         * @param divisor
         * @return
         */
        public static int divideTwo(int dividend, int divisor) {
            if (dividend == divisor) return 1;
            if (dividend == Integer.MIN_VALUE && divisor == 1) return dividend;
            if (dividend == Integer.MIN_VALUE && divisor == Integer.MAX_VALUE) return -1;
            if (divisor == Integer.MAX_VALUE && divisor == -dividend) return -1;
            if (divisor == Integer.MAX_VALUE || divisor == Integer.MIN_VALUE) return 0;
            int result = 0;
            long[] array = new long[32];
            boolean isPos = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
            // 除数确保不会溢出了
            if (divisor < 0) divisor = -divisor;
            array[0] = divisor;
            for (int i = 1; i < array.length; i++) {
                array[i] = array[i - 1] << 1;
            }
            // 被除数如果是最小值要另外判断
            if (dividend == Integer.MIN_VALUE) {
                for (int i = array.length - 1; i >= 0; i--) {
                    if (dividend <= -array[i]) {
                        dividend += array[i];
                        result += 1 << i;
                        if (result<0) return Integer.MAX_VALUE;
                    }
                }
            } else {
                if (dividend < 0) dividend = -dividend;
                for (int i = array.length - 1; i >= 0; i--) {
                    if (dividend >= array[i]) {
                        dividend -= array[i];
                        result += 1 << i;
                    }
                }
            }
            if (isPos) return result;
            else return -result;
        }
    }
}
