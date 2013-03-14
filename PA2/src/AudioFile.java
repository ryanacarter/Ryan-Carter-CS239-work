/**
 * A single AudioFile  
 * 
 * Acknowledgements:  I acknowledge that I have neither given nor 
 *                                  received assistance for this assignment 
 *                                   except as noted below:
 *                                   
 *                                   none
 *                                   
 *                                   
 * Modifications: RAC (2/19/2013): equals added to test if two objects are
 *                              the same.
 *          
 * @author Michael Norton, Ryan Carter
 * @version PA2 (2/21/2013)
 * 
 */
public class AudioFile
{
    private int track;

    private String album;
    private String artist;
    private String title;


    /**
     * Default constructor
     */
    public AudioFile()
    {
        album = "";
        artist = "";
        title = "";
        track = 0;

    } // default constructor


    /**
     * Explicit value constructor
     */
    public AudioFile( String artist, String title, String album, int track )
    {
        
        this.artist = artist == null ? "": artist;
        this.title = title == null ? "": title;
        this.album = album == null ? "": album;
        this.track = track < 0 || track > 99 ? 0: track;

    } // explicit value constructor

    /**
     * equals - is used to see if two AudioFile Objects are equal to each other
     * 
     * @param newFile
     * @return
     */
    public boolean equals(Object newFile)
    {
        boolean forReturn = false;
        AudioFile audio = (AudioFile)newFile;
        
        // if all the fields are equal, then the newFile equals the audioFile.
        if (this.artist.equals(audio.getArtist()))
            if (this.album.equals(audio.getAlbum()))
                if (this.title.equals(audio.getTitle()))
                    if (this.track == audio.getTrack())
                        forReturn = true;
        
        return forReturn;
        
    } // method Equals
    
    /**
     * Return the album information
     * 
     * @return the album
     */
    public String getAlbum()
    {
        return album;

    } // method getAlbum


    /**
     * Return the artist information
     * 
     * @return the artist
     */
    public String getArtist()
    {
        return artist;
    
    } // method getArtist


    /**
     * Return the title information
     * 
     * @return the title
     */
    public String getTitle()
    {
        return title;
    
    } // method getTitle


    /**
     * Return the track number
     * 
     * @return the track number
     */
    public int getTrack()
    {
        return track;
    
    } // method getTrack


    /**
     * Set the album info
     * 
     * @param album
     */
    public void setAlbum( String album )
    {
        if ( album != null )
            this.album = album;

    
    } // method setAlbum


    /**
     * Set the artist information
     * 
     * @param artist
     */
    public void setArtist( String artist )
    {
        if ( artist != null )
             this.artist = artist;

    } // method setArtist


    /**
     * Set the title information
     * 
     * @param title
     */
    public void setTitle( String title )
    {
        if ( title != null )
            this.title = title;

    } // method setTitle


    /**
     * Set the track number if valid. 
     * 
     * @param trackNum
     */
    public void setTrack( int track )
    {
        if ( track > 0 && track < 100 )
            this.track = track;
 
    } // method setTrack
    

    /**
     * Return a string representation for an audio file
     * 
     * @return the string representation
     */
    public String toString()
    {
        String albumInfo = album.length() == 0 ? "" : " (" + album + ")";
        String shortTitle = artist + ", " + title + albumInfo;
        
        return shortTitle;
    
    } // method toString

} // class AudioFile
