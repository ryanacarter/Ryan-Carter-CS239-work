import java.io.IOException;

/**
 * Controls the action of the JMUTunes application. This is the only class
 * that has references to the model and view classes
 * 
 * Acknowledgements:  I acknowledge that I have neither given nor 
 *                                  received assistance for this assignment 
 *                                   except as noted below:
 *                                   
 *                                   
 * Modifications:   RAC (2/13/2013): edit - now lets the user choose the file
 *                                  edit as well as editing the file itself 
 *                          RAC (2/13/2012): getEditedAudioFile - was added
 *                          RAC (4/15/2013): deleteAudioFile - now lets the user choose the
 *                                  file from the list to delete.
 *                          RAC (4/18/2013): loadList - was added
 *                          RAC (4/18/2013): loadFile - was added
 *                          RAC (4/18/2013): run - changed to load the list when
 *                                  first run and write when closed.  
 *          
 *          
 * @author Michael Norton, Ryan Carter
 * @version PA2 (2/21/2013)
 * 
 * 
 * 
 * edited and changed
 * delete
 * edit
 * getEditedAudioFile
 */
public class AudioControl
{
    // instance variable declarations
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
     * RAC** 2/18/2013 - changed to load file of start and write when done.
     */
    public void run()
    {
        try
        {
            loadList();
            
        }
        catch ( IOException e ) { /* do nothing */ }
        
        char choice = (char)0; // default to nonsensical value

        do
        {
            showScreen();
            choice = getChoice();
            respondToChoice( choice );
            
        } while ( choice != QUIT );
        
        saveFile();
        view.closeFileWriter();
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
        if ( list.listSize() > page * 16 + 16 )
            page++;

    } // method addAudioFile


    /**
     * Delete an audio file (PA1 - delete last audio file)
     * 
     * RAC** 2/15/2013 - added in the ability to delete any audioFile
     * 
     */
    private void deleteAudioFile()
    {
        int spot = -1;
        String errorMessage = "Must be a number between 1 and " + list.listSize();
        
        view.clearScreen();
        
        if (list.listSize() > 0)
        {
            view.display("Delete file number -> ");
            spot = view.getNumber(true, errorMessage, 0, list.listSize()) - 1;
            
        } // end if
        
        view.clearScreen();

        view.centerText( "Delete Audio File" );

        if ( list.listSize() > 0 )
        {
            view.displayLine();
            view.displayLine();

            if ( isConfirmed( "Delete \"" +
                list.get( spot ).toString() + "\"? (Y/N) -> " ) )
                list.remove( spot );

            // move to the prior page if removing the last item on a page > 1
            if ( list.listSize() > 0 && list.listSize() < page * 16 + 1 )
                page--;

        } // end if

        else
            view.pause( "Nothing to delete." );

    } // method deleteAudioFile


    /**
     * editAudioFile - is used to edit files in the List.
     * 
     * RAC** 2/13/2013 - added in the ability to edit an audioFile
     */
    private void editAudioFile()
    {   
        int spot = -1;
        String errorMessage = "Must be a number between 1 and " 
                + list.listSize();
        
        AudioFile newFile = new AudioFile();
        AudioFile oldFile = new AudioFile();
        
        view.clearScreen();
        
        if (list.listSize() > 0)
        {
            view.display("Edit File Number -> ");
            spot = view.getNumber(true, errorMessage, 1, list.listSize()) - 1;
        
            oldFile = list.get( spot );
            
           newFile = getEditedAudioFile( oldFile );
           
           if ( isConfirmed( "Confirm Edit (Y/N) -> " ) )
           {
               list.remove( spot );
               list.add(newFile);
           }
            
        } // end if
        
        else
            view.pause( "Nothing to Edit." );

    } // method editAudioFile


    /**
     * Get the Audio file information from the user and create an AudioFile
     * object
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
        track = view.getNumber(false, "Must be a number between 1 and 99.", 1 , 99 );

        return new AudioFile( artist , title , album , track );

    } // method getAudioInfo


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
     * getEditedAudioFile - is used to get the new information of the edited
     * AudioFile, the returned file contains the new values as well as the
     * old ones if they were not changed in the editing process.
     * 
     * RAC** 2/15/2013 - Added to the AudioControl Class
     * 
     * @param oldFile - contains the values in the list
     * @return the new AudioFile to replace the old one.
     */
    private AudioFile getEditedAudioFile(AudioFile oldFile)
    {
        int track = 0;

        String album = "";
        String artist = "";
        String title = "";

        // print header
        view.clearScreen();
        view.centerText( "Edit Audio File" );
        view.displayLine();
        view.displayLine();

        view.display( "Artist (" + oldFile.getArtist() + "): " );
        artist = view.getInput( false , "" );
        if ( artist.equals("") )
            artist = oldFile.getArtist();    

        view.display( "Title (" + oldFile.getTitle() + "): " );
        title = view.getInput( false , "" );
        if ( title.equals("") )
            title = oldFile.getTitle();

        view.display( "Album (" + oldFile.getAlbum() + "): " );
        album = view.getInput( false , "" );
        if ( album.equals("") )
            album = oldFile.getAlbum();

        view.display( "track (" + oldFile.getTrack() + "): " );
        track = view.getNumber(false, "Must be a number between 1 and 99." + 
                "\nPlease re-enter -> ", 1 , 99 );
        if ( track == 0)
            track = oldFile.getTrack();

        return new AudioFile( artist , title , album , track );

    }


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
            
        } // end if
        
        return confirmed;

    } // method isConfirmed
    
    /**
     * isMalformed - is used to test if the line in the Songlist.txt file is
     * corrupted or missing data.
     * 
     * @param artist
     * @param title
     * @param album
     * @param track
     * @return true is it is malformed, false if it is not.
     */
    private boolean isMalformed( String artist, String title, String album,
            int track)
    {
        boolean forReturn = false;
        
        if ( artist == null || artist.equals( "" ) || title == null || title.equals( "" ) )
            forReturn = true;
        
        return forReturn;
    }


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
     * loadFile - is used to read in the audioFiles
     * 
     * RAC** 2/18/2013 - added to AudioControl
     * 
     * @return an AudioFile with the information from the file.
     * @throws IOException
     */
    private AudioFile loadFile() throws IOException
    {
        // used to hold a line of the Songlist.txt file
        String line = "";
        String [] splitedLine = new String [4];
        AudioFile forReturn = null;
        boolean malformed = false;
        
        //used to hold the variables to be submitted to the list.
        String artist = null;
        String title = null;
        String album = null;
        int track = -1;
        
        try
        {
            do
            {
                line = view.readInput();

                splitedLine = line.split( "\\|");

                if (splitedLine.length != 4)
                {
                    view.writeMalformedFile(line);
                    malformed = true;
                }

                else
                {
                    artist = splitedLine[0];
                    title = splitedLine[1];
                    album = splitedLine[2];

                    try
                    {
                        track = Integer.parseInt(splitedLine[3]);
                    }
                    catch (NumberFormatException e) { /* do Nothing */ }

                    if (!isMalformed(artist, title, album, track))
                    {
                        forReturn = new AudioFile( artist, title, album,
                                track );
                        malformed = false;
                    }        
                    else
                    {
                        view.writeMalformedFile(line);
                        malformed = true;
                    }

                }
            }
            while (malformed);
        }
        catch (NullPointerException e) { /* do nothing */ }

        return forReturn;
        
    } // loadFile method

    /**
     * loadList - is used to load in the files and populate the list at the
     * beginning of the program.
     * 
     * RAC** 2/18/2013 - added to AudioControl
     * 
     * @throws IOException
     */
    private void loadList() throws IOException
    {
        
        AudioFile toAdd;
        
        while ((toAdd = loadFile()) != null)
        {
            list.add( toAdd );
        }
        
        view.closeFileReader();
        
    } // loadList method

    /**
     * Print the next page of audio files if there is one
     */
    private void nextPage()
    {
        if ( list.listSize() > page * 16 + 16 )
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
     * saveFile - is used to save the list to a file when the program is closed.
     */
    private void saveFile()
    {
        AudioFile toAdd = new AudioFile();
        
        for (int i = 0; i < list.listSize(); i++)
        {
            toAdd = list.get(i);
            
            view.writeFile( toAdd.getArtist(), toAdd.getTitle(), 
                    toAdd.getAlbum(), toAdd.getTrack() );
        }
    }

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
            view.display( spaceFillNumber( i ) + ". " );
            
            if ( i <= list.listSize() )
                view.displayLine( list.get( i - 1 ).toString() );
            else
                view.displayLine();

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