/**This program will allow user to load flash card entries from file. The program will load 15 of these items (or all the items if there 
 * are fewer than 15 items), and will run through them in "slightly shuffled order".  When the user gets an item incorrect, the program will
 * display the correct response and continue to prompt user until they enter it correctly. After getting an item correct 4 times in a row, it 
 * will stop showing this item. When all items are completed 4 times or user decides to quit, GUI will end. Also allows user to save
 * file with new item correct count
 */
package flashCards;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextArea;
import javax.swing.UIManager;



/**
 * @author Kelley Loder
 */
public class StudyGui {

	private JFrame frame;
	private JTextField txtUserReponse;
	private JLabel lblStimulus;
	private JTextArea txtrCorrectResponse;
	private JButton btnSave;
	private JButton btnSubmit;
	private JButton btnLoad;
	private JButton btnNext;
	private StudyList study;	
	public String userResponse;
	public Boolean saveIsEnabled;
	public ArrayList<String> lines;
	public int itemCounter = 0;
	
	/**
	 * Launch the application.
	 */
	
	public void askToSaveThenQuit() {
		// ask user if they'd like to save program, if yes, call save function, then call quit function
		int YesNoCancel = JOptionPane.showConfirmDialog(frame, "Would you like to save before exiting?");
		if(YesNoCancel == JOptionPane.YES_OPTION) {
			save();
		}
		quit();		
	}
	
	public void save() {
		// save program and send user message when successful
		lines = study.itemsToStrings();
		try {
			study.save();
		} catch (IOException e1) {
			System.out.println(e1);
		}
		
		JOptionPane.showMessageDialog(frame, "Successfully saved!");
	
	}
	
	public void quit() {
		// quit program
		JOptionPane.showMessageDialog(frame, "Thanks for playing!");
		System.exit(0);
	}
	
	
	public void checkItemListCompleted() {
		// check if all items have been answered
		if(itemCounter >= study.studyItems.size()) {
			int YesNoCancel1 = JOptionPane.showConfirmDialog(frame, "You've finished the set! Would you like to save?");
			if(YesNoCancel1 == JOptionPane.YES_OPTION) {
				save();
			}
			int YesNoCancel2 = JOptionPane.showConfirmDialog(frame, "Would you like to play again?");
			if(YesNoCancel2 == JOptionPane.YES_OPTION) {
				study.shuffleStudyItems();
				if(study.studyItems.size() == 0) {
					JOptionPane.showMessageDialog(frame, "You've completed the set!");
					quit();
				}
				itemCounter = 0;
			}
			else {
				quit();
			}	
		}
	}
	
		public static void main(String[] args) {
		// create new StudyGui
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudyGui window = new StudyGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		}

	/**
	 * Create the application.
	 */
	public StudyGui() {
		// create new StudyList and initialize GUI
		study = new StudyList();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		//initialize GUI and set action listeners
		
		// frame initializer
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		// let's study label
		JLabel lblLetsStudy = new JLabel("Let's study!");
		lblLetsStudy.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblLetsStudy.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetsStudy.setBounds(158, 6, 117, 16);
		frame.getContentPane().add(lblLetsStudy);
		

		// save button
		final JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if user presses save button, call save function
				lines = study.itemsToStrings();
				save();
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(327, 31, 117, 29);
		frame.getContentPane().add(btnSave);
		
		// quit button
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if user presses quit button, call function to ask to save/quit
				if(btnSave.isEnabled()) {
					askToSaveThenQuit();
				}
				else {
					quit();
				}
			}
		});
		btnQuit.setBounds(327, 61, 117, 29);
		frame.getContentPane().add(btnQuit);
		
		// stimulus label
		final JLabel lblStimulus = new JLabel("");
		lblStimulus.setBackground(new Color(255, 255, 255));
		lblStimulus.setForeground(Color.BLUE);
		lblStimulus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStimulus.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStimulus.setBounds(20, 92, 411, 34);
		frame.getContentPane().add(lblStimulus);
				
		// user response text field
		final JTextField txtUserResponse = new JTextField();
		txtUserResponse.setForeground(Color.BLUE);
		txtUserResponse.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		txtUserResponse.setHorizontalAlignment(SwingConstants.CENTER);
		txtUserResponse.setText("");
		txtUserResponse.setBounds(30, 138, 397, 28);
		frame.getContentPane().add(txtUserResponse);
		txtUserResponse.setColumns(10);
				
		// correct response label
		final JTextArea txtrCorrectResponse = new JTextArea("Please load a study list");
		txtrCorrectResponse.setEditable(false);
		txtrCorrectResponse.setWrapStyleWord(true);
		txtrCorrectResponse.setBackground(new Color(255, 255, 255));
		txtrCorrectResponse.setLineWrap(true);
		txtrCorrectResponse.setForeground(Color.GRAY);
		txtrCorrectResponse.setBounds(20, 219, 411, 53);
		frame.getContentPane().add(txtrCorrectResponse);
		
		// next button
		final JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if next button is pressed
				
				// figure out where in the program user is based off of label at bottom of GUI
				
				// if user just got a question correct, do this
				if(txtrCorrectResponse.getText().equals("Correct! Press next to continue.")) {
					lblStimulus.setText(study.studyItems.get(itemCounter).getStimulus());
					txtrCorrectResponse.setForeground(Color.GRAY);
					txtUserResponse.setText("");
					txtrCorrectResponse.setText("Type correct response above and press submit.");
				}
				
				// if user just got question incorrect, display correct answer
				if(txtrCorrectResponse.getText().contains("is incorrect. The correct response is")) {
					txtrCorrectResponse.setForeground(Color.GRAY);
					txtUserResponse.setText("");
					txtrCorrectResponse.setText("Type the following correct response above and press submit: " 
					+ study.studyItems.get(itemCounter).getResponse());
				}
				
				// if user got question incorrect, and then retyped it correctly, do this
				if(txtrCorrectResponse.getText().contains("Try to remember that one next time.")) {
					lblStimulus.setText(study.studyItems.get(itemCounter).getStimulus());
					txtrCorrectResponse.setForeground(Color.GRAY);
					txtUserResponse.setText("");
					txtrCorrectResponse.setText("Type correct response above and press submit.");
				}
				
				// when you delete an item from list
				if(txtrCorrectResponse.getText().contains("Correct four times in a row!")) {
					txtrCorrectResponse.setForeground(Color.GRAY);
					txtrCorrectResponse.setText("Type correct response above and press submit.");
					lblStimulus.setText(study.studyItems.get(itemCounter).getStimulus());
					txtUserResponse.setText("");
				}
			}
		});
		btnNext.setEnabled(false);
		btnNext.setBounds(241, 178, 117, 29);
		frame.getContentPane().add(btnNext);
		
		// submit button
		final JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if user presses submit button
				
				// figure out where in the program user is based off of label at bottom of GUI
			
				// if user needs to type in response for first time given stimulus
				if(txtrCorrectResponse.getText().equals("Type correct response above and press submit.")) {
					
					// if response is correct, do this
					if(txtUserResponse.getText().equals(study.studyItems.get(itemCounter).getResponse())) {
						txtrCorrectResponse.setForeground(Color.green);
						txtrCorrectResponse.setText("Correct! Press next to continue.");
						Item i = study.studyItems.get(itemCounter);
						i.setTimesCorrect(i.getTimesCorrect() + 1);
						if(i.getTimesCorrect() == 4) {
							study.studyItems.remove(i);
							txtrCorrectResponse.setText("Correct four times in a row! This item will now be removed from list. Press next to continue.");
						}
						else{
							itemCounter++;
						}
						checkItemListCompleted();
					}
					
					// if response is incorrect, do this
					else {
						txtrCorrectResponse.setForeground(Color.red);
						txtrCorrectResponse.setText(txtUserResponse.getText() + " is incorrect. The correct response is " 
						+ study.studyItems.get(itemCounter).getResponse() + ". Press next to continue.");
						study.studyItems.get(itemCounter).setTimesCorrect(0);	
					}
				}
				
				// if user got response incorrect, do this
				if(txtrCorrectResponse.getText().contains("Type the following correct response above and press submit: ")) {
					if(txtUserResponse.getText().equals(study.studyItems.get(itemCounter).getResponse())) {
						txtrCorrectResponse.setForeground(Color.GRAY);
						txtrCorrectResponse.setText("Try to remember that one next time. Press next to continue.");
						itemCounter++;
						checkItemListCompleted();
					}
					else {
						txtrCorrectResponse.setForeground(Color.red);
						txtrCorrectResponse.setText(txtUserResponse.getText() + " is incorrect. The correct response is " 
						+ study.studyItems.get(itemCounter).getResponse() + " . Press next to continue.");
					}
				}
			}
		});
		btnSubmit.setEnabled(false);
		btnSubmit.setBounds(86, 178, 117, 29);
		frame.getContentPane().add(btnSubmit);
		

		// load button
		final JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if user presses load button, call functions in study to load items and studyItems
		
					try {
						study.load();
						
					} catch (IOException e1) {
						System.out.println(e1);
					}
					
					study.loadStudyItems();
					
					// if items list is empty, do this
				if(study.studyItems.isEmpty()) {
					
					JOptionPane.showMessageDialog(frame, "No items in file!");
					quit();
				}
				
				// start game
				txtrCorrectResponse.setForeground(Color.GRAY);
				btnLoad.setEnabled(false);
				txtrCorrectResponse.setText("Type correct response above and press submit.");
				lblStimulus.setText(study.studyItems.get(itemCounter).getStimulus());
				btnSave.setEnabled(true);
				btnSubmit.setEnabled(true);
				btnNext.setEnabled(true);
			}
				
		});
		btnLoad.setBounds(327, 2, 117, 29);
		frame.getContentPane().add(btnLoad);
		
	}
}
