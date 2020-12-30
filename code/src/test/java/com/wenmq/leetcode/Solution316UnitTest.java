package com.wenmq.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Solution316UnitTest {

    String TAG = "com.wenmq.leetcode.Solution316";
    private Solution316 mTestSolution;


    @Before
    public void setUp() throws Exception {
        mTestSolution = new Solution316();
    }

    @Test
    public void testRemoveDuplicateLetters_case1() {
        String s = "bcabc";
        String expect = "abc";
        String actual1 = mTestSolution.removeDuplicateLetters(s);
        Assert.assertEquals(expect,actual1);
        String actual2 = mTestSolution.removeDuplicateLetters2(s);
        Assert.assertEquals(expect,actual2);
    }

    @Test
    public void testRemoveDuplicateLetters_case2() {
        String s = "cbacdcbc";
        String expect = "acdb";
        String actual1 = mTestSolution.removeDuplicateLetters(s);
        Assert.assertEquals(expect,actual1);
        String actual2 = mTestSolution.removeDuplicateLetters2(s);
        Assert.assertEquals(expect,actual2);
    }
}