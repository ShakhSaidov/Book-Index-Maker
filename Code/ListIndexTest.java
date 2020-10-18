import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class ListIndexTest.
 * @author  Shakh Saidov
 * @version 1.0
 */
public class ListIndexTest{
    /**
     * Test the adding methods of ListIndex
     */
    @Test
    public void testList(){
        ListIndex list = new ListIndex();

        //Setting up the contents of list
        int bSearch = list.listBS("word");
        if(bSearch < 0){                      
            list.addExisting("word", 5, bSearch);
        } else if(bSearch >= 0){
            list.addNew("word", 5, bSearch);
        }
        bSearch = list.listBS("new");
        if(bSearch < 0){                     
            list.addExisting("new", 9, bSearch);
        } else if(bSearch >= 0){
            list.addNew("new", 9, bSearch);
        }
        bSearch = list.listBS("word");
        if(bSearch < 0){                    
            list.addExisting("word", 18, bSearch);
        } else if(bSearch >= 0){
            list.addNew("word", 18, bSearch);
        }
        bSearch = list.listBS("word");
        if(bSearch < 0){                     
            list.addExisting("word", 33, bSearch);
        } else if(bSearch >= 0){
            list.addNew("word", 33, bSearch);
        }
        bSearch = list.listBS("word");
        if(bSearch < 0){                   
            list.addExisting("word", 94, bSearch);
        } else if(bSearch >= 0){
            list.addNew("word", 94, bSearch);
        }        
        bSearch = list.listBS("new");
        if(bSearch < 0){                      
            list.addExisting("new", 154, bSearch);
        } else if(bSearch >= 0){
            list.addNew("new", 154, bSearch);
        }

        TreeSet<Integer> values1 = list.get("word");
        assertEquals(values1.size(), 4);

        TreeSet<Integer> values2 = list.get("new");
        String v2 = "";
        for(Integer i: values2){
            v2 += i + ", ";
        }
        assertEquals(v2, "9, 154, ");
    }
}
