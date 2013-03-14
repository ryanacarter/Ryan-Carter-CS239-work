import java.util.ArrayList;

/**
 * ArrayListManager - is the class the is responsible for handling and
 * maintaining the arrayList of the ints.
 * 
 * Lab 08 - Java Collections (ArrayList)
 * 
 * @author Ryan Carter
 * @version 2/11/2013
 *
 */
public class ArrayListManager
{
    
    private ArrayList< Integer > list;
    
    /**
     * ArrayListManager - is used as the default constructor for the 
     * ArrayListManager Class. It will initialize the ArrayList that is to be 
     * used. The initial size of the array is set to 10 by the Java API and
     * is dynamically reset based on the size of the array.
     */
    public ArrayListManager()
    {
        
        list = new ArrayList<Integer>();
        
    } // end Constructor
    
    //-------------------------PUBLIC METHODS----------------------------//


    /**
     * getList - is used to return the list to the method that called it. 
     *
     * @return
     */
    public ArrayList< Integer > getList()
    {
        return list;
        
    } // end getList
    
    /**
     * add - is used to add an int the the arraylist. it will add the int while
     * sorting it into the array from lowest to highest
     * values
     * 
     * @param toAdd (int)
     */
    public void add (int toAdd)
    {
        // Used to hold the spot that we are adding the int to
        int spot;
        
        // find the spot using the sort method.
        spot = sort(toAdd);

        
        // add to that spot the int that was passed to the method
        list.add(spot, toAdd);
        
    } // end add
    
    /**
     * getSize - return the size of the array to the method that called it.
     * 
     * @return
     */
    public int getSize()
    {
        return list.size();
    }
    
    //-------------------------PRIVATE METHODS----------------------------//
    
    /**
     * sort - is used to add an int to the array at the correct spot. It will
     * sort the values from low to high.
     * 
     * @param toSort
     * @return
     */
    private int sort(int toSort)
    {
        // declarations and initializations
        int counter = 0;
        
        // If there is nothing in the List add it at the beginning
        if (list.size() == 0)
            toSort = 0;
        // if it smaller that the first spot in the ArrayList add it at spot 0
        else if (toSort < list.get(0))
            toSort = 0;
        // see if it is larger than the last spot in the ArrayList
        else if(toSort >= list.get(list.size() - 1))
            toSort = list.size();
        // if it is in the middle, loop through and find the spot that it goes
        else
        {
            // iterate a loop the looks for the spot to place the value.
            while (toSort >= list.get(counter))
            {
                counter++;
            }
            
            toSort = counter;
            
        }
        // Return the spot that was found.
        return toSort;
    
    } // end sort
    
} // end class
