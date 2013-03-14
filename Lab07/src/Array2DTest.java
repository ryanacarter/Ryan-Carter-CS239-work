import junit.framework.TestCase;

/**
 * Array2DTest will be used to test the classes in the Array 2D.java file
 * 
 * @author Ryan Carter
 * @version 2/4/2013
 */
public class Array2DTest extends TestCase
{
    // Declarations for all of the testing
    public double test1[][] =  {
            {2.0, 2.0, 3.0, 4.0}, 
            {1},
            {1.3, 2.3, 2, 0}, 
            {5.0, 3.0, 2.2, 3.0, 5.0},
            {-1.0, 0.0, 1.0}
    };
    public Array2D array2D = new Array2D(test1);
    public double DELTA = 1e-3;

    /**
     * Test method for {@link Array2D#getAverage()}.
     */
    public void testGetAverage()
    {
        assertEquals(2.105, array2D.getAverage(), DELTA);
    }



    /**
     * Test method for {@link Array2D#getColumnTotal(int)}.
     */
    public void testGetColumnTotal()
    {
        assertEquals(8.3, array2D.getColumnTotal(0));
        assertEquals(7.3, array2D.getColumnTotal(1));
    }



    /**
     * Test method for {@link Array2D#getHighestInRow(int)}.
     */
    public void testGetHighestInRow()
    {
        assertEquals(4.0, array2D.getHighestInRow(0));
        assertEquals(1.0, array2D.getHighestInRow(1));
        assertEquals(2.3, array2D.getHighestInRow(2));
        assertEquals(5.0, array2D.getHighestInRow(3));
        assertEquals(1.0, array2D.getHighestInRow(4));
    }



    /**
     * Test method for {@link Array2D#getLowestInRow(int)}.
     */
    public void testGetLowestInRow()
    {
        assertEquals(2.0, array2D.getLowestInRow(0));
        assertEquals(1.0, array2D.getLowestInRow(1));
        assertEquals(0.0, array2D.getLowestInRow(2));
        assertEquals(2.2, array2D.getLowestInRow(3));
        assertEquals(-1.0, array2D.getLowestInRow(4));
    }



    /**
     * Test method for {@link Array2D#getRowTotal(int)}.
     */
    public void testGetRowTotal()
    {
        assertEquals(11.0, array2D.getRowTotal(0));
        assertEquals(1.0, array2D.getRowTotal(1));
        assertEquals(5.6, array2D.getRowTotal(2));
        
    }



    /**
     * Test method for {@link Array2D#getTotal()}.
     */
    public void testGetTotal()
    {
        assertEquals(35.8, array2D.getTotal());
    }



    /**
     * Test method for {@link Array2D#isCol(int)}.
     */
    public void testIsCol()
    {
        assertTrue(array2D.isCol(1));
        assertTrue(array2D.isCol(4));
        assertFalse(array2D.isCol(5));
    }



    /**
     * Test method for {@link Array2D#isRow(int)}.
     */
    public void testIsRow()
    {
        assertTrue(array2D.isRow(1));
    }

}
