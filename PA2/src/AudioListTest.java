import static org.junit.Assert.*;

import org.junit.*;

/**
 * AudioListTest - test the AudioList program
 * 
 * Acknowledgements:  I acknowledge that I have neither given nor 
 *                                  received assistance for this assignment 
 *                                   except as noted below:
 *                                   
 *                                   None
 *                                   
 * Modifications:   RAC (2/11/2013) : changed remove method
 *                          RAC (2/11/2013) : deleted expand test
 *                          RAC (2/21/2013) : added testAddSort
 * 
 * @author Michael Norton, Ryan Carter
 * @version PA1 (1/24/2013)
 */
public class AudioListTest
{
    /**
     * Test method for {@link AudioList#AudioList()} - test the constructor
     */
    @Test
    public void testAudioList()
    {
        AudioList list = new AudioList();
        assertTrue( list.listSize() == 0 );

    } // method testAudioList


    /**
     * Test method for {@link AudioList#add(AudioFile)} - test adding a single
     * AudioFile
     */
    @Test
    public void testAddOne()
    {
        AudioList list = new AudioList();
        // Test 1
        list.add( new AudioFile() );
        assertTrue( list.listSize() == 1 );
        assertNotNull( list.get( 0 ) );
        // Test 2
        list.add(new AudioFile() );
        assertTrue( list.listSize() == 1 );
        assertNotNull( list.get( 0 ) );
        // Test 3
        list.add(null);
        assertTrue( list.listSize() == 1 );
        assertNotNull( list.get( 0 ) );

    } // method testAddOne
    
    /**
    * Test method for {@link AudioList#add(AudioFile)} - test adding a single
    * AudioFile
    */
   @Test
   public void testAddSort()
   {
    AudioList list = new AudioList();
    AudioFile af1 = new AudioFile ("F.U.N", "Some Nights", "Some Nights", 1);
    AudioFile af2 = new AudioFile ("Three Days Grace", "Some Pain", 
            "Greatist Hits", 0);
    AudioFile af3 = new AudioFile ("Zac Brown Band", "Toes", 
            "The Foundation", 1);
    AudioFile af4 = new AudioFile ("Zac Brown Band", "Whatever", 
            "The Foundation", 2);
    AudioFile af5 = new AudioFile ("Zac Brown Band", 
            "Where the Boat Leaves From", "The Foundation", 3);
    AudioFile af6 = new AudioFile ("Zac Brown Band", "Knee Deep", 
            "You Get What You Give", 1);
    AudioFile af7 = new AudioFile ("Zac Brown Band", "Knee Deep", 
            "You Get What You Give", 1);
    AudioFile af8 = new AudioFile ("Zac Brown Band", "No Album",
            "", 0);
    
    // add all of the files in a random order
    list.add( af7 );
    list.add( af1 );
    list.add( af3 );
    list.add( af2 );
    list.add( af6 );
    list.add( af4 );
    list.add( af5 );
    list.add( af8 );
    
    // assert the spot of the songs in the list.
    assertEquals( 0, list.findFile( af1 ));
    assertEquals( 1, list.findFile( af2 ) );
    assertEquals( 2, list.findFile( af3 ) );
    assertEquals( 3, list.findFile( af4 ) );
    assertEquals( 4, list.findFile( af5 ) );
    assertEquals( 5, list.findFile( af6 ) );
    assertEquals( 5, list.findFile( af7 ) );
    assertEquals( 5, list.findFile( af6 ) );
    assertEquals( 6, list.findFile( af8 ) );
    
   } // testAddSort Method


    /**
     * Test method for {@link AudioList#remove(int)} - test the remove
     * functionality by adding 33 AudioFiles & removing 1
     * 
     * RAC** 2/19/2013 - changed to accommodate the set function.
     * 
     */
    @Test
    public void testRemove()
    {
        AudioList list = new AudioList();
        for ( int i = 0; i < 33; i++ )
            list.add( new AudioFile() );
        
        assertTrue( list.listSize() == 1 );
        assertNotNull( list.get( 0 ) );       
        
    } // method testRemove


}
