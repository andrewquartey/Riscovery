package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.ListModel;

public class ListPanel extends JPanel {
	
	private JList<String> list = new JList<String>(new ListModel());
	JScrollPane listScroller = new JScrollPane(createList());
	GridBagConstraints gbc = new GridBagConstraints();
	
	public ListPanel(){
		super(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10,0,0,0);
		add(listScroller, gbc);		
	}
	
	private JList createList(){
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Only one item can be selected at a time
		list.setVisibleRowCount(-1);
		list.setFont(new Font("Calibri", Font.BOLD, 13));
		System.out.println((new ListModel()).get(10));
		
		return list;
	}
	
}
