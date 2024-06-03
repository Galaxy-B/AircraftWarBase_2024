package test;

import org.junit.Test;
import org.junit.Before;

import org.junit.After;
import org.junit.Assert;


public class Test01 
{
    private StrassenMatrixMultiplication smm;
    private int[][] matrix1 = {{3, 1, 4}, {1, 5, 9}, {2, 6, 5}};
    private int[][] matrix2 = {{3, 5, 8}, {9, 7, 9}, {3, 2, 3}};

    private int[][] P1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private int[][] C1 = {{0, 0}, {0, 0}};
    private int[][] P2 = {{1, 2, 3}, {4, 0, 0}, {7, 0, 0}};
    private int[][] C2 = {{5, 6}, {8, 9}};

    private int[][] result1 = {{6, 6, 12}, {10, 12, 18}, {5, 8, 8}}; 
    private int[][] result2 = {{0, -4, -4}, {-8, -2, 0}, {-1, 4, 2}}; 
    private int[][] result3 = {{30, 30, 45}, {75, 58, 80}, {75, 62, 85}};
    private int[][] result4 = {{5, 6}, {8, 9}};
    private int[][] result5 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    
    @Before
    public void setUp()
    {
        smm = new StrassenMatrixMultiplication();
    }

    @After
    public void tearDown()
    {
        smm = null;
    }

    @Test
    public void addTest()
    {
        Assert.assertArrayEquals(result1, smm.add(matrix1, matrix2));
    }
    
    @Test
    public void subTest()
    {
        Assert.assertArrayEquals(result2, smm.sub(matrix1, matrix2));
    }

    @Test
    public void mulTest()
    {
        Assert.assertArrayEquals(result3, smm.multiply(matrix1, matrix2));
    }

    @Test
    public void splitTest()
    {
        smm.split(P1, C1, 1, 1);
        Assert.assertArrayEquals(result4, C1);
    }

    @Test
    public void joinTest()
    {
        smm.join(C2, P2, 1, 1);
        Assert.assertArrayEquals(result5, P2);
    }
}