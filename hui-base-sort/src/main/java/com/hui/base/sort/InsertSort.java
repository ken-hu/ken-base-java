package com.hui.base.sort;

import java.util.Arrays;

/**
 * <b><code>InsertSort</code></b>
 * <p/>
 * Description: 直接插入排序
 * <p/>
 * <b>Creation Time:</b> 2019/6/5 21:50.
 *
 * @author HuWeihui
 */
public class InsertSort {

    public static void main(String[] args) {
        int arry[] = {54, 23, 96, 075, 01, 7438, 9};

        insertSort(arry);

        System.out.println(Arrays.toString(arry));
    }


    /**
     * 插入排序
     * 1从第一个元素开始，该元素可以认为已经被排序
     * 2取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5将新元素插入到该位置后
     * 6重复步骤2~5
     *
     * @param arry
     */
    public static void insertSort(int arry[]) {
        for (int i = 1; i < arry.length; i++) {
            int tmp = arry[i];
            int j;
            for (j = i; j > 0 && arry[j - 1] > tmp; j--) {
                arry[j] = arry[j - 1];
            }
            arry[j] = tmp;
        }
    }
}
