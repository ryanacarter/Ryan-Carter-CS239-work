import java.text.*;

/**
 * A single AudioFile
 * 
 * Modifications: PA2 (2/10/2013) 
 *  - added overloaded version of setTrack(String ) 
 *  - added getSortString() 
 *  - override equals method to facilitate contains method in AudioList
 *  - modified setArtist() & setTitle() to set default value if null or empty
 *    parameter
 * 
 * @author Michael Norton
 * @version PA2 (2/12/2013), PA1 (1/18/2013)
 */
public class AudioFile
{
    //----------------------------------------------------------------------
    // Declarations
    //----------------------------------------------------------------------
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
    public AudioFile(String artist, String title, String album, int track)
    {
        this.artist = ( artist == null || artist.length() == 0 ) ? 
                "No Artist" : artist;
        this.title = ( title == null || title.length() == 0 ) ? 
                "No Title" : title;
        this.album = album == null ? "" : album;
        this.track = track < 0 || track > 99 ? 0 : track;

    } // explicit value constructor


    /**
     * Returns true if all attributes in this AudioFile are the same as
     * those in the other AudioFile.  This method overrides the built-in
     * equals() method.
     * 
     * **MLN (PA2) - added to facilitate the AudioList contains method
     * 
     * @param the other AudioFile
     * @return true if both AudioFiles contain the same values for all
     *         attributes
     */
    public boolean equals( Object other )
    {
        boolean isEqual = false;

        if ( other != null )
            if ( this == other )
                isEqual = true; // if the same object
            else
            {
                AudioFile audio = (AudioFile) other;

                if ( artist.equals( audio.getArtist() )
                        && title.equals( audio.getTitle() )
                        && album.equals( audio.getAlbum() )
                        && track == audio.getTrack() )
                    isEqual = true; // if different, but contents are the same
                
            } // end else

        return isEqual;

    } // method equals


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
     * Return the string for sorting AudioFile. Sorted by Artist, Album, &
     * Track. If album is missing, then list at the end of the artist list
     * arranged by track (should not happen, but just in case) and title.
     * Note: used (char)0 to separate elements to force proper order 
     * (thanks to Rachel Allen for pointing out this potentiality!!)
     * 
     * **MLN (PA2) - Added for PA2
     * 
     * @return the sort string
     */
    public String getSortString()
    {
        char delimiter = (char)0;

        DecimalFormat fmt = new DecimalFormat( "00" );
        String sortString = "";

        sortString = artist + delimiter;

        sortString += ( album.trim().length() > 0 ? album : "~~~~~~~~~~~~" ) +
                delimiter;
        sortString += ( track > 0 && track < 100 ? fmt.format( track ) : 
            "~~" ) + delimiter;
        sortString += title;

        return sortString.toLowerCase();

    } // method getSortString


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
     * **MLN (PA2) - added 2nd condition to test that a value exists
     * 
     * @param artist
     */
    public void setArtist( String artist )
    {
        if ( artist != null && artist.trim().length() > 0 )
            this.artist = artist;
        else
            this.artist = "No Artist";

    } // method setArtist


    /**
     * Set the title informatino
     * 
     * **MLN (PA2) - added 2nd condition to test that a value exists & force
     *               a value if none exists
     * 
     * @param title
     */
    public void setTitle( String title )
    {
        if ( title != null && title.trim().length() > 0 )
            this.title = title;
        else
            this.title = "No Title";

    } // method setTitle


    /**
     * Set the track number if valid.
     * 
     * @param trackNum
     */
    public void setTrack(int track)
    {
        if ( track > 0 && track < 100 )
            this.track = track;

    } // method setTrack


    /**
     * Set the track number if valid (overloaded version) - convert String to
     * int & then send to the int version of the method which will handle 
     * out of bounds issues.
     * 
     * **MLN (1/12/2013) - added for PA2
     * 
     * @param the track as a String - leave as is if null or incorrect
     */
    public void setTrack( String track )
    {
        int trackNum = 0;
        
        if ( track != null )
            if ( track.trim().length() > 0 )
                try
                {
                    trackNum = Integer.parseInt( track );
                    setTrack( trackNum );

                } // end try
                catch ( NumberFormatException e ) { /* do nothing */ }
                
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