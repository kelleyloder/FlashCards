/**This program will allow user to load flash card entries from file, and add, find ,edit, delete these entries. 
 * Also after editing, the user can also save the entries to the original file, or save to other files.
 * 
 */
package flashCards;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * @author Lu Lu
 */
public class EditorGui extends JFrame {
	StudyList studylist = new StudyList();
	DefaultListModel<String> listModel;
	JList lists;
	JPanel topButtons;
	JPanel rightButtons;
	Boolean saved;

    public void init(){
    	saved = true;
    	setLayout(new BorderLayout());
    	
    	//Jlist
    	JPanel listPro = new JPanel();
    	listPro.setLayout(new BorderLayout());
    	listModel = new DefaultListModel();
    	lists = new JList(listModel);
    	JScrollPane scrlpane=new JScrollPane(lists);
    	listPro.add(scrlpane);
    	
    	
    	//topButtons
    	topButtons = new JPanel();
    	topButtons.setLayout(new FlowLayout(0));
    	
    	JButton load = new JButton("Load");
    	load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				listModel.clear();
				try {
					studylist.load();
				} catch (Exception e) {
					System.out.println(e);
				}	
				for (String str: studylist.itemsToStrings()){
					listModel.addElement(str);
				}
			}
    	});
    	
    	JButton save = new JButton("Save");
    	save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				saved = true;
				try {
					studylist.save();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
    	});
    	
    	JButton saveAs = new JButton("saveAs..");
    	saveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				saved = true;
				try {
					studylist.saveAs();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
    	});
    	
    	
    	JButton quit = new JButton("Quit");
    	quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (!saved) {
					int yesNo = JOptionPane.showConfirmDialog(topButtons,
							"Save changes before closing?");
					if (yesNo == JOptionPane.YES_OPTION) {
						setVisible(false); 
						dispose();
					}
				}else{
					setVisible(false); 
					dispose();
				}
			}
    	});
    	topButtons.add(load);
    	topButtons.add(save);
    	topButtons.add(saveAs);
    	topButtons.add(quit);
    	
    	//rightButtons
    	rightButtons = new JPanel();
    	rightButtons.setLayout(new GridLayout(4, 1, 0, 7));
    	
    	JButton add = new JButton("Add");
    	add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				saved = false;
				String stimulus = JOptionPane.showInputDialog(rightButtons, "Please enter stimulus");
				String response = JOptionPane.showInputDialog(rightButtons, "Please enter response");
				stimulus = stimulus.trim();
				response = response.trim();
				if (!stimulus.equals("") && !response.equals("")){
					Item newItem = new Item(stimulus, response);
					studylist.add(newItem);
					listModel.addElement(newItem.toString());
				}
			}
    	});
    	
    	JButton find = new JButton("Find");
    	find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String stiOrRes = JOptionPane.showInputDialog(rightButtons,
						"Please enter stimulus or response to search");
				stiOrRes = stiOrRes.trim();
				Item itemFound = studylist.find(stiOrRes);
				if (itemFound == null){
					JOptionPane.showMessageDialog(rightButtons,
			                   "Can't find the stimulus or response");
				} else{
					JOptionPane.showMessageDialog(rightButtons,
			                   itemFound);
				}
				
			}
    	});
    	
    	JButton edit = new JButton("Edit");
    	edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				saved = false;
				String stiOrRes = JOptionPane.showInputDialog(rightButtons,
						"Please enter stimulus or response to search");
				stiOrRes = stiOrRes.trim();
				Item itemFound = studylist.find(stiOrRes);
				if (itemFound == null){
					JOptionPane.showMessageDialog(rightButtons,
			                   "Can't find the stimulus or response");
				} else{
					String stringFound = itemFound.toString();
					String stimulus = JOptionPane.showInputDialog(rightButtons,
							"Please enter new stimulus");
					String response = JOptionPane.showInputDialog(rightButtons,
							"Please enter new response");
					stimulus = stimulus.trim();
					response = response.trim();
					if (!stimulus.equals("") && !response.equals("")){
						studylist.modify(itemFound, stimulus, response);
						listModel.setElementAt(itemFound.toString(), listModel.indexOf(stringFound));
					}
				}
			}
    	});
    	
    	JButton delete = new JButton("Delete");
    	delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				saved = false;
				String stiOrRes = JOptionPane.showInputDialog(rightButtons,
						"Please enter stimulus or response to search");
				stiOrRes = stiOrRes.trim();
				Item itemFound = studylist.find(stiOrRes);
				if (itemFound == null){
					JOptionPane.showMessageDialog(rightButtons,
			                   "Can't find the stimulus or response");
				} else{
					String stringFound = itemFound.toString();
					listModel.removeElement(stringFound);
					studylist.delete(itemFound);
				}
			}
    	});
    	
    	rightButtons.add(add);
    	rightButtons.add(find);
    	rightButtons.add(edit);
    	rightButtons.add(delete);
    	
    	add(topButtons, BorderLayout.NORTH);
    	add(rightButtons, BorderLayout.EAST);
    	add(listPro);
    	
    	setVisible(true);
    	setSize(400, 400);
    }
    public static void main(String[] args) {
        new EditorGui().init();
    }

}