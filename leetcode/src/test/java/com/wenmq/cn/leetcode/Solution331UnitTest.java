package com.wenmq.cn.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Solution331UnitTest {

    private static final String TAG = "com.wenmq.cn.leetcode.Solution331";
    private Solution331 mTestSolution;


    @Before
    public void setUp() throws Exception {
        mTestSolution = new Solution331();
    }

    @Test
    public void testIsValidSerialization_Case1() {
        String preOrder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        final boolean expected = true;
        Assert.assertEquals(expected, mTestSolution.isValidSerialization(preOrder));
    }

    @Test
    public void testIsValidSerialization_Case2() {
        String preOrder = "1,#";
        final boolean expected = false;
        Assert.assertEquals(expected, mTestSolution.isValidSerialization(preOrder));
    }

    @Test
    public void testIsValidSerialization_Case3() {
        String preOrder = "9,#,#,1";
        final boolean expected = false;
        Assert.assertEquals(expected, mTestSolution.isValidSerialization(preOrder));
    }

}