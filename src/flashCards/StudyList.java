/** Class for StudyList object, where a StudyList object created has two instance variables: an array list of Items and an array list of studyItems.
 * Functions include things to access/modify Items.
 * 
 */
package flashCards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import simpleIO.SimpleIO;

/**
 * @author Lu Lu and Kelley Loder
 */
public class StudyList {
	
	ArrayList<Item> items;
	ArrayList<Item> studyItems;
	final int studyNumber = 15;

    public StudyList(){
        items = new ArrayList<Item>();
        studyItems = new ArrayList<Item>();
    }
    
    public void add(Item item) {
    	// adds an item to the array list "items"
        items.add(item);
    }
    
    public Item find(String stimulusOrResponse) {
    	// finds the Item that contains stimulusOrResponse as it's stimulus or response instance variable, and returns the item
    	// if item doesn't exist, returns null
        for (Item item: items){
        	if (item.getStimulus().equals(stimulusOrResponse) ||
        		item.getResponse().equals(stimulusOrResponse)){
        		return item;
        	}
        }
        return null;
    }
    
    public void delete(Item item) {
    	// deletes the item from the array list of Items
        items.remove(item);
    }
    
    public void modify(Item item, String newStimulus, String newResponse) {
    	// when given an Item, changes it's stimulus and response, both of which must be passed in as arguments
        item.setStimulus(newStimulus);
        item.setResponse(newResponse);
    }
    
    public void load() throws IOException {
    	// calls SimpleIO to load a file and saves it to an array list of strings, then trims this and turns each into
    	// an Item with stimulus and response as instance variables
    	items.clear();
    	ArrayList<String> lines = SimpleIO.load();
        for (String line: lines){
        	if (line.trim().equals("")) continue;
        	String[] strings = line.split(" *\\|\\| *");
        	items.add(new Item(strings[0].trim(), strings[1].trim()));
        }
    }
    
    public void save() throws IOException {
    	// calls itemsToStrings to return an array list of strings, then sends this to SimpleIO to save over
    	// current file with this array list of strings as content
    	ArrayList<String> lines = itemsToStrings();
    	SimpleIO.save(lines);
    }
    
    public void saveAs() throws IOException {
    	// calls itemsToStrings to return an array list of strings, then sends this to SimpleIO to create
    	// a new file with the array list of strings as content
    	ArrayList<String> lines = itemsToStrings();
    	SimpleIO.saveAs(lines);
    }
    
    public ArrayList<String> itemsToStrings(){
    	// uses array list of Items to return an array list of strings
    	ArrayList<String> strings = new ArrayList<String>();
    	for (Item item: items){
    		String s = item.toString();
    		strings.add(s);
    	}
    	return strings;
    }
    
    public void loadStudyItems(){
    	// from array list of Items, semi-randomly selects 15 of them that haven't been gotten correct 4 or more times and adds
    	// them to an array list called studyItems
    	studyItems.clear();
    	int size = items.size();
    	Random r = new Random();
    	int randomStart = r.nextInt(size), counter1 = 0, counter2 = 0;
    	for (; counter1 < studyNumber; randomStart++){
			if (randomStart == size) randomStart = 0;
    		Item itemGet = items.get(randomStart);
    		if (itemGet.getTimesCorrect() < 4){
    			studyItems.add(itemGet);
    			counter1++;
    		}
    		counter2++;
    		if (counter2 == size) break;		
    	}
    }
    
    public void shuffleStudyItems(){
    	// takes current array list studyItems and slightly shuffles it
    	for(int i = 1; i < studyItems.size(); i++) {
    		double rr = Math.random();
    		if(rr < 0.33) {
    			Item temp = studyItems.get(i) ;  
    			studyItems.set(i, studyItems.get(i - 1) ) ; 
    			studyItems.set(i - 1, temp ) ; 
    		}
    	}
    }
    
    public ArrayList<String> studyItemsToStrings(){
    	// // uses array list of studyItems to return an array list of strings
    	ArrayList<String> strings = new ArrayList<String>();
    	for (Item item: studyItems){
    		String s = item.toString();
    		strings.add(s);
    	}
    	return strings;
    }
}