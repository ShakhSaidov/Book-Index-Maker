import java.io.File;
/**
 * Index is an interface that contains the methods all index-types should contain.
 * @author Shakh Saidov
 */
public interface Index{
    /**
     * Fills the hash/tree/list and writes a new file with its contents in order
     */
    void operate();
    
    /**
     * Reads the dictionary file and stores the words
     * @param d dictionary file
     */
    void makeDict(File d);
    
    /**
     * Updates the value of the existing key
     * @param s key
     * @param index value
     * @param bSearch used only in listIndex
     */
    void addExisting(String s, int index, int bSearch);
    
    /**
     * Updates the value of the existing key
     * @param s key
     * @param index value
     * @param bSearch used only in listIndex
     */
    void addNew(String s, int index, int bSearch);
    
    /**
     * Gets the set up time: storing the words from the book and dictionary in the data structure used
     */
    public double getSetUpTime();
    
    /**
     * Gets the time taken to write a new file of index
     */
    public double getWritingTime();
}
