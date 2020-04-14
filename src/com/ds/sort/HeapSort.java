package com.ds.sort;

/**
 * @author TR
 * @content 堆排序
 */
public class HeapSort {
    public int[] heapSort(int[] nums) {
        //获得初始有序堆，调整堆，从最后一个非叶子节点开始
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            doCoordinate(nums,i,nums.length);
        }
        // 交换最大值，调整堆
        int length = nums.length - 1;
        while (length >= 1) {
            int temp = nums[0];
            nums[0] = nums[length];
            nums[length] = temp;
            doCoordinate(nums,0,length--);
        }

        return nums;
    }

    // 在给定的根到末尾调整
    public void doCoordinate(int[] nums,int parent,int length) {
        while (parent <= length/2-1) {
            int lChild = parent*2 + 1;
            // 最后一个非叶节点且无右节点的情况下
            if (parent * 2 + 2 > length - 1) {
                if (nums[parent] < nums[lChild]) {
                    int temp = nums[parent];
                    nums[parent] = nums[lChild];
                    nums[lChild] = temp;
                }
                break;
            }
            int rChild = parent * 2 + 2;
            if (nums[rChild]>nums[parent]&&nums[rChild]>=nums[lChild]){
                int temp = nums[parent];
                nums[parent] = nums[rChild];
                nums[rChild] = temp;
            } else if (nums[lChild] > nums[parent]) {
                int temp = nums[parent];
                nums[parent] = nums[lChild];
                nums[lChild] = temp;
            }
            parent = parent  + 1;
        }
    }
}
class testHeapSort {
    public static void main(String[] args) {
        int[] nums = {45, 38, 65, 97, 76, 13, 27, 49};
        HeapSort insertSort = new HeapSort();
        int[] sort = insertSort.heapSort(nums);
        for (int i :
                sort) {
            System.out.println(i);
        }
    }
}
