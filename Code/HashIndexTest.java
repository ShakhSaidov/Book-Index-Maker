import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class HashIndexTest.
 * @author Shakh Saidov
 * @version 1.0
 */
public class HashIndexTest{
    /**
     * Checks the adding and sorting methods of HashIndex
     */
    @Test
    public void testHash(){
        HashIndex hash = new HashIndex();
        hash.addNew("word", 5, 0);              //word [5]      
        hash.addNew("new", 9, 0);               //new  [9]
        hash.addExisting("word", 18, 0);        //word [5, 18]
        hash.addExisting("word", 33, 0);        //word [5, 18, 33]
        hash.addExisting("word", 94, 0);        //word [5, 18, 33, 94]
        hash.addExisting("new", 154, 0);        //new  [9, 154]
        
        TreeSet<Integer> values1 = hash.get("word");
        assertEquals(values1.size(), 4);
        
        TreeSet<Integer> values2 = hash.get("new");
        String v2 = "";
        for(Integer i: values2){
            v2 += i + ", ";
        }
        assertEquals(v2, "9, 154, ");
        
        hash.addNew("some", 1424, 0);
        hash.addNew("another", 12525, 0);
        hash.addNew("again", 2105, 0);
        
        ArrayList<String> sortedHash = hash.sortHashKey();
        assertEquals("[again, another, new, some, word]", Arrays.toString(sortedHash.toArray()));
    }
}
