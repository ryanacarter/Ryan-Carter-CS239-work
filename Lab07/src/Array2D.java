/**
 * Array2D - Provides tools for manipulating a 2D array
 * 
 * @author - YOUR NAME GOES HERE
 * @version - V1 - YOUR DATE GOES HERE
 */
public class Array2D
{
    private double[][] matrix;



    /**
     * Explicit value constructor
     * 
     * @param inArray - The array to use to create this Array2D object
     */
    public Array2D(double[][] inArray)
    {
        if ( inArray != null )
        {
            this.matrix = new double[inArray.length][];

            for (int row = 0; row < inArray.length; row++)
            {
                if ( inArray[ row ] != null )
                {
                    this.matrix[ row ] = new double[inArray[ row ].length];

                    for (int col = 0; col < inArray[ row ].length;col++)
                        this.matrix[ row ][ col ] = inArray[ row ][ col ];
                }
                else
                    this.matrix[ row ] = null;
            }
        }
        else
            this.matrix = null;
    }



    /**
     * getAverage averages all of the values of this array.
     * 
     * @return The average of the array elements
     */

    public double getAverage()
    {
        double counter = 0.0;
        double forReturn = 0.0;
        
        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[row].length; col++)
            {
                counter ++;
            }
        }
        
        forReturn = getTotal() / counter;
        
        return forReturn;
    }



    /**
     * getColumnTotal sums all of the values in one column of this array.
     * This method was named sumColumn in the worksheet
     * 
     * @param col - The number (0 based) of the col to sum
     * 
     * @return The sum of the array elements in that column. If the row does
     *         not exist in matrix....YOU FILL IN THE REST.
     */
    public double getColumnTotal(int col)
    {
        double total = 0.0;
        
        if(isCol(col))
        {
            for (int row = 0; row < matrix.length; row++)
            {
                try
                {
                    if (matrix[row].length >= col )
                        total += matrix[row][col]; 
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    
                }

            }
        }
        
        return total;
    }



    /**
     * getHighestInRow finds the largest value one row of this array.
     * 
     * @param row - The number (0 based) of the row to find the largest
     * 
     * @return The highest of the row array elements. If the row does not
     *         exist in matrix....YOU FILL IN THE REST.
     */
    public double getHighestInRow(int row)
    {
        double high = 0.0;
        
        for (int col = 0; col < matrix[row].length; col++)
        {
            if (matrix[row][col] > high)
            {
                high = matrix[row][col];
            }
        }
        return high;
    }



    /**
     * getLowestInRow finds the lowest value one row of this array. This
     * method was not in the worksheet, but is similar to getHighestInRow
     * 
     * @param row - The number (0 based) of the row to find the lowest
     * 
     * @return The lowest of the row array elements. If the row does not
     *         exist in matrix....YOU FILL IN THE REST.
     */
    public double getLowestInRow(int row)
    {
        double low = 0.0;
        
        for (int col = 0; col < matrix[row].length; col++)
        {
            if (matrix[row][col] < low)
            {
                low = matrix[row][col];
            }
            else if (col == 0)
            {
                low = matrix[row][col];
            }
        }
        return low;
    }



    /**
     * getRowTotal sums all of the values in one row of this array. This
     * method was named sumRow on the worksheet
     * 
     * @param row - The number (0 based) of the row to sum
     * 
     * @return The sum of the row array elements. If the row does not exist
     *         in matrix....YOU FILL IN THE REST.
     */

    public double getRowTotal(int row)
    {
        double total = 0.0;
        
        if(isRow(row))
        {
            for (int col = 0; col < matrix[row].length; col++)
            {
                total += matrix[row][col]; 
            }
        }
        
        return total;
    }



    /**
     * getTotal sums all of the values of this array.
     * 
     * @return The sum of the array elements
     */
    public double getTotal()
    {
        double total = 0;
        
        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[row].length; col++)
            {
                total += matrix[row][col];
            }
        }
        return total;
    }



    /**
     * Does this column exist?
     * 
     * @param col - The column to check
     * @return true if the row exists in this array, false otherwise
     */
    public boolean isCol(int col)
    {
        boolean forReturn = false;

        for(int row = 0; row < matrix.length; row++)
        {
            if (matrix[row].length > col)
            {
                forReturn = true;
            }
                
        }

        return forReturn;
    } // end class



    /**
     * Does this row exist?
     * 
     * @param row - The row to check
     * @return true if the row exists in this array, false otherwise
     */
    public boolean isRow(int row)
    {
        boolean forReturn = false;

        if (matrix[row] != null)
        {
            forReturn = true;
        }

        return forReturn;
    } // end method
    
}
