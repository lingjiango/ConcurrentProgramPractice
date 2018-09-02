package com.juc.cpp.basis;
/*
此类协助理解位运算
理解位运算方便理解ReentrantReadWriteLock的锁控制
 */
public class BitOperationTest {
    public static void main(String[] args) {
        System.out.println("10&12 = " + (10&12));
        System.out.println("-6&-2 = " + (-6&-2));
        System.out.println("10|12 = " + (10|12));
        System.out.println("-6|-2 = " + (-6|-2));
        System.out.println("~7 = " + (~7));
        System.out.println("~(-6) = " + (~(-6)));
        System.out.println("15^2 = " + (15^2));
        System.out.println("2<<2 = " + (2<<2));
        System.out.println("-2<<2 = " + (-2<<2));
        System.out.println("2>>2 = " + (2>>2));
        System.out.println("-6>>2 = " + (-6>>2));
        System.out.println("-1>>>1 = " + (-1>>>1));
        System.out.println("Integer.MAX_VALUE = " + (Integer.MAX_VALUE ));

        System.out.println("2 is Odd " + (isOddNumber(2)));
        System.out.println("3 is Odd " + (isOddNumber(3)));
        System.out.println("5 is Odd " + (isOddNumber(5)));

        int[] nums = new int[]{1,2,3,4,5};
        reverse(nums);
        for(int i=0; i<nums.length; i++)
            System.out.print(nums[i] + "\t");

        System.out.print("\n");
        System.out.println("-3 absoulte value " + (getAbsoulteVal(-3)));
        System.out.println("5 absoulte value " + (getAbsoulteVal(5)));
    }
    /*
    判断是否奇数
     */
    public static boolean isOddNumber(int num) {
        if ((num & 1) == 1) return true;
        return false;
    }
    /*
     三次异或操作完成变量的替换
     */
    public  static int[] reverse(int[] nums) {
        int i = 0;
        int j = nums.length - 1;

        while(j>i) {
            nums[i] = nums[i]^nums[j];
            nums[j] = nums[j]^nums[i];
            nums[i] = nums[i]^nums[j];
            j--;
            i++;
        }
        return nums;
    }

    /*
    获取绝对值
     */
    public static int getAbsoulteVal(int num) {
        return (num^(num>>31))-(num>>31);
    }
}
