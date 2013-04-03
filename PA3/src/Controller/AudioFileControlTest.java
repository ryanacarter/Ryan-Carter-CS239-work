package Controller;

import static org.junit.Assert.*;

import View.*;
import Model.*;

import java.io.*;
import org.junit.*;

/** 
 * Test the methods of the AudioFileControl class
 * 
 * Acknowledgements:  I acknowledge that I have neither given nor 
 *                                  received assistance for this assignment 
 *                                   except as noted below:
 *                                   
 *                                   none
 * 
 * Modifications: **MLN: PA2 (2/14/2013) - New for PA2
 * 
 * @author Michael Norton
 * @version PA2 (2/15/2013)
 */
public class AudioFileControlTest
{
    private AudioList list = new AudioList();

    /**
     * Test method for {@link AudioFileControl#readFile()}.
     * 
     * **RAC(PA3) - allows for the adding of WAV and MP3's
     */
    @Test
    public void testReadFile()
    {
       
        AudioFileControl fileController;
        AudioFileIO file;
        AudioFileIO malformed;

        // create the Songlist.txt file with good and malformed records
        String line1 = "1|abc|def|he||as|gr|345|";
        String line2 = "1||def|ghi|1";
        String line3 = "2|ghi|jkl|mno|0|u|0|";
        String line4 = "abc|def||";                
        
        file = new AudioFileIO( "Songlist.txt", AudioFileIO.WRITING );        

        file.write( line1 );
        file.write( line2 );
        file.write( line3 );
        file.write( line4 );
        
        file.close();

        // read the file & populate the list
        fileController = new AudioFileControl( list );
        fileController.readFile();

        malformed = new AudioFileIO( "malformed.err", AudioFileIO.READING );
        
        // check what got written to the AudioList
        assertTrue( list.size() == 2 );
        assertTrue( list.get( 0 ).equals( new MP3File( 
                "abc","def","he", 0, "as", "gr", 345 ) ) );
        assertTrue( list.get( 1 ).equals( new WavFile(
                "ghi","jkl","mno", 0, "u", 0 ) ) );
        
        // check what got written to the malformed.err file
        assertTrue( malformed.exists() );
        assertTrue( malformed.readLine().equals( line2 ) );
        assertTrue( malformed.readLine().equals( line4 ) );
        
    } // method testReadFile
   

    /**
     * Test method for {@link AudioFileControl#writeFile()}.
     * 
     * **RAC(PA3) - allows for the writting of WAV and MP3's
     */
    @Test
    public void testWriteFile()
    {
        testReadFile(); // setup the AudioList

        File file;
        AudioFileControl control = new AudioFileControl( list );
        AudioFileIO test;
        
        // write the AudioList back to the file
        control.writeFile();
        file = new File( "Songlist.txt" );
        test = new AudioFileIO( "Songlist.txt", AudioFileIO.READING );
        
        // check that the file exists and is populated correctly
        assertTrue( file.exists() );
        assertTrue( test.readLine().equals( "1|abc|def|he||as|gr|345|" ) );
        assertTrue( test.readLine().equals( "2|ghi|jkl|mno||u||" ) );
        assertTrue( test.readLine() == null );       

    } // method testWriteFile

} // class AudioFileControlTest
