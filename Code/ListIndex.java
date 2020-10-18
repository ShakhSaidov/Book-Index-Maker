import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
/**
 * ListIndex class that used the ArrayList data structure to create an index for a given book
 * @author Shakh Saidov
 */
public class ListIndex implements Index{
    private ArrayList<Entry> list;

    private Scanner book;

    private Scanner dictionary;
    private ArrayList<String> dict;

    private double s1, e1, s2, e2, s3, e3 = 0;      //stores the running time at the appropriate instances

    class Entry implements Comparable<String>{
        private String s;
        private TreeSet<Integer> lines;

        /**
         * Constructor
         * @param s word
         */
        public Entry(String s){
            this.s = s;
            lines = new TreeSet<Integer>();
        }

        /**
         * Accessor for the word
         * @return word
         */
        public String getWord(){
            return this.s;
        }

        /**
         * Accessor for the lines that the word appears in
         * @return line numbers
         */
        public TreeSet<Integer> getLines(){
            return this.lines;
        }

        /**
         * Updates the lines that the word appears in
         * @param i line number
         */
        public void addLine(Integer i){
            lines.add(i);
        }

        /**
         * Compares the entries based on the words alphabetically
         * @param word to be compared
         * @return 0 if equals, <0 if less, and >0 if greater
         */
        public int compareTo(String word){
            return s.compareTo(word);
        }
    }

    /**
     * Constructor
     * Used for unit-testing
     */
    public ListIndex(){
        list = new ArrayList<Entry>();
    }
    
    /**
     * Constructor
     * @param b file of book
     * @param d file of dictionary
     */
    public ListIndex(File b, File d){
        s1 = System.currentTimeMillis();
        list = new ArrayList<Entry>();
        try{
            book = new Scanner(b);
            this.makeDict(d);
        } catch(Exception e){
            e.printStackTrace();
        }
        e1 = System.currentTimeMillis();
    }

    /**
     * Method to fill the map and write a new file with its contents in order
     */
    @Override
    public void operate(){
        s2 = System.currentTimeMillis();
        list = this.storeWords();           //tree is updated with its contents
        e2 = System.currentTimeMillis();

        s3 = System.currentTimeMillis();
        File output = new File("listout.txt");
        try{
            FileWriter writer = new FileWriter("listout.txt");
            BufferedWriter bw = new BufferedWriter(writer);

            for(Entry e: list){       //for every string in map
                String key = e.getWord();

                TreeSet<Integer> value = e.getLines();       //gets the value of the key
                ArrayList<Integer> lines = new ArrayList<Integer>();
                for(Integer i: value){
                    lines.add(i);                       //converts the values to array format to print later
                }

                bw.write(key + " " + Arrays.toString(lines.toArray()));     //writes the lines appropriately
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        e3 = System.currentTimeMillis();
    }

    /**
     * Method to store all the words that appear in the book and the lines they occur at
     * @return treemap with filled keys & values
     */
    public ArrayList<Entry> storeWords(){
        int index = 0;              //keeps track of the line number
        while(book.hasNextLine()){
            index++;
            String line = book.nextLine();
            String[] words = line.split("[^A-Za-z]+");  //stores only words

            for(String s: words){
                s = s.toLowerCase();
                if(Collections.binarySearch(dict, s) >= 0){       //if the dictionary has the word
                    int bSearch = Collections.binarySearch(list, s);
                    if(bSearch < 0){                      //if the map already has the word, update value
                        addExisting(s, index, bSearch);
                    } else if(bSearch >= 0){
                        addNew(s, index, bSearch);
                    }
                }
            }
        }
        return list;
    }

    /**
     * Reads the dictionary file and stores the words
     * @param d dictionary file
     */
    @Override
    public void makeDict(File d){
        try{
            dictionary = new Scanner(d);
            dict = new ArrayList<String>();    //storing the words in dictionary
            while(dictionary.hasNextLine()){
                dict.add(dictionary.nextLine());
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Updates the value of the existing key
     * @param s key
     * @param index value
     * @param bSearch index
     */
    @Override
    public void addExisting(String s, int index, int bSearch){
        Entry e = new Entry(s);
        e.addLine(index);
        list.add(-bSearch - 1,e);
    }

    /**
     * Updates the value of the existing key
     * @param s key
     * @param index value
     * @param bSearch index
     */
    @Override
    public void addNew(String s, int index, int bSearch){
        Entry e = list.get(bSearch);
        e.addLine(index);
    }
    
    /**
     * Gets the values of Entries
     */
    public TreeSet<Integer> get(String s){
        for(Entry e: list){
            if(e.getWord().equals(s)){
                return e.getLines();
            }
        }
        return null;
    }
    
    /**
     * BinarySearch method needed for unit testing
     */
    public int listBS(String s){
        return Collections.binarySearch(list, s);
    }
    
    /**
     * Gets the set up time: storing the words from the book and dictionary in the data structure used
     */
    @Override
    public double getSetUpTime(){
        return((e1-s1)+(e2-s2));
    }
    
    /**
     * Gets the time taken to write a new file of index
     */
    @Override
    public double getWritingTime(){
        return (e3-s3);
    }
}