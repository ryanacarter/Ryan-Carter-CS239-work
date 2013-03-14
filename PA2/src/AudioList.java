import java.util.ArrayList;

/**
 * The collection of AudioFile object
 * 
 * Acknowledgements:  I acknowledge that I have neither given nor 
 *                                  received assistance for this assignment 
 *                                   except as noted below:
 *                                   
 *                                   None
 *                                   
 * Modifications:   RAC (2/11/2013): Changed from an array to an Array list.
 *                          RAC (2/11/2013): Changed Remove to accommodate the
 *                              ArrayList
 *                          RAC (2/11/2013): Changed size to listSize to
 *                              accommodate the ArrayList.
 *                          RAC (2/11/2013): Deleted expand method.
 *                          RAC (2/19/2013): Changed add to include sort 
 *                              method call.
 *                          RAC (2/19/2013): added sort()
 *                          RAC (2/19/2013): added getArtist()
 *                          RAC (2/19/2013): added getAlbum()
 *                          RAC (2/19/2013): added doesArtistExists()
 *                          RAC (2/19/2013): added artistLocation()
 *                          RAC (2/19/2013): added sortTwoStrings();
 *                          RAC (2/19/2013): added doesAlbumExist();
 *                          RAC (2/19/2013): added albumLocation();
 *                          RAC (2/19/2013): added sortTrack();
 *          
 *          
 *          
 * @author Michael Norton, Ryan Carter
 * @version PA2 (2/21/2013)
 */
public class AudioList
{
    // Declarations
    private ArrayList<AudioFile> list ;


    /**
     * Default constructor
     * 
     * RAC** 2/11/2013 - changed list from an array, to an ArrayList
     */
    public AudioList()
    {               
        list = new ArrayList<AudioFile>();

    } // default constructor


    /************************** public methods ****************************/

    /**
     * Add an audio file to the list
     * 
     * RAC** 2/19/2013 - changed to include a sorting method call when adding
     *                                 a new AudioFile.
     * 
     * @param the audio file the add
     * @return the position added
     */
    public void add( AudioFile newFile )
    {
        int spot;

        // make sure the value is not already in the list.
        if(!list.contains(newFile) && newFile != null)
        {
            spot = sort( newFile );

            // if the spot to add is greater than the list size, add the file
            // to the end of the list
            if (spot > listSize())
                list.add( newFile );

            list.add( spot, newFile );

        } // end if

    } // method add


    /**
     * Show the capacity of the list (for testing purposes only)
     * 
     * @return the capacity of the list
     */
    public int capacity()
    {
        return list.size();

    } // method capacity


    /**
     * Get a particular audio file
     * 
     * @param position
     * @return the specified audio file
     */
    public AudioFile get( int which )
    {
        AudioFile gotFile = null; // return null if position not available

        if ( isInBounds( which ) )
            gotFile = list.get(which);

        return gotFile;

    } // method get
    
    /**
     * findFile - used solely testing purposes
     * 
     * RAC** 2/20/2013 - added to allow testing of sort method.
     * 
     * @param toFind
     * @return
     */
    public int findFile( AudioFile toFind )
    {
        return list.indexOf( toFind );
    } // findFile method

    /**
     * Remove an audio file from the list - in the first version we
     * delete only the last audio file
     * 
     * RAC** 2/11/2013 - changed method to accommodate the ArrayList.
     * 
     * @param position
     * @return
     */
    public boolean remove( int which )
    {
        boolean success = false; // assume this will not work

        if ( isInBounds( which ) ) // we're only removing the last one for now
        {
            list.remove( which );

            success = true;

        } // end if

        return success;

    } // method remove


    /**
     * Returns the number of AudioFiles in the list
     * 
     * RAC** 2/11/2013 - changed to accommodate the ArrayList
     * 
     * @return - int
     */
    public int listSize()
    {
        return list.size();

    } // method getLast


    /************************* private methods ****************************/

    /**
     * albumLocation - gets the first and last occurrence of the album and
     * returns that in an array where the first spot in the array is the
     * first occurrence, and the second spot is the last occurrence
     * 
     * RAC** 2/19/2013 - added AudioList class
     * 
     * @param doesExist - Boolean
     * @param artist - String
     * @param album - String
     * @param location - int[]
     * @return - int[]
     */
    private int[] albumLocation( boolean doesExist, String artist, 
            String album, int[] location)
    {
        int[] forReturn = new int [2];
        int counter = 0;

        // --------------------------------------------------
        // If the album is present in the list of AudioFiles then do 
        // the section of the code below.
        // --------------------------------------------------
        if ( doesExist )
        {
            // find the first occurrence of the album within the artist's
            // section of the list.
            for ( int i = location[0]; i <= location[1]; i++ )
            {
                if( album.equals( getAlbum( i ) ) )
                {
                    // set the first variable
                    forReturn[0] = i;

                    // end the loop
                    i = listSize();

                } // end if

            } // end for loop

            // find the last occurrence of the album within the artist's
            // of the list section
            for(int i = forReturn[0]; i < listSize(); i++ )
            {
                if ( album.equals( getAlbum( i ) ) )
                {
                    counter ++;
                    
                } // end if
                
            } // end loop

            forReturn[1] = counter + forReturn [0] - 1;

        } // is if for if the Album exists

        // --------------------------------------------------
        // If the album is not present in the list of AudioFiles then do 
        // the section of the code below.
        // --------------------------------------------------
        else
        {
            counter = location[ 0 ];

            // if the album being entered is before the first album that
            // the artist contains, place it at the front of the artist's
            // section.
            if ( !sortTwoStrings( getAlbum( location[ 0 ] ), album ) )
            {   
                forReturn[ 0 ] = location[ 0 ];
            }

            // if the album being entered is after the last album that
            // the artist contains, place it at the end of the artist's
            // section.
            else if( sortTwoStrings( getAlbum( location[ 1 ] ), album ) )
            {
                forReturn[0] = location[ 1 ] + 1;
            }

            // if the album being entered is somewhere between the
            // first and last spot of the artist, loop through the section
            // and find the spot where the album goes.
            else
            {
                while( sortTwoStrings( getAlbum( counter ), album ) 
                        && counter <= location[1] )
                {
                    counter++;
                }

                forReturn[0] = counter + location [ 0 ];

            }

        } // end if

        return forReturn;

    } // albumLocation method

    
    /**
     * artistLocation - gets the first and last occurrence of the artist and
     * returns that in an array where the first spot in the array is the
     * first occurrence, and the second spot is the last occurrence.
     * 
     * RAC** 2/19/2013 - added to the AudioList class.
     * 
     * @param doesExist - boolean
     * @param artist - String
     * @return - int[]
     */
    private int[] artistLocation(Boolean doesExist, String artist)
    {
        int[] forReturn = new int [2];
        int counter = 1;

        // --------------------------------------------------
        // If the artist is present in the list of AudioFiles then do 
        // the section of the code below.
        // --------------------------------------------------
        if ( artist != null && doesExist != null )
        {
            
            if( doesExist )
            {
                // find the first spot in which the artist is in the list
                for ( int i = 0; i < listSize(); i++ )
                {
                    if( artist.equals( getArtist( i ) ) )
                    {
                        // set the first variable
                        forReturn[0] = i;

                        // end the loop
                        i = listSize();

                    } // end if

                } // end for loop

                counter = forReturn[0];

                // find the last spot in which the artst is in the list.
                for( int i = forReturn[0]; i < listSize(); i++ )
                {                    
                    if ( artist.equals( getArtist( counter ) ) )
                    {                        
                        counter = counter + 1;
                        
                    } // end if
                    
                } // end loop

                forReturn[1] = counter - 1;

            }   
            
            // --------------------------------------------------
            // If the album is not present in the list of AudioFiles then do 
            // the section of the code below.
            // --------------------------------------------------
            else
            {
                // if the newFile's artist is alphabetically before the artist
                // of the first file in the list, set the return value to 0;
                if ( !sortTwoStrings( getArtist(0), artist ) )
                {
                    forReturn[0] = 0;
                }
                
                // if the newFile's artist is alphabetically after the artist
                // of the last file in the list, set the return value to the
                // end of the list.
                else if( sortTwoStrings( getArtist( listSize() -1 ), artist ) )
                {
                    forReturn[0] = listSize();
                }
                
                // else loop through the list until the spot in which to
                // place the newFile is Found.
                else
                {
                    while( sortTwoStrings( getArtist( counter ), artist ) )
                    {
                        counter++;
                    }

                    forReturn[0] = counter;
                    
                } // end if

            } // end else
        }
        return forReturn;

    } // artistLocation method

    
    /**
     *  doesAlbumExists - looks within an artist and returns true if that
     *  artist has the album already present or not.
     *  
     * RAC** 2/19/2013 - added to the AudioList class.
     * 
     * @param album - string
     * @param location - int[]
     * @returns boolean
     */
    private boolean doesAlbumExist(String album, int[] location)
    {
        boolean forReturn = false;

        for (int i = location[0]; i <= location[1]; i++)
        {
            if (getAlbum( i ).equals( album ) )
            {
                forReturn = true;

            } // end if

        } // end for

        return forReturn;

    } // doesAlbumExists method

    /**
     * doesArtistExists - is used to see if the artist of the incoming file is
     * already in the AudioList.
     * 
     * RAC** 2/19/2013 - added to the AudioList class.
     * 
     * @param artist - String
     * @return boolean
     */
    private boolean doesArtistExist(String artist)
    {
        boolean forReturn = false;

        for (int i = 0; i < listSize(); i++)
        {
            if (getArtist(i).equals( artist ) )
            {
                forReturn = true;
            }

        }

        return forReturn;

    } // doesArtistExist method

    
    /**
     * getArtist - is used to grab the artist from the list as well as set the
     * setting to all lower case for sorting.
     * 
     * RAC** 2/19/2013 - added to the AudioList class.
     * 
     * @param spot int
     * @return String
     */
    private String getArtist( int spot )
    {
        String forReturn = null;

        if ( spot > -1 && spot < listSize() )
        {
            forReturn = list.get( spot ).getArtist().toLowerCase();
        } // getArtist method

        return forReturn;
    } // getArtist method

    
    /**
     * getAlbum - is used to grab the album from the list as well as set the
     * setting to all lower case for sorting.
     * 
     * RAC** 2/19/2013 - added to the AudioList class.
     * 
     * @param spot - int
     * @return - String
     */
    private String getAlbum( int spot )
    {
        String forReturn = null;

        if ( spot > -1 && spot < listSize() )
        {
            forReturn = list.get( spot ).getAlbum().toLowerCase();
        } // getArtist method

        return forReturn;
    } // getArtist method

    
    /**
     * Returns true if the parameter is in bounds (> 0 and <= last)
     * 
     * @param which file?
     * @return true if it is here
     */
    private boolean isInBounds( int which )
    {
        return which > -1 && which < list.size();

    } // method isInBounds

    
    /**
     * sort - is used to find the spot in which to place the song into the
     * list.
     * 
     * RAC** 2/19/2013 - added to the AudioList class.
     * 
     * @param newFile - AudioList
     * @return int
     */
    private int sort(AudioFile newFile)
    {
        int spot = 0;
        int track;
        String artist;
        String album;

        int[] location = new int [2];

        if (newFile != null)
        {
            artist = newFile.getArtist().toLowerCase();
            album = newFile.getAlbum().toLowerCase();
            track = newFile.getTrack();

            if (listSize() != 0)
            {
                // If the artist exists in the page. Find the range in which 
                // the artist exists, the continue to sort by album, then
                // track.
                if ( doesArtistExist( artist ) )
                {
                    // Find the first and last spot of artist in the list.
                    location = artistLocation( true, artist );

                    // if the album exists in the list, you send send to 
                    // albumLocation() with a true parameter to signify that
                    // the artist exists in the list, else move on.
                    if ( doesAlbumExist( album, location ) )
                    {
                        location = albumLocation( true, artist, album,
                                location );
                        spot = sortTrack( track, location );
                    }

                    // check the list to see if there is already an audioFile 
                    // that does not have an album. If so, send them to the
                    // sort method for ints and sort the no album files by 
                    // their ints.
                    else if (doesAlbumExist( "", location))
                    {
                        location = albumLocation( true, artist, "", location);
                        spot = sortTrack( track, location);
                    }

                    // if there is no album, and there are no prior audio file
                    // that do not have a track, place in incoming file at the
                    // end of the artist's section.
                    else if (album.equals( "" ) )
                    {
                        spot = location[1] + 1;
                    }

                    // if the album of the incoming audioFile does not exists
                    // within the artist's section, set the location of the new
                    // album alphabetically within the artists section.
                    else
                    {
                        location = albumLocation(false, artist, album,
                                location);
                        
                        spot = location[ 0 ];
                        
                    }

                } // end If

                // if the artist does not exist in the list, find the spot of the
                // artist relative to the other artists in the list by sorting it
                // Alphabetically.
                else
                {
                    location = artistLocation( false, artist );
                    spot = location[0];

                } // end else

            } // end if
        }

        return spot;

    } // method sort

    
    /**
     * sortTwoStrings - is a method that is used to compare the string in
     * the list to the one being added. If the one that is being added is
     * alphabetically before the one in the list, return false, else
     * return true.
     * 
     * RAC** 2/19/2013 - added to the AudioList class.
     * 
     * @param inList - String in list
     * @param toAdd - String to be added
     * @return - true if the one to be added is after the one in the list.
     */
    private boolean sortTwoStrings (String inList, String toAdd)
    {
        boolean forReturn = false;
        boolean endLoop = false;
        char inListHolder;
        char toAddHolder;
        int spot = 0;

        if (inList != null && toAdd != null)
        {
            inList.toLowerCase();
            toAdd.toLowerCase();
            do
            {
                // if the in List holder is shorter than the one being added,
                // return true.
                try
                {
                    inListHolder = inList.charAt(spot);
                    // if the to add hold is shorted than the in list holder,
                    // return false.
                    try 
                    {
                        toAddHolder = toAdd.charAt(spot);

                        // if the char in the list is greater than the char
                        // being added, end the loop
                        if ( inListHolder > toAddHolder )
                        {
                            forReturn = false;
                            endLoop = true;
                        }
                        else if( inList.equals(toAdd) )
                        {
                            forReturn = true;
                            endLoop = true;
                        }
                        // if the char in the list is the same as the char
                        // in the to add, move to the next spot
                        else if ( inListHolder == toAddHolder )
                        {
                            spot++;
                        }
                        // else if the char in the list is less than the char 
                        // in the to add string, return true
                        else
                        {
                            forReturn = true;
                            endLoop = true;
                        }
                    }
                    catch (StringIndexOutOfBoundsException e)
                    {
                        forReturn = false;
                        endLoop = true;
                    }
                }
                catch (StringIndexOutOfBoundsException e)
                {
                    forReturn = true;
                    endLoop = true;
                }
            }
            while ( !endLoop );

        } // end if

        return forReturn;

    } // method sortTwoStrings

    
    /**
     * sortTrack - takes in the album location and sorts the album by the
     * track number.
     * 
     * RAC** 2/19/2013 - added to the AudioList class.
     * 
     * @param - int
     * @param - int[]
     * @return - int
     */
    private int sortTrack( int track, int[] location )
    {
        int forReturn = 0;

        // if the track is not set, place it at the end of the list of artist's
        // album.
        if (track == 0)
        {
            forReturn = location[ 1 ] + 1;
        }

        // if the track is less than any of the other tracks in the album, 
        // place the new file at the beginning of the album.
        else if ( track < list.get( location[0] ).getTrack() )
        {
            forReturn = location[0];
        }

        // if the track is greater than any of the other tracks in the album,
        // place the new file at the end of the album.
        else if ( track > list.get( location[1] ).getTrack())
        {
            forReturn = location[1] + 1;
        }

        // if the track is between the smallest and largest track number in
        // the album, loop through the album and find the spot in which
        // the new file belongs.
        else
        {
            for ( int j = location[0]; j <= location[1]; j++)
            {
                // if the the track in the list is greater the track track
                // being added, 
                if ( track < list.get( j ).getTrack() && list.get( j ).getTrack() != 0)
                {
                    forReturn = j;

                    j = location[1] + 1;

                }
                else if ( track == list.get( j ).getTrack() )
                {
                    forReturn = j + 1;


                } // end else

            } // end for

        } // end else

        return forReturn;

    } // sortTrack Method

} // class AudioList
