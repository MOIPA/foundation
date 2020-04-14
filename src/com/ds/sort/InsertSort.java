package com.ds.sort;

/**
 * @author TR
 * @content 直接插入排序
 * 折半插入排序
 * 希尔排序
 */
public class InsertSort {

    public int[] insertSort(int[] nums) {
        int temp, j;
        for (int i = 1; i < nums.length; i++) {
            j = i - 1;// 完成排序指针
            temp = nums[i]; // 待排序
            while (j >= 0 && temp < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[++j] = temp;
        }
        return nums;
    }

    public int[] halfInsertSort(int[] nums) {
        int temp, low, high, mid;
        for (int i = 1; i < nums.length; i++) {
            low = 0;
            high = i - 1;
            temp = nums[i];
            while (low <= high) {
                mid = (low + high) / 2;
                if (temp >= nums[mid]) low = mid + 1;
                if (temp < nums[mid]) high = mid - 1;
            }
            for (int j = i - 1; j >= high + 1; j--) nums[j + 1] = nums[j];
            nums[high + 1] = temp;
        }
        return nums;
    }

    public int[] shellInsertSort(int[] nums) {
        int n1 = 5, n2 = 2, n3 = 1; //增量因子
        int[] results = doShellSort(nums, n1);
        results = doShellSort(results, n2);
        return doShellSort(results, n3);
    }

    private int[] doShellSort(int nums[], int n) {
        for (int j = 0; j < nums.length - n; j++) {
            int count = 0;
            for (int i = j; i < nums.length; i += n) count++;
            int[] sortArray = new int[count];
            count = 0;
            for (int i = j; i < nums.length; i += n) {
                sortArray[count++] = nums[i];
            }
            int[] results = insertSort(sortArray);
            count = 0;
            for (int i = j; i < nums.length; i += n) {
                nums[i] = results[count++];
            }
        }
        return nums;
    }

}

class testInsertSort {
    public static void main(String[] args) {
        int[] nums = {45, 38, 65, 97, 76, 13, 27, 49};
        InsertSort insertSort = new InsertSort();
        int[] sort = insertSort.shellInsertSort(nums);
        for (int i :
                sort) {
            System.out.println(i);
        }
    }
}
