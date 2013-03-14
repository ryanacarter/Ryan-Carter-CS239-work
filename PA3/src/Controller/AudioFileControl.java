package Controller;

import View.*;
import Model.*;

/**
 * Read/Write "Songlist.txt" & write "malformed.err"
 * 
 * Modifications: **MLN: PA2 (2/20/2012) - New for PA2
 * 
 * @author Michael Norton
 * @version PA2 (2/12/2013)
 */
public class AudioFileControl 
{
    //----------------------------------------------------------------------
    // Declarations
    //----------------------------------------------------------------------
    private AudioList list;

   /**
    * Default constructor 
    */
    public AudioFileControl( AudioList list )
    {
        this.list = list;
    
    } // default constructor
    
    
    /**
     * Read the file if it exists and populate the Library
     */
    public void readFile() 
    {   
        AudioFileIO audioFile = new AudioFileIO();
        AudioFileIO errFile = new AudioFileIO();
        
        // Declare String to hold a line from the file
        String audioLine = null;
        
        // open the audio file for reading and the error file for writing
        audioFile.open( "Songlist.txt" , AudioFileIO.READING );
        errFile.open( "malformed.err" , AudioFileIO.WRITING );

        // make sure the reader for the file exists
        if ( audioFile.exists() )
        {
            // read first line from the library file
            audioLine = audioFile.readLine();
            
            while ( audioLine != null )
            {                
                AudioFile file = createAudioFile( audioLine );
                
                if ( file != null )
                    list.add( file );
                else
                    errFile.write( audioLine );
                
                audioLine = audioFile.readLine();

            } // end while

            // close the reader & writer (for malformed.err)
            audioFile.close();
            errFile.close();

        } // end if

    } // method readFiles
    

    /**
     * write the library to a data file
     */
    public void writeFile()
    {   
        AudioFileIO audioFile = new AudioFileIO();

        // open the audio data file for writing
        audioFile.open( "Songlist.txt" , AudioFileIO.WRITING );

        // read through the library
        for ( int i = 0; i < list.size(); i++)
        {
            // write the library file
            AudioFile file = list.get( i );
            
            audioFile.write( createAudioFile( file ) );
            
        } // end for
        
        // close the writer
        audioFile.close();
         
    } // method writeFiles  
    
    /************************** private methods ***************************/
    
    /**
     * Create a AudioFile object from the text line.  If the line is malformed
     * return null
     * 
     * @param the line from the file
     * @return an AudioFile if good, null otherwise
     */
    private AudioFile createAudioFile( String line )
    {
        AudioFile audio = new AudioFile();

        String[] elements = line.split( "\\|" );
        
        // skip malformed records
        if ( !isMalformed( elements ) )
        {
            audio.setArtist( elements[0].trim() );
            audio.setTitle( elements[1].trim() );
            audio.setAlbum( elements[2].trim() );
            audio.setTrack( elements[3].trim() );

        } // end if
        
        else
            audio = null;

        
        return audio;
   
    } // method createFile
    
    
    /**
     * Create a output line to write from an AudioFile object
     * 
     * @param the AudioFile to translate
     * @return the String representation of the AudioFile
     */
    private String createAudioFile( AudioFile file )
    {
        String album = " ";
        String artist = "";
        String title = "";
        String track = " ";
        
        artist = file.getArtist();
        title = file.getTitle();
        
        if ( file.getAlbum().trim().length() != 0 )
            album = file.getAlbum();
        if ( file.getTrack() != 0 )
            track = "" + file.getTrack();
        
        return artist + "|" + title + "|" + album + "|" + track + "|";
        
    } // method createAudioFile (overloaded)
    
    
    /**
     * Check the line to see if it is malformed.  
     * 
     * @param an array of elements
     * @return true if the line is malformed, false otherwise
     */
    private boolean isMalformed( String[] elements )
    {
        boolean isMalformed = true;

        if ( elements.length == 4 )
            if ( elements[ 0 ].trim().length() > 0 )
                if ( elements[ 1 ].trim().length() > 0 )
                    isMalformed = false;
        
        return isMalformed;
   
    } // method isMalformed

} // class AudioFileControl
