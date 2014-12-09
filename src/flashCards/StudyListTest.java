/**
 * 
 */
package flashCards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Lu Lu and Kelley Loder
 */
public class StudyListTest {
	StudyList studylist;
	Item item1;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    	studylist = new StudyList();
    	item1 = new Item("sti1", "res1");
    	studylist.add(item1);
    }

    /**
     * Test method for {@link flashCards.StudyList#StudyList()}.
     */
    @Test
    public final void testStudyList() {
    	StudyList studylist1 = new StudyList();
    }

    /**
     * Test method for {@link flashCards.StudyList#add(flashCards.Item)}.
     */
    @Test
    public final void testAdd() {
    	assertTrue(studylist.items.contains(item1));
    }

    /**
     * Test method for {@link flashCards.StudyList#find(java.lang.String)}.
     */
    @Test
    public final void testFind() {
    	assertEquals(item1, studylist.find("sti1"));
    	assertEquals(item1, studylist.find("res1"));
    }

    /**
     * Test method for {@link flashCards.StudyList#delete(flashCards.Item)}.
     */
    @Test
    public final void testDelete() {
    	assertTrue(studylist.items.contains(item1));
    	studylist.delete(item1);
    	assertFalse(studylist.items.contains(item1));

    }

    /**
     * Test method for {@link flashCards.StudyList#modify(flashCards.Item, java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testModify() {
    	studylist.modify(item1, "sti2", "res2");
    	assertEquals("sti2", item1.getStimulus());
    	assertEquals("res2", item1.getResponse());
    }
    
    @Test
    public final void testItemsToStrings(){
    	String s = "sti1 || res1 || 0";
    	ArrayList<String> sList = new ArrayList<String>();
    	sList.add(s);
    	assertEquals(sList, studylist.itemsToStrings());
    }
}