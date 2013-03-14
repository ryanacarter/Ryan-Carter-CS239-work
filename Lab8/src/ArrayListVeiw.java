import java.io.*;
import java.util.ArrayList;

/**
 * ArrayListVeiw - is used to handle all of the viewing aspect of the program.
 * So it controls what it is that the user sees.
 * 
 * Lab 08 - Java Collections (ArrayList)
 * 
 * @author Ryan Carter
 * @version 2/11/2013
 * 
 */
public class ArrayListVeiw
{

    private BufferedReader reader;

    /**
     * ArrayListView is used to initialize the BufferedReader
     */
    public ArrayListVeiw()
    {
        reader = new BufferedReader (
                new InputStreamReader(System.in));
    } // end constructor

    /**
     * userInput - is used to prompt the user to enter a value into the program.
     * it does not do error checking, all it doing is taking the input, placing it
     * into a String, and sending back to the controller.
     * 
     * @return
     * @throws IOException
     */
    public void askForInput()
    {
        System.out.print("\nEnter and Interger Value or press (Q) to Quit -> ");

    } // end userInput

    /**
     * error - this method is used to print out and retrieve new input when the
     * user enters an incorrect value into the program.
     * 
     * @return
     * @throws IOException
     */
    public void error()
    {   
        System.out.print("\nThat is not an Integer Value.\n" +
                "Please try again -> ");

    } // end error

    /**
     * getInput() - the purpose of this method is to get string input from the
     * keyboard and return that to the method that called it.
     * 
     * @return
     * @throws IOException
     */
    public String getInput() throws IOException
    {
        // Read in the input from the keyboard.       
        return reader.readLine().toLowerCase();
    }
    /**
     * quitMessage - this method is used to send the final message to the
     * user when they leave the program.
     * 
     * @param arrayList
     */
    public void quitMessage(int size, ArrayList< Integer > arrayList)
    {
        System.out.print("\nThe array length is: " + size + "\n");
        System.out.print("The List contains " + arrayList + "\n");
    } // end quitMessage
    
} // end class