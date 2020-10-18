import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
/**
 * HashMap class that used the HashMap data structure to create an index for a given book 
 * @author Shakh Saidov
 */
public class HashIndex implements Index{
    private HashMap<String, TreeSet<Integer>> hash;
    private Scanner book;

    private Scanner dictionary;
    private ArrayList<String> dict;

    private double s1, e1, s2, e2, s3, e3 = 0;
    /**
     * Constructor
     * Used for unit-testing
     */
    public HashIndex(){
        hash = new HashMap<String, TreeSet<Integer>>();
    }
    
    /**
     * Constructor
     * @param b file of book
     * @param d file of dictionary
     */
    public HashIndex(File b, File d){
        s1 = System.currentTimeMillis();
        hash = new HashMap<String, TreeSet<Integer>>();
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
        hash = this.storeWords();               //tree is updated with its contents
        e2 = System.currentTimeMillis();

        s3 = System.currentTimeMillis();
        ArrayList<String> sorted = sortHashKey();

        File output = new File("hashout.txt");
        try{
            FileWriter writer = new FileWriter("hashout.txt");
            BufferedWriter bw = new BufferedWriter(writer);

            for(String s: sorted){                  //for every string in map
                String key = s.toString();

                TreeSet<Integer> value = hash.get(s);       //gets the value of the key
                ArrayList<Integer> lines = new ArrayList<Integer>();
                for(Integer i: value){
                    lines.add(i);                       //converts the values array format to print later
                }

                bw.write(key + " " + Arrays.toString(lines.toArray()));         //writes the lines appropriately
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
     * @return hashmap with filled keys & values
     */
    public HashMap<String, TreeSet<Integer>> storeWords(){
        int index = 0;              //keeps track of the line number
        while(book.hasNextLine()){
            index++;
            String line = book.nextLine();
            String[] words = line.split("[^A-Za-z]+");  //stores only words

            for(String s: words){
                s = s.toLowerCase();
                if(Collections.binarySearch(dict, s) >= 0){       //if the dictionary has the word
                    if(hash.containsKey(s)){                    //if the map already has the word, update value
                        addExisting(s, index, 0);
                    } else{         //create a new value otherwise
                        addNew(s, index, 0);
                    }
                }
            }
        }
        return hash;
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
     */
    @Override
    public void addExisting(String s, int index, int b){
        TreeSet<Integer> newTree = hash.get(s);
        newTree.add(index);
        hash.replace(s, newTree);
    }

    /**
     * Updates the value of the existing key
     * @param s key
     * @param index value
     */
    @Override
    public void addNew(String s, int index, int b){
        TreeSet<Integer> tree = new TreeSet<Integer>();
        tree.add(index);
        hash.put(s, tree);
    }

    /**
     * Sorts the keys of the hash, to make it alphabetical
     * @return arraylist of sorted keys
     */
    public ArrayList<String> sortHashKey(){
        ArrayList<String> sorted = new ArrayList<String>(hash.keySet()); //to sort the keys of the map
        Collections.sort(sorted);
        return sorted;
    }
    
    /**
     * Returns value of key
     */
    public TreeSet<Integer> get(String s){
        return hash.get(s);
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