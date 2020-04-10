package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save
	 * list, and load list.
	 *
	 * When add task is clicked: Create a JOptionPane to ask the user for a task and
	 * add it to an ArrayList
	 * 
	 * When the view tasks button is clicked: show all the tasks in the list
	 * 
	 * When the remove task button is clicked: Create a JOptionPane to prompt the
	 * user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked: Save the list to a file
	 * 
	 * When the load list button is clicked: Create a JOptionPane to Prompt the user
	 * for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file
	 * into the list.
	 */
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	JButton add = new JButton();
	JButton view = new JButton();
	JButton remove = new JButton();
	JButton save = new JButton();
	JButton load = new JButton();
	ArrayList<String> stuff = new ArrayList<String>();

	public static void main(String[] args) {
		ToDoList eek = new ToDoList();
		eek.doStuff();
	}

	public void doStuff() {
		frame.add(panel);
		panel.add(label);
		panel.add(add);
		panel.add(view);
		panel.add(remove);
		panel.add(save);
		panel.add(load);
		add.addActionListener(this);
		view.addActionListener(this);
		remove.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		add.setText("Add");
		view.setText("View List");
		remove.setText("Remove");
		save.setText("Save List");
		load.setText("Load List");
		label.setText("Please select an option:");
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == add) {
			String add = JOptionPane.showInputDialog("Which task do you want to add?");
			stuff.add(add);
		} else if (e.getSource() == remove) {
			int remove = Integer.parseInt(JOptionPane.showInputDialog("Which task number do you want to remove? (task numbers can be found in list)"));
			stuff.remove(remove - 1);
		} else if (e.getSource() == view) {
			if (stuff.isEmpty()) {
				JOptionPane.showMessageDialog(null, "There is nothing in your list. Try adding something!",	"Nothing in List!", JOptionPane.ERROR_MESSAGE);
			} else {
				String list = "List:\n";
				for (int i = 0; i < stuff.size(); i++) {
					list = list + (i + 1) + ". " + stuff.get(i) + "\n";
				}
				JOptionPane.showMessageDialog(null, list);
			}
		}else if (e.getSource() == save) {
			String name = JOptionPane.showInputDialog("What will the file name be? If the name is the same as a pre-existing file it will be overwritten. No spaces.");
			String list = "";
			for (int i = 0; i < stuff.size(); i++) {
				list = list + stuff.get(i) + "\n";
			}
			try {
				FileWriter fw = new FileWriter("src/_03_To_Do_List/"+name+".txt");
				fw.write(list);
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if (e.getSource() == load) {
			String name = JOptionPane.showInputDialog("What is the file name you want to load?");
			stuff.clear();
			try {
				BufferedReader br = new BufferedReader(new FileReader("src/_03_To_Do_List/"+name+".txt"));
				
				String line = br.readLine();
				while(line != null){
					stuff.add(line);
					line = br.readLine();
				}
				
				br.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}

	}
}
