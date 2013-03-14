import junit.framework.TestCase;

/**
 *  The AudioListTest is a class filled with junit test cases that are used to
 *  test the method in the AudioList class.
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
public class AudioListTest extends TestCase {

    // declarations and initializations
    private AudioList myCollection = new AudioList();
    private AudioFile af1 = new AudioFile("Elvis", "Hound Dog", 
            "Greatist Hits", 1);
    private AudioFile af2 = new AudioFile();
    private AudioFile af3 = new AudioFile("Three Days Grace", "Pain", "", 0);
    private AudioFile af4 = new AudioFile("", "Pain", "", 0);
    private AudioFile af5 = new AudioFile("Three Days Grace", "Pain",
            "Pain", 100);
    private AudioFile af6 = new AudioFile("To Set", "To Set", "To Set", -5);
    private AudioFile af7 = new AudioFile("To Set", "To Set", "To Set", 50);
    private AudioFile af8 = new AudioFile("To Set", "To Set", "To Set", 200);
    private AudioFile af9 = new AudioFile("To Set", "To Set", "To Set", 3);
    private AudioFile af10 = new AudioFile("To Set", "To Set", "To Set", 77);
    private AudioFile af11 = new AudioFile("To Set", "To Set", "To Set", 43);
    private AudioFile af12 = new AudioFile("To Set", "To Set", "To Set", 8);
    private AudioFile af13 = new AudioFile("To Set", "To Set", "To Set", 4);
    private AudioFile af14 = new AudioFile("To Set", "To Set", "To Set", 1);
    private AudioFile af15 = new AudioFile("To Set", "To Set", "To Set", 99);
    private AudioFile af16 = new AudioFile("To Set", "To Set", "To Set", 56);
    private AudioFile af17 = new AudioFile("To Set", "To Set", "To Set", 34);

    /**
     * testAdd - tests the the add method in the Audio
     */
    public void testAdd()
    {
        // Test One
        myCollection.add(af1);
        assertEquals(myCollection.getCollection(1), af1);

        // Test Two
        myCollection.add(af2);
        assertEquals(myCollection.getCollection(2), af2);

        // Test Three
        myCollection.add(af3);
        assertEquals(myCollection.getCollection(3), af3);

        // Test Four
        myCollection.add(af4);
        assertEquals(myCollection.getCollection(4), af4);

        // Test Five
        myCollection.add(af5);
        assertEquals(myCollection.getCollection(5), af5);

        // Test Six
        myCollection.add(af6);
        assertEquals(myCollection.getCollection(6), af6);

        // Test Seven
        myCollection.add(af7);
        assertEquals(myCollection.getCollection(7), af7);

        // Test Eight
        myCollection.add(af8);
        assertEquals(myCollection.getCollection(8), af8);

        // Test Nine
        myCollection.add(af9);
        assertEquals(myCollection.getCollection(9), af9);

        // Test Ten
        myCollection.add(af10);
        assertEquals(myCollection.getCollection(10), af10);

        // Test Eleven
        myCollection.add(af11);
        assertEquals(myCollection.getCollection(11), af11);

        // Test Twelve
        myCollection.add(af12);
        assertEquals(myCollection.getCollection(12), af12);

        // Test Thirteen
        myCollection.add(af13);
        assertEquals(myCollection.getCollection(13), af13);

        // Test Fourteen
        myCollection.add(af14);
        assertEquals(myCollection.getCollection(14), af14);

        // Test Fifteen
        myCollection.add(af15);
        assertEquals(myCollection.getCollection(15), af15);

        // Test Sixteen
        myCollection.add(af16);
        assertEquals(myCollection.getCollection(16), af16);

        // Test Seventeen
        myCollection.add(af17);
        assertEquals(myCollection.getCollection(17), af17);

    } // end test add

    /**
     * testGetCollection - this test method will be used to test the 
     * getCollection method in the AudioList class.
     */
    public void testGetCollection() 
    {
        // Test One
        myCollection.add(af1);
        assertEquals(myCollection.getCollection(1), af1);

        // Test Two
        myCollection.add(af2);
        assertEquals(myCollection.getCollection(2), af2);

        // Test Three
        myCollection.add(af3);
        assertEquals(myCollection.getCollection(3), af3);

        // Test Four
        myCollection.add(af4);
        assertEquals(myCollection.getCollection(4), af4);

        // Test Five
        myCollection.add(af5);
        assertEquals(myCollection.getCollection(5), af5);

        // Test Six
        myCollection.add(af6);
        assertEquals(af6, myCollection.getCollection(6));

    } // end testGetCollection method

    /**
     * testDelete - is used to test the delete method in the AudioList
     * class
     */
    public void testDelete() 
    {
        myCollection.delete();
        assertEquals(myCollection.getCollection(6), null);

    } // end testDelete method

    /**
     * testDoubleArraySize - test the double array size method in the
     * AudioList class
     */
    public void testDoubleArraySize() 
    {
        // declarations and initializations
        AudioList aL2 = new AudioList();

        // Test 1
        aL2.doubleArraySize();

        // Test 2 - double the array size 10 times
        for (int i = 0; i < 10; i++)
        {
            aL2.doubleArraySize();
        }
        assertEquals(32768, aL2.numberOfSlotsInArray());
    } // end DoubleArraySize

    /**
     * testIsFull - is a method that is used to test the isFull class in the
     * audioList class.
     */
    public void testIsFull() 
    {
        // declarations and initializations
        AudioList isFull = new AudioList();
        AudioFile af = new AudioFile("test", "test", "test", 1);

        // Test 1 - make 16 new files and test
        for (int i = 0; i < 16; i++)
        {
            isFull.add(af);
        }
        assertTrue(isFull.isFull());

        // Test 2 - make 16 more new files and test
        isFull.doubleArraySize();
        for (int i = 0; i < 16; i++)
        {
            isFull.add(af);
        }
        assertTrue(isFull.isFull());

    } // end testIsFull

    /**
     * testLength - is used to test the arrayLength method of the of the
     * AudioList class.
     */
    public void testArrayLength() 
    {
        // declarations and initializations
        AudioList arrayLength = new AudioList();
        AudioFile af = new AudioFile("test", "test", "test", 1);

        // Test 1 - make 16 new files and test
        for (int i = 0; i < 16; i++)
        {
            arrayLength.add(af);
        }
        assertEquals(16 , arrayLength.arrayLength());

        // Test 2 - add 32 more files and re-test
        for (int i = 0; i < 32; i++)
        {
            arrayLength.add(af);
        }
        assertEquals(48, arrayLength.arrayLength());

    } // end testArrayLength method

} // end testAudioListMethod
