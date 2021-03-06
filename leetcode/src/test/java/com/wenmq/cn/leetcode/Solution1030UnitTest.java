package com.wenmq.cn.leetcode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Solution1030UnitTest {

    private static final String TAG = "com.wenmq.cn.leetcode.Solution1030";

    private Solution1030 mTestSolution;


    @Before
    public void setUp() throws Exception {
        mTestSolution = new Solution1030();
    }

    @Test
    public void testAllCellsDistOrder_Case1() {
        int r = 1;
        int c = 2;
        int r0 = 0;
        int c0 = 0;
        int[][] expected = new int[][]{{0, 0}, {0, 1}};
        Assert.assertArrayEquals(expected, mTestSolution.allCellsDistOrder(r, c, r0, c0));
    }

    @Test
    public void testAllCellsDistOrder_Case2() {
        int r = 2;
        int c = 2;
        int r0 = 0;
        int c0 = 1;
        int[][] expected = new int[][]{{0, 1}, {1, 1}, {0, 0}, {1, 0}};
//        int[][] expected = new int[][]{{0, 1}, {0, 0}, {1, 1}, {1, 0}};
        Assert.assertArrayEquals(expected, mTestSolution.allCellsDistOrder(r, c, r0, c0));
    }

    @Test
    public void testAllCellsDistOrder_Case3() {
        int r = 2;
        int c = 3;
        int r0 = 1;
        int c0 = 2;
        int[][] expected = new int[][]{{1, 2}, {0, 2}, {1, 1}, {1, 0}, {0, 1}, {0, 0}};
//        int[][] expected = new int[][]{{1, 2}, {0, 2}, {1, 1}, {0, 1}, {1, 0}, {0, 0}};
        Assert.assertArrayEquals(expected, mTestSolution.allCellsDistOrder(r, c, r0, c0));
    }
}