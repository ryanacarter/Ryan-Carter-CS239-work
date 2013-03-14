import java.io.IOException;

/**
 * ArrayListController - The class will be used to handle user input and
 * control what the program will be doing.
 * 
 * Lab 08 - Java Collections (ArrayList)
 * 
 * @author Ryan Carter
 * @version 2/11/2013
 * 
 */
public class ArrayListController
{

    ArrayListVeiw aLV;
    ArrayListManager aLM;

    /**
     * ArrayList - The default Constructor is going to be used to initialize 
     * the BufferedReader to allow user input.
     */
    public ArrayListController()
    {
        aLV = new ArrayListVeiw();
        aLM = new ArrayListManager();
    }

    /**
     * start - starts the program.
     * @throws IOException 
     */
    public void run() throws IOException
    {
        String sInput;
        int iInput;
        boolean endLoop = false;
        boolean errorLoop;

        do
        {
            // Print out the statement asking the user for there input.
            aLV.askForInput();

            // get user input while the user does not want to quit the program
            do
            {
                // get the user input
                sInput = aLV.getInput();

                // if the input does not equal q try and add the value to the List
                if (!sInput.equals("q"))
                {
                    try
                    {
                        // try to parse out the integer value
                        iInput = Integer.parseInt(sInput);
                        // add it to the list
                        aLM.add(iInput);
                        // end the method
                        errorLoop = true;
                    }
                    // if that fails print an error and try again
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nInvalid Entry.");
                        System.out.print("Please enter a valid int -> ");
                        errorLoop = false;
                    }
                }
                // if it equals q fill the error checking loop and main loop
                else
                {
                    errorLoop = true;
                    endLoop = true;
                }
            }
            while (!errorLoop);
        }
        while (!endLoop);

        aLV.quitMessage(aLM.getSize(), aLM.getList());
        System.exit(0);
    }

}
