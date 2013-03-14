/**
 * This class represents the collection of audio files used by the program.
 * This will be built around an array of AudioFile objects which will have
 * the methods to manage the collection.
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
public class AudioList
{
    //declaration and initializations
    private AudioFile[] collection;
    private int collectionLength = 16;

    /**
     * AudioList -Default Constructor that is used to initialize the values of
     * the object.
     */
    public AudioList() 
    {
        // create a new collection array that defaults to a length of 16
        collection = new AudioFile[16];

    } // end Default Constructor

    /**
     * add - will be used to add a new object to the array of audioFile
     * objects may or may not already exist.
     * 
     * @param add adds a few AudioFile to the collection (AudioFile)
     */
    public void add(AudioFile add)
    {
        // if the array is full, double the size of the array.
        if (isFull())
        {
            doubleArraySize();
        } // end error checker

        // add the new audioFile at the end of the array
        collection[arrayLength()] = add;

    } // end add method

    /**
     * arrayLength - will be used to return the length of the array to the
     * method call
     * 
     * @return the number of objects in the array
     */
    public int arrayLength()
    {
        //declaration and initializations
        int count = 0;

        // Goes through the collection array and looks for empty spots.
        for (AudioFile title : collection)
        {
            if (title != null)
            {
                count++;
            }
        }

        // return the count to the method call
        return count;
        
    } // end arrayLength method
    
    /**
     * numberOfSlotsInArray - returns the size of the array and not the length
     * of the array.
     * 
     * @return number size of the array
     */
    public int numberOfSlotsInArray()
    {
        return collection.length;
    } // end numberOfSlotsInArray

    /**
     * delete - method is used to delete audio files from the array of 
     * collections. For V1 it will only delete the file in the last spot of
     * the array.
     * 
     * @param location
     */
    public void delete()
    {
        //declaration and initializations
        AudioFile[] newCollection = new AudioFile[collectionLength];

        // loop through and copy all spots in the array except for the last one
        for (int i = 0; i < arrayLength(); i++)
        {
            if (i == arrayLength() - 1)
            {
                newCollection[i] = null;
            }
            else
            {
                newCollection[i] = collection[i];
            }
        }
        
        // return the new collection to the method call
        collection = newCollection;

    } // end delete method

    /**
     *  doubleArraySize - will be used to double the size of the array
     *  in the case that is is filled.
     */
    public void doubleArraySize()
    {
        // declaration and initializations
        int newAudioFileSize = collectionLength * 2;
        AudioFile[] newCollection = new AudioFile[newAudioFileSize];

        // for loop to copy the old array into the new, larger array
        for (int i = 0; i < collectionLength; i++)
        {
            newCollection[i] = collection[i];
        } // end copying for loop

        // reset the values in the class
        collection = newCollection;
        collectionLength = newAudioFileSize;

    } // end doubleArraySize

    /**
     *  getCollection - will be used to return the collection of audio
     *  files to the statement that called it.
     *
     * @param i is the intriguer position in the array (int)
     * @return the song at the specified spot in the array (AudioFile)
     */
    public AudioFile getCollection(int i)
    {
        // declaration and initializations
        int spot = 0;
        
        // gets the last audioFile in the array list
        if (arrayLength() != 0)
        {
            spot = i - 1;
        }
        else 
        {
            spot = 0;
        }
        
        // the audioFile to the method call
        return collection[spot];

    } // end getCollection method

    /**
     * isFull - will be used to test to see if the array is full.
     * 
     * @return true if full, false if not
     */
    public boolean isFull()
    {
        // declaration and initializations
        boolean forReturn = false;

        // if there is no empty spot return true to signify the array is full.
        if (arrayLength() == collectionLength)
        {
            forReturn = true;
        } // end if

        // return the value to the method call
        return forReturn;
    } // end isFull method

} // end AudioList Class