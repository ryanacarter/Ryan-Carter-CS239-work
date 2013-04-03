package Model;

/**
 * The WavFile Class is a class that has been create to extend the 
 * functionality AudioFile.
 * 
 * Acknowledgements:  I acknowledge that I have neither given nor 
 *                                  received assistance for this assignment 
 *                                   except as noted below:
 *                                   
 *                                   none
 * 
 * @author Ryan Carter
 * @version PA3 (3/29/2013)
 */
public class WavFile extends AudioFile
{
 
    private long length;
    
    
    /**
     * Default Constructor
     */
    public WavFile()
    {
        artist = "";
        album = "";
        length = 0;
        pathName = "";
        title = "";
        length = 0;
        
    } // end method
    
    
    /**
     * Explicit Value Constructor
     * 
     * @param name (String)
     * @param pathName  (String)
     * @param size (long)
     */
    public WavFile(String name, String pathName, long size)
    {
        artist = name;
        album = "";
        length = size;
        track = 0;
        super.pathName = pathName;
        title = name;
        
    } // end method
    
    
    /**
     * Explict Value Constructor
     * 
     * @param artist (String)
     * @param title (String)
     * @param album (String)
     * @param track (int)
     * @param fileName (String)
     * @param length (long)
     */
    public WavFile(String artist, String title, String album, int track,
            String pathName, long length)
    {
        super.artist = ( artist == null || artist.length() == 0 ) ? 
                "No Artist" : artist;
        super.title = ( title == null || title.length() == 0 ) ? 
                "No Title" : title;
        super.album = (album == null) ? "" : album;
        super.track = (track < 0 || track > 99) ? 0 : track;
        super.pathName = ( pathName == null || pathName.length() == 0 ) ? 
                "" : pathName;
        this.length = ( length < 0 ) ?  0 : length;
        
    } // end method
    
 
/********************************PUBLIC METHODS*******************************/
    
    
    /**
     * getLength - returns the size of the WAV file.
     */
    public Long getLength()
    {
        return length;
        
    } // end method
    
    /**
     * setLength - sets the length.
     * 
     * @param length (long)
     */
    public void setLength( long length )
    {
        if( length > 0 )
            this.length = length;
        
    } // end method

} // end class