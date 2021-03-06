package com.wenmq.cn.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Solution746UnitTest {

    public static final String TAG = "com.wenmq.cn.leetcode.Solution746";
    private Solution746 mTestSolution;


    @Before
    public void setUp() throws Exception {
        mTestSolution = new Solution746();
    }

    @Test
    public void testMinCostClimbingStairs_case1() {
        int[] input = {10, 15, 20};
        int expectCost = 15;
        int actualCost = mTestSolution.minCostClimbingStairs(input);
        Assert.assertEquals(expectCost, actualCost);
    }

    @Test
    public void testMinCostClimbingStairs_case2() {
        int[] input = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int expectCost = 6;
        int actualCost = mTestSolution.minCostClimbingStairs(input);
        Assert.assertEquals(expectCost, actualCost);
    }
}