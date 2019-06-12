package com.hui.base.search;


/**
 * <b><code>BinarySearch</code></b>
 * <p/>
 * Description:二分查找（折半查找）
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
        int low, mid, high;
        low = 0;
        high = arry.length - 1;

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
