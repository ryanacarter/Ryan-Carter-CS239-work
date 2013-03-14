package Model;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Test class for AudioFile
 * 
 * Modifications: PA2 (2/14/2013)
 * - added methods to test getSortString()
 * - added methods to test setTrack(String)
 *   
 * @author Michael Norton
 * @version PA2 (2/14/2013), PA1 (1/24/2013)
 *
 */
public class AudioFileTest
{
    /**
     * Test method for {@link AudioFile#AudioFile()} 
     * - test the default constructor
     */
    @Test
    public void testAudioFile()
    {
        AudioFile file = new AudioFile();
        assertTrue( file.getAlbum().length() == 0 );
        assertTrue( file.getArtist().length() == 0 );
        assertTrue( file.getTitle().length() == 0 );
        assertTrue( file.getTrack() == 0 );
        
    } // method testAudioFile



    /**
     * Test method for {@link AudioFile#AudioFile(java.lang.String, 
     *     java.lang.String, java.lang.String, int)}.
     *  -- test the explicit vvalue constructor
     */
    @Test
    public void testAudioFileExplicitGood()
    {
        AudioFile file = new AudioFile("abc", "def", "efg", 9 );
        assertTrue( file.getAlbum().equals( "efg" ) );
        assertTrue( file.getArtist().equals( "abc" ) );
        assertTrue( file.getTitle().equals( "def" ) );
        assertTrue( file.getTrack() == 9 );       

    } //method testAudioFileExplicitGood

    
    /**
     * Test method for {@link AudioFile#AudioFile(java.lang.String, 
     *      java.lang.String, java.lang.String, int)}
     * - Test explicit value constructor with null & invalid data
     */
    @Test
    public void testAudioFileExplicitBad()
    {
        AudioFile file = new AudioFile( null, null, null, 100 );
        assertNotNull( file.getAlbum() );
        assertTrue( file.getAlbum().length() == 0 );
        assertNotNull( file.getArtist() );
        assertTrue( file.getArtist().equals( "No Artist" ) );
        assertNotNull( file.getTitle() );
        assertTrue( file.getTitle().equals( "No Title" ) );
        assertTrue( file.getTrack() == 0 );

    } // method testAudioFileExplicitBad


    /**
     * Test method for {@link AudioFile#getSortString()} - test sort string 
     * for normal AudioFile with album
     */
    @Test
    public void testGetSortStringNormalWithAlbum()
    {
        char delim = (char)0;
        AudioFile file = new AudioFile("abc", "def", "efg", 9 );
        String expected = "abc" + delim + "efg" + delim + "09" + delim +
                "def";
        assertTrue( file.getSortString().equals( expected ) );
        
    } // method testGetSortStringNormalWithAlbum
    

    /**
     * Test method for {@link AudioFile#getSortString()} - test sort string 
     * for normal AudioFile without album
     */
    @Test
    public void testGetSortStringNormalWithoutAlbum()
    {
        char delim = (char)0;
        AudioFile file = new AudioFile("abc", "def", null, 0 );
        String expected = "abc" + delim + "~~~~~~~~~~~~" + delim + "~~" +
                delim + "def";
        System.out.println( file.getSortString() );
        assertTrue( file.getSortString().equals( expected ) );
                
    } // method testGetSortStringNormalWithoutAlbum()
    

    /**
     * Test method for {@link AudioFile#getSortString()} - test sort string 
     * for abnormal AudioFile with album
     */
    @Test
    public void testGetSortStringAbnormalWithAlbum()
    {
        char delim = (char)0;
        AudioFile file = new AudioFile( null, null, "abc", 0 );
        String expected = "no artist" + delim + "abc" + delim + "~~" +
                delim + "no title";
        System.out.println( file.getSortString() );
        assertTrue( file.getSortString().equals( expected ) );
                
    } // method testGetSortStringAbnormalWithAlbum
    

    /**
     * Test method for {@link AudioFile#setAlbum()} - test bad album
     */
    @Test
    public void testSetAlbumBad()
    {
        AudioFile file = new AudioFile();
        file.setAlbum( null );
        
        assertNotNull( file.getAlbum() );
        assertTrue( file.getAlbum().length() == 0 );

    } // method testSetAlbumBad



    /**
     * Test method for {@link AudioFile#setAlbum()} - test good album
     */
    @Test
    public void testSetAlbumGood()
    {
        AudioFile file = new AudioFile();
        file.setAlbum( "Album" );
        
        assertNotNull( file.getAlbum() );
        assertTrue( file.getAlbum().equals( "Album") );

    } // method testSetAlbumGood



    /**
     * Test method for {@link AudioFile#setArtist()} - test bad artist
     */
    @Test
    public void testSetArtistBad()
    {
        AudioFile file = new AudioFile();
        file.setArtist( null );
        
        assertNotNull( file.getArtist() );
        assertTrue( file.getArtist().equals( "No Artist" ) );
    
    } // method testSetTitleBad



    /**
     * Test method for {@link AudioFile#setArtist()} - gest good artist
     */
    @Test
    public void testSetArtistGood()
    {
        AudioFile file = new AudioFile();
        file.setArtist( "Artist" );
        
        assertNotNull( file.getArtist() );
        assertTrue( file.getArtist().equals( "Artist") );

    } // method testSetArtistGood



    /**
     * Test method for {@link AudioFile#setTitle()} - test bad title
     */
    @Test
    public void testSetTitleBad()
    {
        AudioFile file = new AudioFile();
        file.setTitle( null );
        
        assertNotNull( file.getTitle() );
        assertTrue( file.getTitle().equals( "No Title" ) );

    } // method testSetTitleBad



    /**
     * Test method for {@link AudioFile#setTitle()} - set good title
     */
    @Test
    public void testSetTitleGood()
    {
        AudioFile file = new AudioFile();
        file.setArtist( "Title" );
        
        assertNotNull( file.getTitle() );
        assertTrue( file.getArtist().equals( "Title") );

    } // method testSetTitleGood


    /**
     * Test method for {@link AudioFile#setTrack()} - set track too high
     */
    @Test
    public void testSetTrackAfterLast()
    {
        AudioFile file = new AudioFile();
        file.setTrack( 50 );
        file.setTrack( 100 );
        assertTrue( file.getTrack() == 50 );

    } // method testSetTrackAfterLast


    /**
     * Test method for {@link AudioFile#SetTrack()} - test track too low
     */
    @Test
    public void testSetTrackBeforeFirst()
    {
        AudioFile file = new AudioFile();
        file.setTrack( 50 );
        file.setTrack( 0 );
        assertTrue( file.getTrack() == 50 );

    } // method testSetTrackBeforeFirst

    
    /**
     * Test method for {@link AudioFile#SetTrack()} - test set first track
     */
    @Test
    public void testSetTrackFirst()
    {
        AudioFile file = new AudioFile();
        file.setTrack( 20 );
        file.setTrack( 1 );
        assertTrue( file.getTrack() == 1 );

    } // method testSetTrackFirst



    /**
     * Test method for {@link AudioFile#SetTrack()} - test set last track
     */
    @Test
    public void testSetTrackLast()
    {
        AudioFile file = new AudioFile();
        file.setTrack( 20 );
        file.setTrack( 99 );
        assertTrue( file.getTrack() == 99 );

    } // method testSetTrackLast


    /**
     * Test method for {@link AudioFile#SetTrack()} - test set middle track
     */
    @Test
    public void testSetTrackMiddle()
    {
        AudioFile file = new AudioFile();
        file.setTrack( 20 );
        file.setTrack( 50 );
        assertTrue( file.getTrack() == 50 );

    } // method testSetTrackMiddle


    /**
     * Test method for {@link AudioFile#SetTrack(Java.lang.String)} - test set 
     * empty track
     */
    @Test
    public void testSetTrackWithStringEmpty()
    {
        AudioFile file = new AudioFile();
        file.setTrack( "100" );
        file.setTrack( "" );
        assertTrue( file.getTrack() == 0 );
        
    } // method testSetTrackWithStringEmpty
    
    
    /**
     * Test method for {@link AudioFile#SetTrack(Java.lang.String)} - test set 
     * valid track
     */
    @Test
    public void testSetTrackWithStringGood()
    {
        AudioFile file = new AudioFile();
        file.setTrack( "50" );
        file.setTrack( "" );
        assertTrue( file.getTrack() == 50 );
        
    } // method testSetTrackWithStringGood
    
    
    /**
     * Test method for {@link AudioFile#SetTrack(Java.lang.String)} - test set 
     * null track
     */
    @Test
    public void testSetTrackWithStringNull()
    {
        AudioFile file = new AudioFile();
        file.setTrack( "50" );
        file.setTrack( null );
        assertTrue( file.getTrack() == 50 );
        
    } // method testSetTrackWithStringNull
    
    
    /**
     * Test method for {@link java.lang.Object#toString()} - test toString
     * with album
     */
    @Test
    public void testToStringWithAlbum()
    {
        AudioFile file = new AudioFile( "Abc", "Def", "Ghi", 90 );
        assertTrue( file.toString().equals( "Abc, Def (Ghi)") );

    } // method testToStringWithAlbum


    /**
     * Test method for {@link java.lang.Object#toString()} - test toString
     * without album
     */
    @Test
    public void testToStringWithoutAlbum()
    {
        AudioFile file = new AudioFile( "Abc", "Def", "", 0 );
        assertTrue( file.toString().equals( "Abc, Def") );
    
    } // method testToStringWithoutAlbum

} // class AudioFileTest
