import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class TreeIndexTest.
 * @author  Shakh Saidov
 * @version 1.0
 */
public class TreeIndexTest{
    /**
     * Tests the adding methods of TreeIndex
     */
    @Test
    public void testTree(){
        TreeIndex tree = new TreeIndex();
        
        tree.addNew("word", 5, 0);              //word [5]      
        tree.addNew("new", 9, 0);               //new  [9]
        tree.addExisting("word", 18, 0);        //word [5, 18]
        tree.addExisting("word", 33, 0);        //word [5, 18, 33]
        tree.addExisting("word", 94, 0);        //word [5, 18, 33, 94]
        tree.addExisting("new", 154, 0);        //new  [9, 154]
        
        TreeSet<Integer> values1 = tree.get("word");
        assertEquals(values1.size(), 4);
        
        TreeSet<Integer> values2 = tree.get("new");
        String v2 = "";
        for(Integer i: values2){
            v2 += i + ", ";
        }
        assertEquals(v2, "9, 154, ");
    }
}
