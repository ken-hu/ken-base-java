package com.hui.base.algorithm;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <code>TwoSum</code>
 * <desc>
 * 描述：两数之和
 * <desc/>
 * <b>Creation Time:</b> 2021/1/9 1:09.
 *
 * @author Gary.Hu
 */
public class TwoSum {

    /**
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 的那两个整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * 你可以按任意顺序返回答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 枚举法（自己解题）
     * 遗漏点：
     * 1.不细心读题（每种输入只会对应一个答案），漏了最后的抛异常
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No tow sum solution");
    }

    /**
     * 查表法
     * 空间换时间
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> remark = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int targetNum = target - nums[i];
            if (remark.containsKey(targetNum)) {
                return new int[]{remark.get(targetNum), i};
            }
            remark.put(nums[i], i);
        }
        throw new IllegalArgumentException("No tow sum solution");
    }

    public static void main(String[] args) {
        int[] nums = new int[]{8, 2, 3, 4, 10, 1};
        int[] res = twoSum2(nums, 9);
        System.out.println(Arrays.toString(res));
    }
}
