import junit.framework.TestCase;

/**
 * This junit class hold all of the test cases for the AudioFile class.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor 
 *                                 received assistance for this assignment 
 *                                 except as noted below:
 *                                 
 *                                 None
 * @author Ryan Carter
 * @version 01/31/2013
 */
public class AudioFileTest extends TestCase 
{

    // Holds all of the objects for testing the AudioFile Class
    private AudioFile af1 = new AudioFile("Elvis", "Hound", "Greatist", 1);
    private AudioFile af2 = new AudioFile();
    private AudioFile af3 = new AudioFile("Three Days", "Pain", "", 0);
    private AudioFile af4 = new AudioFile("", "Pain", "", 0);
    private AudioFile af5 = new AudioFile("Three Days", "Pain", "Pain", 100);
    private AudioFile af6 = new AudioFile("To Set", "To Set", "To Set", 1);

    /**
     * testIsSet - tests the incomingArtistAnd Title method in the
     * audioFile class.
     */
    public void testIsSet()
    {

        assertTrue(af1.isSet("lkajsdf"));
        assertFalse(af1.isSet(""));
        assertFalse(af1.isSet(null));
        assertTrue(af1.isSet(1));
        assertFalse(af1.isSet(0));
        assertFalse(af1.isSet(100));

    } // end isSet test

    /**
     * testGetAlbum - tests the code the resides in the getAlbum method in the
     * AudioFile Class.
     */
    public void testGetAlbum() 
    {
        // declarations and initializations
        String test1 = "Greatist";
        String test2 = "Pain";

        // Test 1
        assertEquals(test1, af1.getAlbum());

        // Test2
        assertNull(af2.getAlbum());

        // Test 3
        assertNull(af3.getAlbum());

        // Test 4
        assertNull(af4.getAlbum());

        //Test 5
        assertEquals(test2, af5.getAlbum());

    } // end getAlbum test

    /**
     * testGetArtist - tests the code the resides in the getArtist method in the
     * AudioFile Class.
     */
    public void testGetArtist() 
    {
        String test1 = "Elvis";
        String test2 = "Three Days";

        // Test 1
        assertEquals(test1, af1.getArtist());

        // Test2
        assertNull(af2.getArtist());

        // Test 3
        assertEquals(test2, af3.getArtist());

        // Test 4
        assertNull(af4.getArtist());

        //Test 5
        assertEquals(test2, af5.getArtist());

    } // end getArtist Test

    /**
     * testGetTitle - tests the code the resides in the getTitle method in the
     * AudioFile Class.
     */
    public void testGetTitle() 
    {
        String test1 = "Hound";
        String test3 = "Pain";

        // Test 1
        assertEquals(test1, af1.getTitle());

        // Test 2
        assertNull(af2.getTitle());

        // Test3
        assertEquals(test3, af3.getTitle());

        // Test 4
        assertNull(af4.getTitle());

    } // end getTitle Test

    /**
     * testGetTrack - tests the code the resides in the getTrack method in the
     * AudioFile Class.
     */
    public void testGetTrack() 
    {
        // Test 1
        assertEquals(1, af1.getTrack());

        // Test 2
        assertEquals(-1, af2.getTrack());

        // Test 3
        assertEquals(0, af3.getTrack());

        // Test 4
        assertEquals(0, af4.getTrack());

        // Test 5
        assertEquals(0, af5.getTrack());

    } // end getTrack Test

    /**
     * testSetAlbum - tests the code the resides in the setAlbum method in the
     * AudioFile Class.
     */
    public void testSetAlbum() 
    {
        // Declaration of the new album to be set
        String one = "string";

        // Test 1
        af6.setAlbum("string");
        assertEquals(one, af6.getAlbum());

        // Test 2
        af6.setAlbum("");
        assertEquals(one, af6.getAlbum());

        // Test 3
        af6.setAlbum(null);
        assertEquals(one, af6.getAlbum());

    } // end testSetAlbum test

    /**
     * testSetArtist - tests the code the resides in the setArtist method in the
     * AudioFile Class.
     */
    public void testSetArtist() 
    {
        // Declaration of the new album to be set
        String one = "string";

        // Test 1
        af6.setArtist("string");
        assertEquals(one, af6.getArtist());

        // Test 2
        af6.setArtist("");
        assertEquals(one, af6.getArtist());

        // Test 3
        af6.setArtist(null);
        assertEquals(one, af6.getArtist());

    } // end setArtist test

    /**
     * testSetTitle - tests the code the resides in the setTitle method in the
     * AudioFile Class.
     */
    public void testSetTitle() 
    {
        // Declaration of the new album to be set
        String one = "string";

        // Test 1
        af6.setTitle("string");
        assertEquals(one, af6.getTitle());

        // Test 2
        af6.setTitle("");
        assertEquals(one, af6.getTitle());

        // Test 3
        af6.setTitle(null);
        assertEquals(one, af6.getTitle());

    } // end testSetTitle method

    /**
     * testSetTrack - tests the code the resides in the setTrack method in the
     * AudioFile Class.
     */
    public void testSetTrack() {

        // Test 1
        af6.setTrack(76);
        assertEquals(76, af6.getTrack());

        // Test 2
        af6.setTrack(0);
        assertEquals(76, af6.getTrack());

        // Test 3
        af6.setTrack(100);
        assertEquals(76, af6.getTrack());

    } // end setTrack test

    /**
     * testToString - tests the code the resides in the toString method in the
     * AudioFile Class.
     */
    public void testToString() {

        assertEquals("Elvis, Hound (Greatist)", af1.toString());
        assertEquals(null, af2.toString());
        assertEquals("Three Days, Pain", af3.toString());
        assertEquals(null, af4.toString());
        assertEquals("Three Days, Pain (Pain)", af5.toString());

    } //  end toString Method

} // end class
