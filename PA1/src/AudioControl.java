import java.io.*;

/**
 * Audio Control is the controller class that will be used to gather user input
 * and coordinate that with the view and object classes.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor 
 *                                 received assistance for this assignment 
 *                                 except as noted below:
 *                                 
 *                                 None
 * 
 * @author Ryan Carter
 * @version 1/31/2013 V1
 */
public class AudioControl 
{

    private AudioView aV1;
    private AudioList collection;

    /**
     * Audio Control - is the default constructor which sets the object values
     * to null.
     */
    public AudioControl()
    {
        // Set the objects
        aV1 = new AudioView();
        collection = new AudioList();
    }

    /**
     * getUserInput - Grabs input from the user, validates that the input is
     * correct, and finds directs the flow of the program based on the input
     * from the user.
     * 
     * @throws IOException
     */
    public void mainMenu() throws IOException
    {
        // declaration and initializations
        char option = 0;

        // constantly repeat for the entirety of the program.
        while (true)
        {
            // start the main screen
            if (collection == null)
            {
                aV1.mainScreen();
            }
            else
            {
                aV1.mainScreen(collection);
            }

            option = aV1.getMainScreenOption();
            // The switch statement handles all actions of the users input.
            switch (option)
            {
                case 'a': 
                {
                    aV1.add(collection);
                }
                    break;

                case 'e':
                {
                    aV1.edit(collection);
                }
                    break;
                case 'd':
                {
                    aV1.delete(collection);
                }
                    break;
                case 's':
                {
                    aV1.search(collection);
                }
                    break;
                case 'l':
                {
                    aV1.playList(collection);
                }
                    break;
                case 'p':
                {
                    aV1.play(collection);
                }
                    break;
                case 'n':
                {
                    aV1.next(collection);
                }
                    break;
                case 'b':
                {
                    aV1.back(collection);
                }
                    break;
                case 'q':
                {
                    aV1.quit();
                }
                    break;
                // The default case is used to catch user error and print of the
                // error message
                default:
                {
                    System.exit(0);
                }
            } // end switch statement
        } // end loop
    } // end getUserInputMethod

    /**
     * start - This method is used to start the audioControler class.
     * 
     * @throws IOException 
     */
    public void start() throws IOException
    {

        mainMenu();

    } // end start method

} // end Audio Control Class