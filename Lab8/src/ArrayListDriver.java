import java.io.IOException;

/**
 * ArrayList is a program that will allow a use to enter in whole numbers,
 * the ArrayList will sort the numbers and when the user leaves the program
 * it will print the number of slots used and the ints that are contained in
 * the list.
 * 
 * Lab 08 - Java Collections (ArrayList)
 * 
 * @author Ryan Carter
 *@version 2/11/2013
 */
public class ArrayListDriver
{

    /**
     * main - is used to start the program.
     * 
     * @param args
     */
    public static void main(String[] args) throws IOException
    {
       
        ArrayListController aLC = new ArrayListController();
        
        aLC.run();

    } // end main

} // end class
