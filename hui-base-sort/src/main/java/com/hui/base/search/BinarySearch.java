package com.hui.base.search;


/**
 * <b><code>BinarySearch</code></b>
 * <p/>
 * Description:二分查找（折半查找）
 * 1. 要求待查找的序列有序
 * 2. 每次取中间位置的值与待查关键字比较
 * 3. 如果中间位置的值比待查关键字大，则在前半部分循环这个查找的过程，
 * 4. 如果中间位置的值比待查关键字小，则在后半部分循环这个查找的过程
 * <p/>
 * <b>Creation Time:</b> 2019/6/11 21:06.
 *
 * @author HuWeihui
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arry[] = {1, 2, 3, 4, 5, 6, 8, 12, 17, 25, 77, 89,788};

        int i = binarySearch(arry, 25);

        System.out.println(i);
    }

    /**
     * 二分查找 （折半查找）
     *
     * @param arry
     * @param key
     * @return
     */
    private static int binarySearch(int arry[], int key) {
        //定义变量记录 头、尾、中间的位置
        int low, mid, high;
        low = 0;
        high = arry.length - 1;

        //循环判断
        while (low <= high) {
            mid = (high + low) / 2;
            if (key > arry[mid]) {
                low = mid + 1;
            }
            else if (key < arry[mid]) {
                high = mid - 1;
            }else {
                return mid;
            }
        }
        return 0;
    }
}
