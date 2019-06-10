package com.hui.base.sort;

import java.util.Arrays;

/**
 * <b><code>SelectSort</code></b>
 * <p/>
 * Description 选择排序
 * <p/>
 * <b>Creation Time:</b> 2019/6/6 16:02.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class SelectSort {
    public static void main(String[] args) {
        int arry[] = {54, 23, 96, 075, 01, 7438, 9};

        selectSort(arry);

        System.out.println(Arrays.toString(arry));

    }

    /**
     * 选择排序
     * 从未排序序列中，找到关键字最小的元素
     * 如果最小元素不是未排序序列的第一个元素，将其和未排序序列第一个元素互换
     * 重复1、2步，直到排序结束。
     */
    public static void selectSort(int arry[]) {
        for (int i = 0; i < arry.length; i++) {
            //待确定的位置
            int min = i;
            //选择出应该在第i个位置的数
            for (int j = i + 1; j < arry.length; j++) {
                if (arry[j] < arry[min]) {
                    min = j;
                }
            }
            //交换两个数
            int temp = arry[i];
            arry[i] = arry[min];
            arry[min] = temp;
        }
    }
}
