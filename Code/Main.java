import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Write a description of class Main here.
 * @author Shakh Saidov
 */
public class Main{
    public static void main(String[] args){
        File dictionary = new File("English.txt");
        File[] books = {new File("Data1000000 - 1.txt"), new File("Data1000000 - 2.txt"), new File("Data1000000 - 3.txt"), 
                        new File("Data1000000 - 4.txt"), new File("Data1000000 - 5.txt")};

        double listSetUp = 0;
        double listWrite = 0;
        double treeSetUp = 0;
        double treeWrite = 0;
        double hashSetUp = 0;
        double hashWrite = 0;
        for(File f: books){
            Index index1 = new ListIndex(f, dictionary);
            index1.operate();
            listSetUp += index1.getSetUpTime();
            listWrite += index1.getWritingTime();

            Index index2 = new TreeIndex(f, dictionary);
            index2.operate();
            treeSetUp += index2.getSetUpTime();
            treeWrite += index2.getWritingTime();

            Index index3 = new HashIndex(f, dictionary);
            index3.operate();
            hashSetUp += index3.getSetUpTime();
            hashWrite += index3.getWritingTime();
        }

        System.out.println("ListIndex setting up time: " + listSetUp/5);
        System.out.println("ListIndex execution time: " + listWrite/5);
        System.out.println("TreeIndex setting up time: " + treeSetUp/5);
        System.out.println("TreeIndex execution time: " +  treeWrite/5);
        System.out.println("HashIndex setting up time: " + hashSetUp/5);
        System.out.println("HashIndex execution time: " +  hashWrite/5);
    }
}