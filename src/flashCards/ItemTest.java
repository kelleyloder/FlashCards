/**
 * 
 */
package flashCards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Lu Lu and Kelley Loder
 */
public class ItemTest {
	Item item;
    @Before
    public void setUp() throws Exception {
    	item = new Item("myStimulus", "myResponse");
    }

    /**
     * Test method for {@link flashCards.Item#Item(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testItem() {
        Item item1 = new Item("CIT", "591");
        assertEquals("CIT", item1.stimulus);
        assertEquals("591", item1.response);
        assertEquals(0, item1.timesCorrect);
    }

    /**
     * Test method for {@link flashCards.Item#setStimulus(java.lang.String)} and
     * {@link flashCards.Item#getStimulus()} (combined).
     */
    @Test
    public final void testSetAndGetStimulus() {
    	assertEquals("myStimulus", item.getStimulus());
    	item.setStimulus("myNewStimulus");
    	assertEquals("myNewStimulus", item.getStimulus());
    }

    /**
     * Test method for {@link flashCards.Item#setResponse(java.lang.String)} and
     * {@link flashCards.Item#getResponse()} (combined).
     */
    @Test
    public final void testSetAndGetResponse() {
    	assertEquals("myResponse", item.getResponse());
    	item.setResponse("myNewResponse");
    	assertEquals("myNewResponse", item.getResponse());
    }

    /**
     * Test method for {@link flashCards.Item#setTimesCorrect(int)} and
     * {@link flashCards.Item#getTimesCorrect()} (combined).
     */
    @Test
    public final void testSetAndGetTimesCorrect() {
    	assertEquals(0, item.timesCorrect);
    	item.setTimesCorrect(5);
    	assertEquals(5, item.getTimesCorrect());
    }
    
    @Test
    public final void testToString() {
    	assertEquals("myStimulus || myResponse || 0", item.toString());
    }

}