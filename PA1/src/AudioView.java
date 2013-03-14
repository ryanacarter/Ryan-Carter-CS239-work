import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * All interaction between the user and the program will be here.  This is the
 * only class that can contain System.out.print/println() statements. This is
 * also the only class that can use Scanner or BufferedReader objects.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor 
 *                                 received assistance for this assignment 
 *                                 except as noted below:
 *                                 
 *                                 None
 * 
 * @author Ryan Carter
 * @version 1/31/2013 V1
 *
 */
public class AudioView 
{
    // declarations and initializations
    private int endingNumber;
    private int pageNumber;
    private int startingNumber;

    /**
     * add - is used to display the screen that will handle the
     * adding of the artist to the audio collection
     * 
     * @param collection is the array of AudioFiles (AudioList)
     * @throws IOException 
     */
    public void add(AudioList collection) throws IOException
    {
        // declarations and initializations
        String artist = null;
        String title = null;
        String album = null;
        int track = -1;

        // Main Heading at the top of the screen
        clearScreen();
        System.out.print(center("Add Audio File") + "\n\n\n");

        // This section of code will prompt the user for the artist's info.
        System.out.print("Artist: ");
        artist = getStringInput();
        while (artist.length() <= 0)
        {
            System.out.print("\nRequired entry.\nPlease re-enter -> ");
            artist = getStringInput();
            System.out.println();
        }

        // This section of code will prompt the user for the title info.
        System.out.print("Title: ");
        title = getStringInput();
        while (title.length() <= 0)
        {
            System.out.print("\nRequired entry.\nPlease re-enter -> ");
            title = getStringInput();
            System.out.println();
        }

        // This section of codae will prompt the user to enter the album info.
        System.out.print("Album: ");
        album = getStringInput();
        if (album.length() == 0)
        {
            album = null;
        }

        // This section of the code will prompt the user to enter the track 
        // info.
        System.out.print("Track: ");
        track = getIntInput();
        while (track == 0 || track < -1 || track > 99)
        {
            System.out.println();
            System.out.print("Must be a number between 1 and 99.\n" +
                    "Please re-enter -> ");
            track = getIntInput();
        }

        // This section will ask the user if they are sure they want to add the
        // artist information
        System.out.print("\nAdd (Y/N): ");
        
        // If yes, add the information to the collection
        if (yesNoInput() == 'y')
        {
            AudioFile af = new AudioFile(artist, title, album, track);
            collection.add(af);
        }

    } // end add method

    /**
     * AudoVeiw - The default constructor is used set the class variables to
     * their default values
     */
    public AudioView() 
    {
        pageNumber = 1;
        startingNumber = 1;
        endingNumber = 16;

    } // end default constructor

    /**
     * back - is used for going to the previous page on the main screen.
     * 
     * @param collection is the array of AudioFiles (AudioList)
     */
    public void back(AudioList collection)
    {
        // check to make sure that we are not one the first of second page
        if (pageNumber > 2)
        {
            // Decrement the starting and ending points along with the page #
            startingNumber = (pageNumber - 2) * 16 + 1;
            endingNumber = (pageNumber - 1) * 16;
            pageNumber--;
        }
        else if (pageNumber == 2)
        {
            // if the page # is two just set the values equal to the first page
            startingNumber = 1;
            endingNumber = 16;
            pageNumber--;
        }
        else
        {
            // If it is on page one just make sure the values are properly set
            startingNumber = 1;
            endingNumber = 16;
        }

    } // end previous method

    /**
     * delete - is used to display the screen that will handle the deleting 
     * of the artist/title from the audio collection. In V1 of JMUTunes
     * the delete method will only delete the last song in the array.
     * 
     * @param collection is the array of AudioFiles (AudioList)
     * @throws IOException
     */
    public void delete(AudioList collection) throws IOException
    {
        // heading
        clearScreen();
        System.out.print(center("Delete Audio File") + "\n\n\n");

        // see if there is anything to delete
        if (collection.arrayLength() == 0)
        {
            System.out.print("Nothing to delete.\n" +
                    "Press <ENTER> to continue . . .");
            getStringInput();
        }
        // if there is something to delete
        else
        {
            System.out.print("Delete \"" 
                    + collection.getCollection(collection.arrayLength())
                    + "\"? (Y/N) -> ");

            // delete the File if the User Agrees
            if (yesNoInput() == 'y')
            {
                collection.delete();
            }
        }

    } // end delete method

    /**
     * edit - is used to display the screen that will handle the
     * editing of the artist/title from the audio collection. For the first
     * version of the project, this method will returned a fixed message.
     * 
     * @param collection is the array of AudioFiles (AudioList)
     * @throws IOException 
     */
    public void edit(AudioList collection) throws IOException
    {   
        // header
        clearScreen();
        System.out.print(center("Edit Audio File") + "\n\n\n");

        //Non-Functioning Statement to print
        nonFunctioningStatement();

        // Wait for ENTER to go back to main screen
        getStringInput();

    } // end edit method

    /**
     * getIntInput - this method is used to gather an integer input from the
     * keyboard.
     * 
     * @return the integer input from the keyboard.
     * @throws IOException for the BufferedReader
     */
    public int getIntInput() throws IOException
    {
        // declarations and initialization
        BufferedReader reader = new BufferedReader(new InputStreamReader
                (System.in));
        int intOutput = 0;
        String input = null;
    
        // get input from the keyboard
        input = reader.readLine();
    
        // Check to make sure that the value is not null
        if (input != null)
        {
            // check to make sure that the length is not equal to zero
            if (input.length() == 0)
            {
                intOutput = -1;
            }
            else
            {
                // try to change the string to a char, if it does not work,
                // throw an error and set the value to 0.
                try
                {
                    intOutput = Integer.parseInt(input);
                }
                catch (NumberFormatException e) 
                {
                    intOutput = 0;
                } // end try catch
            } // end nested if
        } // end if
    
        // return the keyboard input or 0 if the input was invalid
        return  intOutput;  
    } // end getIntInput

    /**
     * getMainScreenOption - this method is used to read in and validate the
     * input from the keyboard to be used as an option on the main screen.
     * 
     * @return true if the option if valid
     * @throws IOException
     */
    public char getMainScreenOption() throws IOException
    {
        // declaration and initializations
        String options = "aedslpnbq";
        boolean validate = false;
        String input = "";
        char returnValue = 0;
    
        // continue to loop until the input is valid
        do
        {
            // get the input from the keyboard
            input = getStringInput();
    
            returnValue = stringToChar(input);
    
            // Loop through the options to see if the char = one of them
            for (int i = 0; i < options.length(); i++)
            {
                // if yes, validation is true
                if (options.charAt(i) == returnValue)
                {
                    validate = true;
                }
            } // end for loop
            
            // if it is false print out a statement to an error statement
            if (!validate)
            {
                System.out.println();
                System.out.println("Incorrect entry (" + input + ").");
                System.out.print("Please re-enter -> ");
            }
        }
        while(!validate);
        
        // return the char to the method
        return returnValue;
        
    } // end getMainScreenOption

    /**
     * getStringInput - grabs input from the keyboard, saves that into a
     * string, and returns it to the method call.
     * 
     * @return the String from the keyboard input (String)
     * @throws IOException for the BufferedReader
     */
    public String getStringInput() throws IOException
    {
        // declaration and initializations
        BufferedReader reader = new BufferedReader(new InputStreamReader
                (System.in));
        String input = null;
    
        // grab input from the keyboard
        input = reader.readLine();
    
        // return the input string to the method call
        return  input;
    
    } // end getInput() method

    /**
     * mainScreen - is used to create the main screen of the program. 
     * It will have the title centered at the top and be 25 lines x 80 columns
     * tall. It will show the current audio collection of the user.
     * 
     * @throws IOException 
     */
    public void mainScreen() throws IOException
    {   
        //header
        clearScreen();
        System.out.print(center("JMUTunes Audio Player") + "\n" 
                + center("CS239 (Spring 2013)") + "\n\n\n");

        // use a loop to print out the numbers based on the page number
        for (int i = startingNumber; i <= endingNumber; i++)
        {
            if ( i < 10)
            {
                System.out.print(" " + i + ".\n");
            }
            else
            {
                System.out.print(i + ".\n");
            }
        } // end for loop

        // print out the options at the bottom of the page
        System.out.print("\n(A)dd, (E)dit, (D)elete, (S)earch, play(L)ist," +
                " (P)lay, (N)ext, (B)ack, (Q)uit\n\nChoose Operation -> ");

    } // end main Screen
    
    /**
     * mainScreen - is used to create the main screen of the program. 
     * It will have the title centered at the top and be 25 lines x 80 columns
     * tall. It will show the current audio collection of the user.
     * 
     * @param collection is the array of AudioFiles (AudioList)
     * @throws IOException 
     */
    public void mainScreen(AudioList collection) throws IOException
    {
        //header
        clearScreen();
        System.out.print(center("JMUTunes Audio Player") + "\n" 
                + center("CS239 (Spring 2013)") + "\n\n\n");

        // use a loop to print out the numbers based on the page number
        for (int i = startingNumber; i <= endingNumber; i++)
        {
            // if there is nothing to be printed dont print null
            if (collection.getCollection(i) == null)
            {
                // add a space for the first ten numbers
                if (i < 10)
                {
                    System.out.print(" " + i + ".\n");
                }
                else
                {
                    System.out.print(i + ". \n");                    
                }
            }
            // if there is something to print, then print it.
            else
            {
                // for the first nine lines, put a space in front of the number
                if (i < 10)
                {
                    System.out.print(" " + i + ". "
                            + collection.getCollection(i) + "\n");
                }
                else
                {
                    System.out.print(i + ". "
                            + collection.getCollection(i) + "\n");
                }
            } // end for loop
        }

        // print out the options at the bottom of the page
        System.out.print("\n(A)dd, (E)dit, (D)elete, (S)earch, play(L)ist," +
                " (P)lay, (N)ext, (B)ack, (Q)uit\n\nChoose Operation -> ");

    } // end main Screen

    /**
     * maintain - is used to display the screen that will handle the playing 
     * of an artist/title from the audio collection. This is a non-
     * functioning method for the first version of the program.
     * 
     * @param collection is the array of AudioFiles (AudioList)
     * @throws IOException 
     */
    public void maintain(AudioList collection) throws IOException
    {
        // header
        clearScreen();
        System.out.print(center("Maintain Playlist") + "\n\n\n");

        //Non-Functioning Statement to print
        nonFunctioningStatement();

        // Wait for ENTER to go back to main screen
        getStringInput();


    } // end play method

    /**
     * next -  will be used to advance the mainScreen to the next page
     * of audioFile Objects.
     * 
     * @param collection is the array of AudioFiles (AudioList)
     */
    public void next(AudioList collection)
    {
        // make sure there are is enough info in the array to advance
        if (endingNumber < collection.arrayLength())
        {
            // if on the first page, reset the vales for the second page
            if (pageNumber == 1)
            {
                startingNumber = 17;
                endingNumber = 32;
                pageNumber++;
            }
            // for any page after the first two use a formula to set the 
            // boundaries
            else
            {
                startingNumber = pageNumber * 16 + 1;
                endingNumber = (pageNumber + 1) * 16;
                pageNumber++;
            }
        }
        
    } // end next method

    /**
     * play - will be used to display the screen that will handle the
     * playing of an artist/title from the audio collection. For the first
     * version of the program it will print a fixed statement.
     * 
     * @param collection is the array of AudioFiles (AudioList)
     * @throws IOException 
     */
    public void play(AudioList collection) throws IOException
    {
        
        // header
        clearScreen();
        System.out.print(center("Play Audio File") + "\n\n\n");

        //Non-Functioning Statement to print
        nonFunctioningStatement();

        // Wait for ENTER to go back to main screen
        getStringInput();
        
    } // end play method

    /**
     * playList - will be used to display the screen that will handle 
     * the editing of the artist/title from the audio collection. For the first
     * version of the program it will print a fixed statement.
     * 
     * @param collection is the array of AudioFiles (AudioList)
     * @throws IOException 
     */
    public void playList (AudioList collection) throws IOException
    {
        // header
        clearScreen();
        System.out.print(center("Maintain Playlist") + "\n\n\n");

        // Non-Functioning Statement to print
        nonFunctioningStatement();

        // Wait for ENTER to go back to main screen
        getStringInput();

    } // end playList method

    /**
     * quit - is used to exit out of the program
     */
    public void quit()
    {
        clearScreen();

        System.exit(1);

    } // end quit method

    /**
     * search - will be used to display the screen that will handle 
     * the searching of an artist/title from the audio collection. For the first
     * version of the program it will print a fixed statement.
     * 
     * @param collection is the array of AudioFiles (AudioList)
     * @throws IOException 
     */
    public void search(AudioList collection) throws IOException
    {
        // header
        clearScreen();
        System.out.print(center("Search Audio File") + "\n\n\n");

        //Non-Functioning Statement to print
        nonFunctioningStatement();

        // Wait for ENTER to go back to main screen
        getStringInput();

    } // end search method

    /**
     * stringToChar - is used to take a String one character in length and
     * return a char of that same character.
     * 
     * @param input is the array of AudioFiles (AudioList)
     * @return the character from the first postion in the array
     * @throws IOException
     */
    public char stringToChar(String input) throws IOException
    {
        // Declarations and initializations
        char returnValue = 0;
    
        // make sure that the value is not null or an empty string
        if (input != null && !input.equals("") && input.length() <= 1)
        {
            returnValue = Character.toLowerCase(input.charAt(0));
        }
        
        // return the char or 0
        return returnValue;
    
    } // end getCharValue

    /**
     * center - is private helper method is used to center the text for a screen
     * that is 80 spots wide.
     * 
     * @param centerString
     * @return the centered string
     */
    private String center(String centerString)
    {
        // declarations and initializations
        int length = centerString.length();
        int centerSpaces = 0;
        String toReturn = "";

        // find half of the number of spaces for the space before the text/
        centerSpaces = (80 - length) / 2;

        // add the spaces to center
        for (int i = centerSpaces; i >= 0; i--)
        {
            toReturn = toReturn + " ";
        }
        // add the string to center at the end of the spaces
        toReturn = toReturn + centerString;

        return toReturn;

    } // end center method

    /**
     * clearScreen -  is used to clear the screen that will 
     * handle the adding of the artist to the audio collection.
     */
    private void clearScreen()
    {
        // used to print the 25 black lines
        for (int i = 0; i <= 25; i++)
        {
            System.out.println();
        } // end for loop

    } // end clearScreen Method

    /**
     * nonFunctioningStatement - is a private method that is used to print
     * the non-functioning methods in the first version of the program.
     */
    private void nonFunctioningStatement()
    {

        System.out.print("This function is not currently available.\n" +
                "Press <ENTER> to continue . . .");

    } // end nonFunctioningStatement method

    /**
     * The yesNoInput method is a private helper method that is used to
     * determine a yes of no response from the user.
     * 
     * @return 'y' or 'n'
     * @throws IOException 
     */
    private char yesNoInput() throws IOException
    {
        // declarations and initializations
        boolean validate = false;
        char input = 0;

        // loop until the input is valid
        do
        {
            // get the input
            input = stringToChar(getStringInput());
            
            // if the input is y or no, set validate = true
            if (input == 'y' || input == 'n')
            {
                validate = true;  
            }
            // else print out an error statement
            else
            {
                System.out.println();
                System.out.println("Must be 'Y' or 'N'.");
                System.out.print("Please re-enter -> ");
            }
        }
        while (!validate);

        return input;

    } // end yesNoInputValidation method
} // end AudioVeiw class