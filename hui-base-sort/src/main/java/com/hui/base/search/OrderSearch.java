package com.hui.base.search;

/**
 * <b><code>OrderSearch</code></b>
 * <p/>
 * Description: 顺序查找法
 * <p/>
 * <b>Creation Time:</b> 2019/6/11 20:59.
 *
 * @author HuWeihui
 */
public class OrderSearch {
    public static void main(String[] args) {
        int arry[] = {54,23,96,075,01,7438,9};

        int i = orderSearch(arry, 7438);

        System.out.println(i);
    }


    public static int orderSearch(int arry[], int key) {
        for (int i = 0; i < arry.length; i++) {
            if (arry[i] == key){
                return i;
            }
        }
        return -1;
    }
}
