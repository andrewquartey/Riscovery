package com.pace.riscovery.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import com.pace.riscovery.DueDates;
import com.pace.riscovery.MyRunnable;
import com.pace.riscovery.Logic.Logic;


public class ApplicationView extends JFrame implements View{

	private static final long serialVersionUID = -7360610421229722456L;
	
	Logic logic;
	
	private Action openAction, importAction, exitAction;
	private JButton button1, button2;	
		
	public ApplicationView(){
		initActions();	
		initMenu();
		initToolbar();
		initComponents();						
	}
		
	private void initComponents() {		
		GridBagConstraints c = new GridBagConstraints();
		//SplitPane
		JSplitPane split = new JSplitPane();
		
		//Left Component
		JPanel leftPanel = new JPanel(new GridBagLayout());
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addElement("Motor");
		model.addElement("House");		
		JList<String> list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(10,0,0,0);  //top padding
		leftPanel.add(listScroller, c);
		
		JButton button;
		//Right component		
		JPanel rightPanel = new JPanel(new GridBagLayout());
		button = createButton1();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
//			c.gridwidth = ;
		c.ipady = 5;
		rightPanel.add(button, c);
		
		
		button = createButton2();
		c.gridx = 10;
		c.gridy = 0;
		c.gridwidth = 15;
		rightPanel.add(button, c);
		
		split.setLeftComponent(leftPanel);
		split.setRightComponent(rightPanel);
		this.add(split);					
	}

	private void initToolbar() {
		// TODO Auto-generated method stub		
	}

	private JButton createButton1(){
		button1 = new JButton("Check Expired Policies");
		button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
//					 TODO Auto-generated method stub
				Runnable threadJob = new MyRunnable();
				Thread myThread = new Thread(threadJob);
				myThread.start();								
			}});
		return button1;		
	}
	
	// This method is responsible for assignment button2 to the JList of expired/due policies
	public JButton createButton2(){
		button2 = new JButton("List of Due Polices");
		button2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				DueDates dueDate = new DueDates();  
				dueDate.go();
			}});
		return button2;
	}
		
	private void initMenu(){
		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu file = menuBar.add(new JMenu("File"));
//	        menuItem1 = new JMenuItem(" Open File...   ");
//	        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,
//	                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//	        menuItem1.addActionListener(openAction);        
//	        menu.add(menuItem1);        
//			menuBar.add(openAction);
		file.add(openAction);
		file.add(importAction);
		file.add(exitAction);
	}
	
	private void initActions(){
		//openAction is an holds an AbstractAction object
		openAction = new AbstractAction("Open"){  			
		private static final long serialVersionUID = 1L;

		@Override
		//This is executed when menuItem1 is clicked
		public void actionPerformed(ActionEvent e) {   
			getLogic().onOpen();
			}
		};
		
		exitAction = new AbstractAction("Exit"){
			private static final long serialVersionUID = -4268592738971814249L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub				
			}			
		};
	}
	
	@Override
	public void setLogic(Logic logic) {
		this.logic = logic;	
	}
	
	private Logic getLogic(){
		if(logic == null){
			throw new IllegalStateException("Application Logic not set");
		}
		return logic;
	}

	@Override
	public File showOpenFileChooser(final File selectedFile) {		
		final JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(selectedFile);
		if(fc.showOpenDialog(ApplicationView.this) == JFileChooser.APPROVE_OPTION){
			return fc.getSelectedFile();
		}	
		return null;
	}

	@Override
	public void showWarning(String title, String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);		
	}
}

