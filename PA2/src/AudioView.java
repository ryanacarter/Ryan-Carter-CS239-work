import java.io.*;

/**
 * AudioView - handles the interaction between the control part of the 
 * program and the user.  All screen and keyboard I/O is here.
 * 
 * Acknowledgements:  I acknowledge that I have neither given nor 
 *                                  received assistance for this assignment 
 *                                   except as noted below:
 *                                   
 *                                   None
 *                                   
 * Modifications:   **MLN - (pa02) fixed newline after retry in getInput()
 *                         RAC 2/21/2013: changed param of getNumber to allow
 *                              a non-required return.
 * 
 * @author Michael Norton, Ryan Carter
 * @version PA1 (1/19/2013)
 *
 */
public class AudioView
{
    // declarations
    private BufferedReader reader; // the reader object
    private BufferedReader fileReader;
    private BufferedWriter fileWriter; // the writer object
    private BufferedWriter malWriter; // used to write the malformed file
   
    /**
     * Default value constructor
     * 
     */
    public AudioView()
    {

        reader = new BufferedReader( new InputStreamReader( System.in ) );

        try
        {
            fileReader = new BufferedReader ( 
                    new InputStreamReader( new FileInputStream( "Songlist.txt" )));
            
        }
        catch ( IOException e ) { /*Do nothing*/}

    } // default constructor


    /**************************** public Methods *************************/

    /**
     * Print the text in the center of an 80-column screen
     * 
     * @param incoming text
     */
    public void centerText(String text)
    {
        String spaces = "";

        if ( text != null && text.length() > 0 )
        {
            // truncate if over 80 characters
            text = truncate( text );

            for (int i = 0; i < (80 - text.length()) / 2; i++)
                spaces += " ";

            displayLine( spaces + text );

        } // end if

    } // method centerText


    /**
     * Clear the screen.
     */
    public void clearScreen()
    {
        for (int i = 0; i < 25; i++)
            displayLine();

    } // method clearScreen

    /**
     * closeFileReader - is used to close the reader that reads in the file
     * information
     */
    public void closeFileReader()
    {
        try
        {
            fileReader.close();
        }
        catch ( IOException e ) { /* Do Nothing*/ }
        catch ( NullPointerException e ) { /* do nothing */ }
    } // close FileReader
    
    /**
     * closeFileWriter - is used to close the file writer of the program.
     */
    public void closeFileWriter()
    {
        try
        {
            fileWriter.close();
        }
        catch ( IOException e ) { /* do nothing */ }
        catch (NullPointerException e){ /*do nothing*/}
    }

    /**
     * display - display the text to the screen without end-of-line. 
     * Truncates if necessary
     * 
     * @param the text to display
     */
    public void display(String text)
    {
        if ( text != null )
            System.out.print( truncate( text ) );

    } // method display


    /**
     * displayError - display an error message and request re-entry of input
     * 
     * @param the error message
     */
    public void displayError(String errorMessage)
    {
        displayLine();
        
        if ( errorMessage != null )
            displayLine( truncate( errorMessage ) );
        
        display( "Please re-enter -> " );        

    } // method displayError

    
    /**
     * displayLine - display a blank line to the screen
     * 
     * @param String
     */
    public void displayLine()
    {
        System.out.println();

    } // method displayLine


    /**
     * displayLine - display the content of the parameter to the screen with
     * NL (overloaded version). Truncates to 80 characters if necessary.
     * 
     * @param the String to display
     */
    public void displayLine(String text)
    {
        if ( text != null )
            System.out.println( truncate( text ) );

    } // method displayLine


    /**
     * Get input from the user
     * 
     * Modification: **MLN - fixed newline after retry (pa2)
     * 
     * @param true if the entry is required, false otherwise
     * @param the error message to print
     * 
     * @return the String entered by the user
     */
    public String getInput(boolean required, String errorMessage)
    {
        boolean isValid = true;
        String input = null;

        do
        {
            try
            {
                input = reader.readLine();

                // MLN - fixed to add NL after re-entry
                if ( !isValid )
                    displayLine();
                
                if ( required )
                {
                    if ( input.length() == 0 )
                    {
                        isValid = false;
                        displayError( errorMessage );

                    } // end if
                    else
                        isValid = true;

                } // end if

            } // end try

            catch ( IOException e ) { /* do nothing */ }

        } while ( !isValid );

        return input;

    } // method getInput


    /**
     * Get a number from the user between the lower and upper bounds
     * (inclusive)
     * 
     * RAC** 2/18/2013 - added boolean parameter.
     * 
     * @param required
     * @param lower
     * @param upper
     * @return a valid number
     */
    public int getNumber(boolean required, String errorMessage, int lower,
            int upper)
    {
        boolean isValid = false;

        int number = 0; // default value
        String str;

        do
        {
            str = getInput( required , errorMessage );

            if ( isValidInt( str , lower , upper ) )
            {
                if ( str.length() > 0 )
                    number = Integer.parseInt( str );
               
                isValid = true;
                
            } // end if

            else
            {
                displayError( errorMessage );
                isValid = false;

            } // end else

        } while ( !isValid );

        return number;

    } // method getNumber
       
    /**
     * Reads a line from the input file
     * 
     * @return a line from the input file
     * @throws IOException
     */
    public String readInput() throws IOException
    {
        return fileReader.readLine();
    
    } // method readLine

    /**
     * Optionally print a message and wait for the user to press enter
     * 
     * @param the message to print
     */
    public void pause( String msg )
    {
        displayLine();
        displayLine();

        if ( msg.length() > 0 )
            displayLine( msg );

        display( "Press <ENTER> to continue . . ." );

        getInput( false , "" ); // don't need to capture this input

    } // method pause



    /**
     * Show that the choice is not currently available
     */
    public void showUnavailable(String title)
    {
        clearScreen();
        centerText( title );
        pause( "This function is not currently available" );

    } // method showUnavailable
    
    /**
     * writeFile - is used to write each line to the file.
     * 
     * @param artist
     * @param title
     * @param album
     * @param track
     */
    public void writeFile (String artist, String title, String album, int track)
    {
        if (fileWriter == null)
            initializeWriter();
        
        try
        {
            fileWriter.write( artist +"|" + title + "|" + album + "|"
                    + track + "|\n" );
        }
        catch ( IOException e ) { /* Do Nothing */ }
        
    } // writeFile Method
    
    /**
     * writeMalformedFile - is used to write all of the bad incoming lines
     * of information from the Songlist.txt and write them to a corrupted
     * file called malformed.err.
     * 
     * @param line
     * @throws IOException
     */
    public void writeMalformedFile (String line) throws IOException
    {
        malWriter = new BufferedWriter( 
                new FileWriter( "malformed.err", true ) );

        try
        {
            malWriter.write(line);
        }
        catch ( IOException e ) { /* Do Nothing */ }
        
    } // writeMalformedFile method


    /************************* private methods ****************************/

    /**
     * This method ensures that the input String will evaluate to an int
     * between the lower & upper bounds (inclusive)
     * 
     * @param input string
     * @return true if the value is between 1 and 99 (inclusive)
     */
    private boolean isValidInt( String input, int lower, int upper )
    {
        boolean isValid = true;
        int test;

        if ( input.length() > 0 ) // don't bother if nothing here
            try
            {
                test = Integer.parseInt( input );

                if ( test < lower || test > upper )
                    throw new NumberFormatException();

            } // end try

            catch ( NumberFormatException e )
            {
                isValid = false;

            } // end catch

        return isValid;

    } // method isValidInt
    
    /**
     * initializeWriter - is used to initialize the write for writing the good
     * file when the program is exited
     */
    private void initializeWriter()
    {
        try
        {
            fileWriter = new BufferedWriter( new FileWriter ("Songlist.txt") );
        }
        catch ( IOException e ) { /* do nothing */ }
   
    } // initializeWriter method
    


    /**
     * Truncate a line if it is over 80 characters in length
     * 
     * @param the string to truncate
     * @return the truncated string
     */
    private String truncate(String string)
    {
        if ( string.length() > 80 )
            string = string.substring( 0 , 80 );

        return string;

    } // method truncate

} // class AudioView
