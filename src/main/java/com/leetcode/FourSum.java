package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tr
 * @date 2020/4/27 18:11
 */
public class FourSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        List<int[]> list = solution.twoSum(new int[]{1, 0, -1, 0, -2, 2}, 0, 0, 5, 0);
//        List<int[]> list = solution.threeSum(new int[]{1, 0, -1, 0, -2, 2}, 0, 5, 0, 0);
        List<List<Integer>> lists = solution.fourSum(new int[]{1,-2,-5,-4,-3,3,3,5}, -11);
        lists.forEach(x -> {
            x.stream().forEach(o -> {
                System.out.print(o + ",");
            });
            System.out.println();
        });
//        list.stream().forEach((x) -> {
//            Arrays.stream(x).forEach(System.out::print);
//            System.out.println();
//        });
    }

    private static class Solution {
        public List<List<Integer>> fourSum(int[] array, int target) {
            Arrays.sort(array);
            List<List<Integer>> result = new LinkedList<>();
            // four sum convert to three sum
            for (int i = 0; i < array.length-3; i++) {
                // skip
//                if (array[i] > target) break;
                result.addAll(threeSum(array, i+1, array.length - 1, target - array[i], array[i]));
                // skip
                while(array[i+1]==array[i] && i+3<array.length) i++;
            }
            return result;
        }

        public List<List<Integer>> threeSum(int[] array, int left, int right, int target, int thirdNum) {
            // convert to 2 sum
            List<List<Integer>> result = new LinkedList<>();
            for (int i = left; i < right - 1; i++) {
                result.addAll(twoSum(array, target - array[i], i + 1, right, array[i], thirdNum));
                while(i+2<right&&array[i]==array[i+1]) i++;
            }
            return result;
        }

        // two sum 双指针 一个头 一个尾
        public List<List<Integer>> twoSum(int[] array, int target, int left, int right, int thirdNum, int fourthNum) {
            List<List<Integer>> result = new LinkedList<>();
            while (left < right) {
                if (array[left] + array[right] == target) {
                    result.add(Arrays.asList(array[left], array[right], thirdNum, fourthNum));
                    while (left<right&&array[left + 1] == array[left])left++;
                    left++;
                    while (left<right&&array[right - 1] == array[right]) right--;
                    right--;
                } else if (array[left] + array[right] < target) {
                    while (left<right&&array[left + 1] == array[left]) left++;
                    left++;
                } else {
                    while (left<right&&array[right - 1] == array[right]) right--;
                    right--;
                }
            }
            return result;
        }
    }
}
