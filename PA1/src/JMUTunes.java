import java.io.IOException;

/**
 * JMUTunes V1 is a program that will be used to manage audio
 * track information including artist, album, title, track.
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
public class JMUTunes
{

    /**
     * main - is used to start the JMUTunes program.
     * 
     * @param args array of Strings from a command line(String[])
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException 
    {

        AudioControl ac1 = new AudioControl();

        ac1.start();

    } // end main method

} // end JMUTunes Class
