import java.util.ArrayList;
import java.util.HashSet;

/**
 * The collection of AudioFile object
 * 
 * @author Michael L. Norton
 * @version PA1 (1/18/2013)
 * 
 * @version PA2 (2/20/2013)
 * 
 * Revisions for PA2:
 * 
 * Updated Add: Added the functionality to sort and not take a repeats
 * Added Sort: Sorts the AudioFile by the following order: Artist, Album, Track
 */
public class AudioList
{

    public boolean sortTwoStrings (String inList, String toAdd)
    {
        boolean forReturn = false;
        char inListHolder;
        char toAddHolder;
        boolean endLoop = false;
        int spot = 0;

        if (inList != null && toAdd != null)
        {
            inList.toLowerCase();
            toAdd.toLowerCase();
            do
            {
                try
                {
                    inListHolder = inList.charAt(spot);
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



//**************************************************************************************
//                                             FOR TESTING ONLY
//**************************************************************************************
    private void print(int i)
    {
        System.out.print(i);
    }
    private void print(String s)
    {
        System.out.print(s);
    }
    private void print(char c)
    {
        System.out.print(c);
    }  
    private void print(boolean b)
    {
        System.out.print(b);     
    }

} // class AudioList
