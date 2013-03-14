import static org.junit.Assert.*;

import org.junit.*;
import java.io.*;

/**
 * AudioViewTest - test the methods of the AudioView class
 * 
 * Acknowledgements:  I acknowledge that I have neither given nor 
 *                                  received assistance for this assignment 
 *                                   except as noted below:
 *                                   
 *                                   None
 * 
 * Modifications:   **MLN (pa02) - modified testGetInputEmptyRequired to 
 *                                 reflect fix in AudioView
 *                             RAC 2/21/2013: updated testGetNumberEmpty
 *                             RAC 2/21/2013: updated testTestGetNumberGood
 *                             RAC 2/21/2013: updated testGetNumberOutOfBounds
 *                             RAC 2/21/2013: updated testGetNumberString
 * 
 * @author Michael Norton, Ryan Carter
 * @version PA1 (1/25/2013)
 */
public class AudioViewTest
{

    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private ByteArrayOutputStream err = new ByteArrayOutputStream();


    /**
     * Setup the environment - redirect the stdin & stderr streams so that
     * we can capture it for testing
     * 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        System.setOut( new PrintStream( out ) );
        System.setErr( new PrintStream( err ) );
        
    } // method setup (before tests)


    /**
     * Tear down the environment - reset the stdin & stderr streams
     * 
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {
        System.setOut( null );
        System.setErr( null );

    } // method tearDown (after tests)


    /**
     * Test method for {@link AudioView#centerText(java.lang.String)} -
     * Test empty input
     */
    @Test
    public void testCenterTextEmpty()
    {
        AudioView view = new AudioView();
        view.centerText( "" );

        assertTrue( out.toString().length() == 0 );

    } // method testCenterTextEmpty


    /**
     * Test method for {@link AudioView#centerText(java.lang.String)} -
     * Test good input
     */
    @Test
    public void testCenterTextGood()
    {
        AudioView view = new AudioView();
        view.centerText( "Hello" );

        // one greater due to \nl
        assertTrue( out.toString().length() == 43 );
 
    } // method testCenterTextGood


    /**
     * Test method for {@link AudioView#centerText(java.lang.String)} -
     * Test long input (over 80 characters)
     */
    @Test
    public void testCenterTextLong()
    {
        AudioView view = new AudioView();
        view.centerText( getLongText() );

        // one greater due to \nl
        assertTrue( out.toString().length() == 81 );

    } // method testCenterTextLong


    /**
     * Test method for {@link AudioView#centerText(java.lang.String)} -
     * Test null input
     */
    @Test
    public void testCenterTextNull()
    {
        AudioView view = new AudioView();
        view.centerText( null );

        assertTrue( out.toString().length() == 0 );

    } // method testCenterTextNull


    /**
     * Test method for {@link AudioView#clearScreen()} - test clear screen
     */
    @Test
    public void testClearScreen()
    {
        AudioView view = new AudioView();
        view.clearScreen();

        assertTrue( out.toString().length() == 25 );
        for (int i = 0; i < 25; i++)
            assertTrue( out.toString().substring( i , i + 1 ).equals( "\n" ) );

    } // method testClearScreen


    /**
     * Test method for {@link AudioView#display(java.lang.String)} - test
     * display with an empty message
     */
    @Test
    public void testDisplayEmpty()
    {
        AudioView view = new AudioView();
        view.display( "" );

        assertTrue( out.toString().length() == 0 );

    } // method testDisplayEmpty


    /**
     * Test method for {@link AudioView#display(java.lang.String)} - test the
     * display method with a string < 80 characters
     */
    @Test
    public void testDisplayGood()
    {
        AudioView view = new AudioView();
        view.display( "Hello" );

        assertTrue( out.toString().equals( "Hello" )
                && out.toString().length() == 5 );

    } // method testDisplayGood


    /**
     * Test method for {@link AudioView#display(java.lang.String)} - test the
     * display method with a string > 80 characters
     */
    @Test
    public void testDisplayLong()
    {
        AudioView view = new AudioView();
        view.display( getLongText() );

        assertTrue( out.toString().length() == 80 );

    } // method testDisplayLong


    /**
     * Test method for {@link AudioView#display(java.lang.String)} - test
     * display with null input
     */
    @Test
    public void testDisplayNull()
    {
        AudioView view = new AudioView();
        view.display( null );

        assertTrue( out.toString().length() == 0 );

    } // testDisplayNull


    /**
     * Test method for {@link AudioView#displayError(java.lang.String)} - test
     * displayError with an empty message
     */
    @Test
    public void testDisplayErrorEmpty()
    {
        AudioView view = new AudioView();
        view.displayError( "" );

        assertTrue( out.toString().equals( "\n\nPlease re-enter -> " ) );

    } // method testDisplayErrorEmpty


    /**
     * Test method for {@link AudioView#displayError(java.lang.String)} - test
     * displayError with a message < 80 characters
     */
    @Test
    public void testDisplayErrorGood()
    {
        AudioView view = new AudioView();
        view.displayError( "Hello" );

        assertTrue( out.toString().equals( "\nHello\nPlease re-enter -> " ) );

    } // method testDisplayErrorGood


    /**
     * Test method for {@link AudioView#displayError(java.lang.String)} - test
     * displayError with a message that is > 80 characters
     */
    @Test
    public void testDisplayErrorLong()
    {
        AudioView view = new AudioView();
        view.displayError( getLongText() );

        assertTrue( out.toString().length() == 101 );

    } // method testDisplayErrorLong


    /**
     * Test method for {@link AudioView#displayError(java.lang.String)} - test
     * displayError with a null message
     */
    @Test
    public void testDisplayErrorNull()
    {
        AudioView view = new AudioView();
        view.displayError( null );

        assertTrue( out.toString().equals( "\nPlease re-enter -> " ) );

    } // method testDisplayErrorNull


    /**
     * Test method for {@link AudioView#displayLine()} - test displayLine 
     * with no parameter
     */
    @Test
    public void testDisplayLine()
    {
        AudioView view = new AudioView();
        view.displayLine();

        assertTrue( out.toString().length() == 1
                && out.toString().equals( "\n" ) );

    } // method testDisplayLine


    /**
     * Test method for {@link AudioView#displayLine(java.lang.String)} - test
     * displayLine with an empty string
     */
    @Test
    public void testDisplayLineStringEmpty()
    {
        AudioView view = new AudioView();
        view.displayLine( "" );

        assertTrue( out.toString().equals( "\n" ) );

    } // method testDisplayLineEmpty


    /**
     * Test method for {@link AudioView#displayLine(java.lang.String)} - test
     * displayLine with a string that is < 80 characters
     */
    @Test
    public void testDisplayLineStringGood()
    {
        AudioView view = new AudioView();
        view.displayLine( "Hello" );

        assertTrue( out.toString().equals( "Hello\n" ) );

    } // method testDisplayLineGood


    /**
     * Test method for {@link AudioView#displayLine(java.lang.String)} - test
     * displayLine with a string that is > 80 characters
     */
    @Test
    public void testDisplayLineLong()
    {
        AudioView view = new AudioView();
        view.displayLine( getLongText() );

        assertTrue( out.toString().length() == 81 );

    } // testDisplayLineLong


    /**
     * Test method for {@link AudioView#displayLine(java.lang.String)} - test
     * displayLine with null input
     */
    @Test
    public void testDisplayLineNull()
    {
        AudioView view = new AudioView();
        view.displayLine( null );

        assertTrue( out.toString().length() == 0 );

    } // testDisplayLineNull


    /**
     * Test method for {@link AudioView#getInput(boolean, java.lang.String)} - 
     * test getInput with the required flag not set & empty input
     */
    @Test
    public void testGetInputEmptyOptional()
    {
        String input = "\n";
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );

        AudioView view = new AudioView();
        String output = null;

        output = view.getInput( false , "" );

        assertTrue( out.toString().length() == 0 );
        assertTrue( output.length() == 0 );
 
    } // testGetInputEmptyOptional


    /**
     * Test method for {@link AudioView#getInput(boolean, java.lang.String)} -
     * test getInput with the required flag set & message & empty input
     * 
     * Modification: **MLN (pa02) - added NL to end of test string to 
     *                              reflect change in AudioView.getInput()
     */
    @Test
    public void testGetInputEmptyRequired()
    {
        AudioView view;
        
        String input = "\nOk\n";
        String output = null;
        
        // redirect input to method
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );

        view = new AudioView();

        output = view.getInput( true , "Oops" );

        assertTrue( out.toString().equals( "\nOops\nPlease re-enter -> \n" ) );
        assertTrue( output.equals( "Ok" ) );

    } // method testGetInputEmptyRequired


    /**
     * Test method for {@link AudioView#getNumber(boolean, int, int)} - test
     * getNumber with empty input
     * 
     * RAC** 2/18/2013 - updated for PA2.
     */
    @Test
    public void testGetNumberEmpty()
    {
        AudioView view;
        
        String input = "" + " \n12\n";
        		
        int output = 0;
        
        // redirect input to method
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );

        view = new AudioView();

        output = view.getNumber(true, "Must be a number between 1 and 99.", 1, 99 );

        assertTrue( out.toString().equals(
                "\nMust be a number between 1 and 99.\nPlease re-enter -> "  ) );
        assertTrue( output == 12 );

    } // method testGetNumberEmpty


    /**
     * Test method for {@link AudioView#getNumber(int, int)} - test 
     * getNumber with valid input
     * 
     * RAC** 2/21/2013 - updated for PA2.
     * 
     */
    @Test
    public void testGetNumberGood()
    {
        AudioView view;
        
        String input = "12\n";
        int output = 0;
        
        // redirect input to method
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );

        view = new AudioView();

        output = view.getNumber(true, "error message", 1, 99 );

        assertTrue( output == 12 );

    } // method testGetNumberGood


    /**
     * Test method for {@link AudioView#getNumber(int, int)} - test
     * getNumber with a number out of bounds
     * 
     * RAC** 2/18/2013 - updated for PA2.
     */
    @Test
    public void testGetNumberOutOfBounds()
    {
        AudioView view;
        
        String input = "101\n12\n";
                
        int output = 0;
        
        // redirect input to method
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );

        view = new AudioView();

        output = view.getNumber( true, "\nMust be a number between 1 and 99"
                , 1, 99 );

        assertFalse( out.toString().equals( 
                "\nMust be a number between 1 and 99\nPlease re-enter -> " ) );
        assertTrue( output == 12 );

    } // method testGetNumberOutOfBounds


    /**
     * Test method for {@link AudioView#getNumber(int, int)} - test the 
     * getNumber method when a string is entered
     * 
     * RAC** 2/18/2013 - updated for PA2.
     */
    @Test
    public void testGetNumberString()
    {
        AudioView view;
        
        String input = "Hello\n12\n";
                
        int output = 0;
        
        // redirect input to method
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );

        view = new AudioView();

        output = view.getNumber( true,
                "\nMust be a number between 1 and 99", 1, 99 );

        assertFalse( out.toString().equals( 
                "\nMust be a number between 1 and 99" ) );
        assertTrue( output == 12 );

    } // method testGetNumberString


    /**
     * Test method for {@link AudioView#pause(java.lang.String)} - test the 
     * pause function
     */
    @Test
    public void testPause()
    {
        AudioView view;
        
        String input = "\n";
        
        // redirect input to method
        System.setIn( new ByteArrayInputStream( input.getBytes() ) );

        view = new AudioView();
        view.pause( "Hello" );
        
        assertTrue( out.toString().equals( 
                "\n\nHello\nPress <ENTER> to continue . . ." ) );
       
    } // method testPause

    /**
     * Test method for {@link AudioView#showUnavailable(String)} - test
     * the showUnavailable method
     */
    @Test
    public void testShowUnavailable()
    {
        // This test is redundant since the method only calls other methods
        // that have already been tested:
        //
        //    clearScreen()
        //    centerText( title )
        //    pause( "This function is not currently available" );
        //
        // I put it here to show that I did not forget it.  I left it
        // out intentionally.
        
    } // method testShowUnavailable

    /************************ private methods *****************************/

    /**
     * Return a long text for testing
     * 
     * @return a long text
     */
    private String getLongText()
    {
        return "abcdefghijklmnopqrstuvwxyz" 
                + "abcdefghijklmnopqrstuvwxyz"
                + "abcdefghijklmnopqrstuvwxyz"
                + "abcdefghijklmnopqrstuvwxyz";

    } // method getLongText

} // class AudioViewTest
