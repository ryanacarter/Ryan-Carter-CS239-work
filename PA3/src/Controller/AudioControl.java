package Controller;

import View.*;
import Model.*;

/**
 * Controls the action of the JMUTunes application. This is the only class
 * that has references to the model and view classes
 * 
 * Modifications: **MLN PA2 (2/10/2013) 
 *   - modified delete method to allow deletion of user selected file
 *   - enabled edit method to edit user selected file
 *   - created overloaded getAudioInfo(AudioFile) to handle edit
 * 
 * @author Michael Norton
 * @version PA2 (2/12/2013), PA1 (1/19/2013)
 */
public class AudioControl
{
    //----------------------------------------------------------------------
    // Declarations
    //----------------------------------------------------------------------
    private int page;

    private AudioList list;
    private AudioView view;

    // public static finals
    public static char ADD = 'a';
    public static char BACK = 'b';
    public static char DELETE = 'd';
    public static char EDIT = 'e';
    public static char LIST = 'l';
    public static char NEXT = 'n';
    public static char PLAY = 'p';
    public static char SEARCH = 's';
    public static char QUIT = 'q';

    /**
     * Default constructor
     */
    public AudioControl()
    {
        page = 0;

        list = new AudioList();
        view = new AudioView();

    } // default constructor


    /************************** public methods ****************************/

    /**
     * This method has the outermost level of program control. The program
     * will display the screen, ask the user to choose an action, then
     * respond to the action designated by the user. The program will
     * continue until the user chooses to quit.
     * 
     * **MLN (PA2) - Modified for PA2 to read/write files
     */
    public void run() 
    {
        char choice = (char) 0; // default to nonsensical value
        AudioFileControl fileController = new AudioFileControl( list );
        
        fileController.readFile();

        do
        {
            showScreen();
            choice = getChoice();
            respondToChoice( choice );
            
        } while ( choice != QUIT );
        
        fileController.writeFile();

    } // method run

    /************************* private methods ****************************/

    /**
     * Add an audio file to the list
     */
    private void addAudioFile()
    {
        AudioFile song = getAudioInfo();

        if ( isConfirmed( "Add (Y/N ): " ) )
            list.add( song );

        // move to the next page if adding beyond the current page
        if ( list.size() > page * 16 + 16 )
            page++;

    } // method addAudioFile


    /**
     * Delete an audio file (PA1 - delete last audio file)
     * 
     * **MLN (PA2) - allow deletion of the file chosen by the user
     */
    private void deleteAudioFile()
    {
        if ( list.size() > 0 )
        {
            int fileNumber = 0;
            AudioFile deleteFile;

            view.display( "Delete file number -> " );
            fileNumber = view.getNumber( true, 1 , list.size() );

            deleteFile = list.get( fileNumber - 1 );

            view.clearScreen();
            view.centerText( "Delete Audio File" );
            view.displayLine();
            view.displayLine();

            if ( isConfirmed( "Delete \"" + deleteFile.toString()
                    + "\"? (Y/N) -> " ) )
               list.remove( list.size() - 1 );

            // move to the prior page if removing the last item on a page > 1
            if ( list.size() > 0 && list.size() < page * 16 + 1 )
                page--;

        } // end if

        else
            view.pause( "Nothing to delete." );

    } // method deleteAudioFile


    /**
     * Edit an audio file (not functional in PA1)
     * 
     * **MLN (PA2) - Edit file chosen by the user
     */
    private void editAudioFile()
    {
        if ( list.size() > 0 )
        {
            int fileNumber = 0;
            AudioFile original;
            AudioFile edited;

            view.display( "Edit file number -> " );
            fileNumber = view.getNumber( true, 1 , list.size() );

            original = list.get( fileNumber - 1 );

            if ( isConfirmed( "Edit \""
                    + original.toString()
                    + "\"? (Y/N) -> " ) )
            {
                edited = getAudioInfo( original );

                if ( isConfirmed( "Confim Edit (Y/N): " ) )
                {
                    list.remove( original );
                    list.add( edited );

                } // end if

            }
            // move to the next page if adding beyond the current page
            if ( list.size() > page * 16 + 16 )
                page++;
        }
        else
            view.pause( "Nothing to edit." );

    } // method editAudioFile


    /**
     * Get the Audio file information from the user and create an AudioFile
     * object to add to the list
     * 
     * @return an AudioFile object
     */
    private AudioFile getAudioInfo()
    {
        int track = 0;

        String album = "";
        String artist = "";
        String title = "";

        // print header
        view.clearScreen();
        view.centerText( "Add Audio File" );
        view.displayLine();
        view.displayLine();

        view.display( "Artist: " );
        artist = view.getInput( true , "Required entry." );

        view.display( "Title:  " );
        title = view.getInput( true , "Required entry." );

        view.display( "Album:  " );
        album = view.getInput( false , "" );

        view.display( "Track:  " );
        track = view.getNumber( false, 1 , 99 );

        return new AudioFile( artist , title , album , track );

    } // method getAudioInfo


    /**
     * Get the Audio file information from an existing AudioFile for
     * the user to edit
     * 
     * **MLN (PA2) - added for the edit function
     * 
     * @return an AudioFile object
     */
    private AudioFile getAudioInfo( AudioFile original )
    {
        int orgTrack = original.getTrack();
        int track = 0;

        String album = "";
        String artist = "";
        String orgAlbum = original.getAlbum();
        String orgArtist = original.getArtist();
        String orgTitle = original.getTitle();
        String title = "";
        
        // print header
        view.clearScreen();
        view.centerText( "Edit Audio File" );
        view.displayLine();
        view.displayLine();

        view.display( "Artist" + 
                ( orgArtist.length() > 0 ? " (" + orgArtist + "): " : ": " ) );
        artist = view.getInput( false , "" );

        view.display( "Title" +
                ( orgTitle.length() > 0 ? " (" + orgTitle + "): " : ": " ) );
        title = view.getInput( false , "" );

        view.display( "Album" + 
                ( orgAlbum.length() > 0 ? " (" + orgAlbum + "): " : ": " ) );
        album = view.getInput( false , "" );

        view.display( "Track" +
                ( orgTrack != 0 ? " (" + orgTrack + "): " : ": " ) );
        track = view.getNumber( false, 1 , 99 );

        artist = artist.length() > 0 ? artist : orgArtist;
        title = title.length() > 0 ? title : orgTitle;
        album = album.length() > 0 ? album : orgAlbum;
        track = track != 0 ? track : orgTrack;        
        
        return new AudioFile( artist , title , album , track );

    } // method getAudioInfo (overloaded for edit)


    /**
     * Get a valid menu choice from the user
     * 
     * @return the validated choice
     */
    private char getChoice()
    {
        boolean isValid = false;

        String input;

        do
        {
            input = view.getInput( false , "" );

            isValid = isValidChoice( input , "aedslpnbq" );

            if ( !isValid )
                view.displayError( "Incorrect entry (" + input + ")" );
            

        } while ( !isValid );

        return input.toLowerCase().charAt( 0 );

    } // method getChoice


    /**
     * Handle playlists (not functional in PA1)
     */
    private void handlePlayLists()
    {
        view.showUnavailable( "Maintain Playlists" );

    } // method handlePlayLists


    /**
     * Confirms (Y/N) an operation
     * 
     * @return true if the user enters 'Y' or 'y'
     */
    private boolean isConfirmed( String message )
    {
        boolean confirmed = false;
        boolean isValid = false;

        String input = "";

        view.displayLine();
        
        // make sure there is a message to print before continuing
        if ( message != null && message.length() > 0 )
        {
            view.display( message );

            do
            {
                input = view.getInput( true , "Required entry." );

                isValid = isValidChoice( input , "yn" );

                if ( !isValid )
                    view.displayError( "Must be 'Y' or 'N'." );

            } while ( !isValid );

            confirmed = input.toLowerCase().charAt( 0 ) == 'y';
        }
        
        return confirmed;

    } // method isConfirmed


    /**
     * Determines whether the input value is among the valid choices
     * 
     * @param input
     * @return true if the input value is among the valid choices
     */
    private boolean isValidChoice(String input, String validChoices)
    {
        boolean isValid = false;

        // make sure there are valid choices to check & that the input is
        // a single character 
        if ( validChoices != null && validChoices.length() > 0 )
            if ( input.length() == 1 )
                if ( validChoices.indexOf( 
                        input.toLowerCase().charAt( 0 ) ) > -1 )
                    isValid = true;

        return isValid;

    } // method isValidChoice


    /**
     * Print the next page of audio files if there is one
     */
    private void nextPage()
    {
        if ( list.size() > page * 16 + 16 )
            page++;

    } // method nextPage


    /**
     * Play an audio file (not functional in PA1)
     */
    private void playAudioFile()
    {
        view.showUnavailable( "Play Audio File" );

    } // method playAudioFile


    /**
     * Print the prior page of audio files if there is one
     */
    private void priorPage()
    {
        if ( page > 0 )
            page--;
        
    } // method nextPage


    /**
     * Perform the operation selected by the user from the menu
     * 
     * @param choice
     */
    private void respondToChoice( char choice )
    {
        switch (choice)
        {
            case 'a':
                addAudioFile();
                break;
            case 'e':
                editAudioFile();
                break;
            case 'd':
                deleteAudioFile();
                break;
            case 's':
                searchAudioFiles();
                break;
            case 'l':
                handlePlayLists();
                break;
            case 'p':
                playAudioFile();
                break;
            case 'b':
                priorPage();
                break;
            case 'n':
                nextPage();
                break;
            case 'q':
                view.clearScreen();
                break;
            default:
                view.pause( "Oops! Error!!" );
                showScreen();

        } // end switch

    } // method respondToChoice


    /**
     * Search for audio files (not functional in PA1)
     */
    private void searchAudioFiles()
    {
        view.showUnavailable( "Search Audio Files" );

    } // method searchAudioFiles


    /**
     * Show the body of the page
     */
    private void showBody()
    {
        for (int i = page * 16 + 1; i < page * 16 + 17; i++)
        {
            view.displayLine( spaceFillNumber( i ) + ". "
                    + list.getShortTitle( i - 1 ) );

        } // end for

    } // method showBody


    /**
     * Show the bottom of the primary screen
     */
    private void showFoot()
    {
        view.displayLine();
        view.displayLine( "(A)dd. (E)dit, (D)elete, (S)earch, play(L)ist, "
                + "(P)lay, (N)ext, (B)ack, (Q)uit" );
        view.displayLine();
        view.display( "Choose Operation -> " );

    } // method showFoot


    /**
     * Show the top of the primary screen
     */
    private void showHead()
    {
        view.centerText( "JMUTunes Audio Player" );
        view.centerText( "CS239 (Spring 2013)" );
        view.displayLine();

    } // method showHead


    /**
     * Show the primary screen
     */
    private void showScreen()
    {
        view.clearScreen();
        showHead();
        showBody();
        showFoot();

    } // method showScreen


    /**
     * Space-fill the number if necessary
     * 
     * @param the number to space fill
     * @return the number as a space-filled String
     */
    private String spaceFillNumber( int num )
    {
        String numAsString = "" + num;
        String lastNumAsString = "" + ( ( page * 16 ) + 16 );

        if ( lastNumAsString.length() > numAsString.length() )
            numAsString = " " + numAsString;

        return numAsString;

    } // method spaceFillNumber

} // class AudioControl