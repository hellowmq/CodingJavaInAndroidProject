package com.wenmq.cn.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Solution765UnitTest {

    private static final String TAG = "com.wenmq.cn.leetcode.Solution765";
    private Solution765 mTestSolution;


    @Before
    public void setUp() throws Exception {
        mTestSolution = new Solution765();
    }

    @Test
    public void testMinSwapsCouples_Case1() {
        int[] array = new int[]{0, 2, 1, 3};
        int result = 1;
        Assert.assertEquals(result, mTestSolution.minSwapsCouples(array));
    }

    @Test
    public void testMinSwapsCouples_Case2() {
        int[] array = new int[]{3, 2, 0, 1};
        int result = 0;
        Assert.assertEquals(result, mTestSolution.minSwapsCouples(array));
    }

    @Test
    public void testMinSwapsCouples2_Case1() {
        int[] array = new int[]{0, 2, 1, 3};
        int result = 1;
        Assert.assertEquals(result, mTestSolution.minSwapsCouples2(array));
    }

    @Test
    public void testMinSwapsCouples2_Case2() {
        int[] array = new int[]{3, 2, 0, 1};
        int result = 0;
        Assert.assertEquals(result, mTestSolution.minSwapsCouples2(array));
    }
}