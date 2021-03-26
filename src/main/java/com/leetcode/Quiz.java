package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/16 10:16
 */
public class Quiz {
    public static void main(String[] args) {
        List<List<Integer>> inputs = new LinkedList<>();
        inputs.add(Arrays.asList(1, 0, 0, 1, 0));
        inputs.add(Arrays.asList(1, 0, 1));
        inputs.add(Arrays.asList(0, 0, 1, 0, 1));
        inputs.add(Arrays.asList(1, 0, 1, 0, 1));
        inputs.add(Arrays.asList(1, 0, 1, 1));
        inputs.forEach(x -> {
            x.forEach(System.out::print);
            System.out.println("");
        });

        System.out.println("结果:"+maxArea(inputs));
    }

    /**
     * 思路：递归查询邻近
     *
     * @param input
     * @return
     */
    public static Integer maxArea(List<List<Integer>> input) {
        int max = 0;
        int temp = 0;
        for (int x = 0; x < input.size(); x++) {
            List<Integer> col = input.get(x);
            for (int y = 0; y <col.size() ; y++) {
                temp = getAround(input, x, y, 0);
                if (max<temp)max = temp;

            }
        };
        return max;
    }

    /**
     *
     * @param input
     * @param x
     * @param y
     * @param direct 从哪个方向来的，否则StackOverflow  0 遍历四周 1：从上方来遍历 左右下 2:从下方来 3：从左边来 4：从右边来
     * @return
     */
    private static Integer getAround(List<List<Integer>> input, int x, int y,int direct) {
        int num = 0;
        // 是否越界
        if (x < 0 || y < 0) return 0;
        if (x >= input.size()) return 0;
        if (y >= input.get(x).size()) return 0;
        // 如果查找为1 返回1
        if (input.get(x).get(y) == 1) {
            num += 1;
            //  上
            if (direct!=1)num += getAround(input, x - 1, y,2);
            // 下
            if (direct!=2)num += getAround(input, x + 1, y,1);
            // 左
            if (direct!=3)num += getAround(input, x, y - 1,4);
            // 右
            if (direct!=4)num += getAround(input, x, y + 1,3);
        } else {
            // 遇到0 边界 结束
            return 0;
        }

        return num;
    }
}
