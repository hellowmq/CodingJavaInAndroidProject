package com.wenmq.cn.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Solution204UnitTest {

    private Solution204 mTestSolution;

    @Before
    public void setUp() throws Exception {
        mTestSolution = new Solution204();
    }

    @Test
    public void testCountPrimes_Case1() {
        Assert.assertEquals(4, mTestSolution.countPrimes(10));
        Assert.assertEquals(4, mTestSolution.countPrimes2(10));
    }

    @Test
    public void testCountPrimes_Case2() {
        Assert.assertEquals(0, mTestSolution.countPrimes(0));
        Assert.assertEquals(0, mTestSolution.countPrimes2(0));
    }

    @Test
    public void testCountPrimes_Case3() {
        Assert.assertEquals(0, mTestSolution.countPrimes(1));
        Assert.assertEquals(0, mTestSolution.countPrimes2(1));
    }

    @Test
    public void testCountPrimes_Case4() {
        Assert.assertEquals(25, mTestSolution.countPrimes(100));
        Assert.assertEquals(25, mTestSolution.countPrimes2(100));
    }
}