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
     * 在要排序的一组数中，
     * 选出最小的一个数与第一个位置的数交换；
     * 然后在剩下的数当中再找最小的与第二个位置的数交换，
     * 如此循环到倒数第二个数和最后一个数比较为止
     */
    public static void selectSort(int arry[]) {
        for (int i = 0; i < arry.length; i++) {
            //待确定的位置
            int k = i;
            //选择出应该在第i个位置的数
            for (int j = arry.length - 1; j > i; j--) {
                if (arry[j] < arry[k]) {
                    k = j;
                }
            }
            //交换两个数
            int temp = arry[i];
            arry[i] = arry[k];
            arry[k] = temp;
        }
    }
}
