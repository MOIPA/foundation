package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class IntegertoRoman {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.intToRoman(1994));

    }

    /**
     * description:
     * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
     * <p>
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     * <p>
     * I can be placed before V (5) and X (10) to make 4 and 9.
     * X can be placed before L (50) and C (100) to make 40 and 90.
     * C can be placed before D (500) and M (1000) to make 400 and 900.
     * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
     * <p>
     * performance:
     * Runtime: 6 ms, faster than 40.03% of Java online submissions for Integer to Roman.
     * Memory Usage: 41.4 MB, less than 10.00% of Java online submissions for Integer to Roman.
     * <p>
     * suck!
     */
    private static class Solution {
        public String intToRoman(int num) {
            String result = "";
            int h = 0;
//            char[] store = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
            while (num > 0) {
                // 最高位千位，由多个M组成
                h = num / 1000;
                if (h < 10 && h > 0) {
                    for (int i = 0; i < h; i++) {
                        result += 'M';
                    }
                    num %= 1000;
                    continue;
                }
                // 最高位百位，多个C或者D组成，注意400(CD)，900(CM)
                h = num / 100;
                if (h < 10 && h > 0) {
                    if (4 == h) result += "CD";
                    else if (9 == h) result += "CM";
                    else if (h < 5)
                        for (int i = 0; i < h; i++) result += "C";
                    else if (h >= 5) {
                        result += "D";
                        for (int i = 5; i < h; i++) result += "C";
                    }
                    num %= 100;
                    continue;
                }
                // 最高位十位，注意40（XL）90（XC）
                h = num / 10;
                if (h < 10 && h > 0) {
                    if (4 == h) result += "XL";
                    else if (9 == h) result += "XC";
                    else if (h < 5)
                        for (int i = 0; i < h; i++) result += "X";
                    else if (h >= 5) {
                        result += "L";
                        for (int i = 5; i < h; i++) result += "X";
                    }
                    num %= 10;
                    continue;
                }
                // 最高位个位，注意4（IV）9（IX）
                h = num;
                if (4 == h) result += "IV";
                else if (9 == h) result += "IX";
                else if (h < 5)
                    for (int i = 0; i < h; i++) result += "I";
                else if (h >= 5) {
                    result += "V";
                    for (int i = 5; i < h; i++) result += "I";
                }
                break;
            }
            return result;
        }
    }

    /**
     * copied from leetcode, kind of tricky
     * 打表就无难度了
     */
    private static class Solution2 {
        public String intToRoman(int num) {
            String M[] = {"", "M", "MM", "MMM"};
            String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
            String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
        }
    }
}
