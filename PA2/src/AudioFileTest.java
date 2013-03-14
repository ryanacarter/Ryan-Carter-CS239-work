import static org.junit.Assert.*;

import org.junit.*;

/**
 * Test class for AudioFile
 * 
 * Acknowledgements:  I acknowledge that I have neither given nor 
 *                                  received assistance for this assignment 
 *                                   except as noted below:
 *                                   
 *                                   None
 * 
 * @author Michael Norton, Ryan Carter
 * @version PA1 (1/24/2013)
 * @version PA2 (2/20/2012)
 * 
 * added the testEqualsGood and Bad method
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
        assertTrue( file.getArtist().length() == 0 );
        assertNotNull( file.getTitle() );
        assertTrue( file.getTitle().length() == 0 );
        assertTrue( file.getTrack() == 0 );

    } // method testAudioFileExplicitBad

    /**
     * Test method for {@link AudioFile#equals()} - test good equals
     */
    public void testEqualsBad()
    {
        AudioFile test1 = new AudioFile("FUN", "Some Nights", "", 4);
        AudioFile test2 = new AudioFile("FUN", "Some Nights", "", 0);
        
        assertFalse(test1.equals(test2));
        
    } // method testEquals
    
    /**
     * Test method for {@link AudioFile#equals()} - test good equals
     */
    public void testEqualsGood()
    {
        AudioFile test1 = new AudioFile("FUN", "Some Nights", "", 0);
        AudioFile test2 = new AudioFile("FUN", "Some Nights", "", 0);
        
        assertTrue(test1.equals(test2));
        
    } // method testEquals
    
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
        file.setAlbum( null );
        
        assertNotNull( file.getAlbum() );
        assertTrue( file.getAlbum().length() == 0 );

    } // method testSetArtistBad



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
        assertTrue( file.getTitle().length() == 0 );

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
