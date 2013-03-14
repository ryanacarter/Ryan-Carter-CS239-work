/**
 * This class sets the audio file object, and sets the string to be returned
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
public class AudioFile 
{
    private String artist;
    private String title;
    private String album;
    private int track;

    /**
     * AudioFile - This is the default constructor for the AudioFile Class.
     * 
     */
    public AudioFile()
    {
        this.artist = null;
        this.title = null;
        this.title = null;
        this.track = -1;

    } // end AudioFile method

    /**
     * AudioFile - is an overloaded constructor for the audioFile object
     * 
     * @param artist of the audio file (String)
     * @param title of the audio file (String)
     * @param album  of the audio file (String)
     * @param track of the audio file (int)
     */
    public AudioFile(String artist, String title, String album, int track)
    {
        // set the album and track to their default value since they are not
        // required input
        this.album = null;
        this.track = 0;

        // make sure that the artist and title are set.
        if (isSet(artist) && isSet(title))
        {
            // if title and artist parameters are set then set their values
            // in the class
            this.artist = artist;
            this.title = title;
            
            //See if album has a set value
            if (isSet(album))
            {
                // if yes, set that value in the class
                this.album = album;
            }
            
            // See if album has a set value
            if (isSet(track))
            {
                // if yes, set that value in the class
                this.track = track;
            }
        } // end if

    } // end overloaded constructor

    /**
     * isSet -  is used to make sure that the param that is being
     * passed to it has a value that is not null or an empty string.
     * 
     * @param toTest is the String to be tested for a value (String)
     * @return true or false if the value is set (boolean)
     */
    public boolean isSet(String toTest)
    {
        // declarations and initializations
        boolean placeHolder = false;
        
        // make sure the value is not null or empty
        if (toTest != null && !toTest.equals(""))
        {
            // if there is a valid string in the param, return true
            placeHolder = true;
        }
        
        // return a boolean to to the method call
        return placeHolder;

    } // end incomingArtistAndTitle Method

    /**
     * This method is used to test the incoming track number to see if it
     * is between the values 0 and 100.
     * 
     * @param toTest is the int to be tested for a value (int)
     * @return true or false if there is an int present (boolean)
     */
    public boolean isSet(int toTest)
    {
        
        // declarations and initializations
        boolean placeHolder = false;
        
        // Make sure the value is between 0 and 100
        if (toTest > 0 && toTest < 100)
        {
            placeHolder = true;
        }
        
        // return the integer
        return placeHolder;

    } // end over loaded isSet Method

    /**
     * getAlbum - Gets the album information
     * 
     * @return the album information (String)
     */
    public String getAlbum()
    {

        return this.album;

    } // end getAlbum method

    /**
     * getArtist - Gets the artist of the object
     * 
     * @return the artist information (String)
     */
    public String getArtist()
    {

        return this.artist;

    } // end getArtist method

    /**
     * getArtist - gets the title information
     * 
     * @return the title information (String)
     */
    public String getTitle()
    {

        return this.title;

    } // end getTitle method

    /**
     * getTrack - gets the track information
     * 
     * @return the track information (int)
     */
    public int getTrack()
    {

        return this.track;

    } // end getTrack method

    /**
     * setAlbum - sets the album information
     * 
     * @param album is the title to be set for the audioFile (String)
     */
    public void setAlbum(String album)
    {
        // make sure that the parameter is set
        if (isSet(album))
        {
            this.album = album;
        }

    } // end setAlbum Method

    /**
     * setArtist - sets the artist information.
     * 
     * @param artist is the title to be set for the audioFile (String)
     */
    public void setArtist(String artist)
    {     
        // make sure that the parameter is set
        if (isSet(artist))
        {
            this.artist = artist;
        }

    } // end setArtist method

    /**
     * setTitle - sets the title information
     * 
     * @param title is the title to be set for the audioFile (String)
     */
    public void setTitle(String title)
    {
        // make sure that the parameter is set
        if (isSet(title))
        {
            this.title = title;
        }

    } // end setTitle method

    /**
     * setTrack - sets the track information
     * 
     * @param track is the title to be set for the audioFile (int)
     */
    public void setTrack(int track)
    {
        // make sure that the parameter is set
        if (isSet(track))
        {
            this.track = track;
        }

    } // end setTrack method

    /**
     * toString - returns the formated String back to the method
     * 
     * @return the formatted string of the object
     */
    public String toString()
    {
        // declaration and initialization
        String toReturn = null;

        // if artist, title, and album are set return this format
        if (isSet(title) && isSet(artist) && isSet(album))
        {
            toReturn = this.artist + ", " + this.title + " (" 
                    + this.album + ")";
        } 
        // if artist and title are set and album is not, then print this format
        else if (isSet(title) && isSet(artist))
        {
            toReturn = this.artist + ", " + this.title;
        }
        
        // return the formated string to the method
        return toReturn;

    } // end toString method
} // end AudioFile Class