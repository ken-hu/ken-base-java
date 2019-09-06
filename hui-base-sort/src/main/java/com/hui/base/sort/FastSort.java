package com.hui.base.sort;

import java.util.Arrays;

/**
 * <b><code>FastSort</code></b>
 * <p/>
 * Description 快速排序
 * <p/>
 * <b>Creation Time:</b> 2019/6/6 17:56.
 *
 * @author Hu-Weihui
 * @since hui-base-java ${PROJECT_VERSION}
 */
public class FastSort {

    public static void main(String[] args) {

        int arry[] = {54,23,96,75,1,7438,9};

        fastSort(arry,0,arry.length-1);

        System.out.println(Arrays.toString(arry));
    }

    /**
     * 快速排序
     *
     * 从数列中挑出一个元素，称为"基准"（pivot）。
     * 重新排序数列，所有比基准值小的元素摆放在基准前面，
     * 所有比基准值大的元素摆在基准后面（相同的数可以到任一边）
     * 在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     * 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     *
     * @param arry
     * @param low
     * @param high
     */
    private static void fastSort(int arry[],int low,int high){
            //已经排完
            if (low >= high) {
                return;
            }
            int left = low;
            int right = high;

            //保存基准值
            int pivot = arry[left];
            while (left < right) {
                //从后向前找到比基准小的元素
                while (left < right && arry[right] >= pivot){
                    right--;
                }
                arry[left] = arry[right];
                //从前往后找到比基准大的元素
                while (left < right && arry[left] <= pivot){
                    left++;
                }
                arry[right] = arry[left];
            }
            // 放置基准值，准备分治递归快排
            arry[left] = pivot;
        fastSort(arry, low, left - 1);
        fastSort(arry, left + 1, high);
    }
}
