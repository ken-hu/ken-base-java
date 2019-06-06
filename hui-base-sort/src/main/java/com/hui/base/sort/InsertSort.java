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
     * 每步将一个待排序的记录，
     * 按其顺序码大小插入到前面已经排序的子序列的合适位置（从后向前找到合适位置后），
     * 直到全部插入排序完为止。
     * {4,5,1,2,8,6,7,3,10,9} 
     * 取无序区间的第一个，从右向左扫描有序区间比较，方括号内可视为有序区间。 
     * 第一次：[4],5,1,2,8,6,7,3,10,9 
     * 第二次：[4,5],1,2,8,6,7,3,10,9 
     * 第三次：[1,4,5],2,8,6,7,3,10,9 
     * 第四次：[1,2,4,5],8,6,7,3,10,9 
     * 第五次：[1,2,4,5,8],6,7,3,10,9 
     * 第六次：[1,2,4,5,6,8],7,3,10,9 
     * 第七次：[1,2,4,5,6,7,8],3,10,9 
     * 第八次：[1,2,3,4,5,6,7,8],10,9 
     * 第九次：[1,2,3,4,5,6,7,8,10],9 
     * 第十次：[1,2,3,4,5,6,7,8,9,10]
     *
     * @param arry
     */
    public static void insertSort(int arry[]) {
        for (int i = 1; i < arry.length; i++) {
            int tmp = arry[i];
            int j;
            for (j = i - 1; j >= 0 && arry[j] > tmp; j--) {
                arry[j + 1] = arry[j];
            }
            arry[j + 1] = tmp;
        }
    }
}
