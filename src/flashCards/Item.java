/**Class for Item object, where an Item object created has two instance variables: a stimulus and a response (both strings).
 * Functions include things to access/modify Items.
 * 
 */
package flashCards;

/**
 * @ Lu Lu and Kelley Loder
 */
public class Item {
    String stimulus;
    String response;
    int timesCorrect;
    
    public Item(String stimulus, String response) {
    	// creates a new Item object with instance variables stimulus and response strings
        this.stimulus = stimulus;
        this.response = response;
    }
    
    public String getStimulus() {
    	// returns stimulus for Item
        return stimulus;
    }
    
    public void setStimulus(String stimulus) {
    	// sets Item stimulus to new passed in string value
        this.stimulus = stimulus;
    }
    
    public String getResponse() {
    	// returns response for Item
        return response;
    }
    
    public void setResponse(String response) {
    	// sets Item response to new passed in string value
        this.response = response;
    }
    
    public int getTimesCorrect() {
    	// returns global variable of timesCorect
        return timesCorrect;
    }
    
    public void setTimesCorrect(int times) {
    	// sets Item timesCorrect to new passed in int value
    	timesCorrect = times;
    }
    
    public String toString(){
    	// returns the stimulus, response, and times correct in one string 
    	return stimulus + " || " + response + " || " + timesCorrect;
    }
}
