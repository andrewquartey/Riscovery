package com.pace.riscovery.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.pace.riscovery.MyRunnable;
import com.pace.riscovery.Logic.Logic;


public class ApplicationView extends JFrame implements View{

	private static final long serialVersionUID = -7360610421229722456L;
	
	Logic logic;
			    
	private Action openAction, importAction, exitAction;
//	private JButton button1, button2;	
	final JTextField filterText = new JTextField(20);
	
	private String[] columnNames = {"NO.", "INSURED", "CONTACT",
        "EMAIL ADD.", "POSTAL ADD.", "COVER", "INSURER", "POLICY NO",
        "COMM. DATE", "EXP. DATE", "PREM. CHARGED", "PREM. PAID", "PREM. O/S"
        };

	private Object[][] data = {
	    {"1", "Smith",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "Motor", "EIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
	     {"1", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)}
//	    {"John", "Doe",
//	     "Rowing", new Integer(3), new Boolean(true)},
//	    {"Sue", "Black",
//	     "Knitting", new Integer(2), new Boolean(false)},
//	    {"Jane", "White",
//	     "Speed reading", new Integer(20), new Boolean(true)},
//	    {"Joe", "Brown",
//	     "Pool", new Integer(10), new Boolean(false)}
	};
	
	TableModel model = new DefaultTableModel(data, columnNames) {
	      public Class getColumnClass(int column) {
	        Class returnValue;
	        if ((column >= 0) && (column < getColumnCount())) {
	          returnValue = getValueAt(0, column).getClass();
	        } else {
	          returnValue = Object.class;
	        }
	        return returnValue;
	      }
	    };
	final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	
	public ApplicationView(){
		initActions();	
		initMenu();
		initToolbar();
		initComponents();						
	}
		
	private void initComponents() {				
		//SplitPane
		JSplitPane split = new JSplitPane();
		
		//Left Component
		GridBagConstraints c = new GridBagConstraints();
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
		//Right componen		
		JPanel rightPanel = new JPanel(new BorderLayout());		
		button = createButton1();		
		JPanel lowerPanel = new JPanel();
		lowerPanel.add(button);		
		button = createButton2();		
		lowerPanel.add(button);		
		button = createFilterButton();
		lowerPanel.add(filterText);

		lowerPanel.add(button);
				
		JScrollPane scrollPane = createTable();
		rightPanel.add(scrollPane, BorderLayout.CENTER);		
		rightPanel.add(lowerPanel, BorderLayout.PAGE_END);
		
		split.setLeftComponent(leftPanel);
		split.setRightComponent(rightPanel);
		add(split);
		setSize(1000,400);
	}

	private void initToolbar() {
		
	}

	public JButton createButton1(){
		JButton button1 = new JButton("Check Expired Policies");
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
		JButton button2 = new JButton("List of Due Polices");
		button2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {						
				DueDates dueDate = new DueDates();  
				dueDate.go();
			}});
		return button2;
	}
		
	public JScrollPane createTable(){			
		final JTable table = new JTable(model){  				
		      public boolean isCellEditable(int row, int column){  			//prevents editing of cells
		          return false;  
		        }  
		      }; 
		table.getTableHeader().setReorderingAllowed(false);   //prevents reordering of columns
	    table.setRowSorter(sorter);	    
		table.setFont(new Font("Cambria", Font.PLAIN, 14));
		JScrollPane scrollPane = new JScrollPane(table);
		table.setAutoCreateRowSorter(true);
		table.setPreferredScrollableViewportSize(new Dimension(1500, 600));
        table.setFillsViewportHeight(true);
        //set Table Column width
        
        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0 ) {
                column.setPreferredWidth(10); //third column is bigger
            } else if (i == 4){
                column.setPreferredWidth(200);
            }
            else {
            	column.setPreferredWidth(50);
            }
            
        }
        
		return scrollPane;
	}
	
	public JButton createFilterButton(){
		JButton button = new JButton("Filter");
		button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        String text = filterText.getText();
	        if (text.length() == 0) {
	          sorter.setRowFilter(null);
	        } else {
	          sorter.setRowFilter(RowFilter.regexFilter(text));
	        }
	      }
	    });
	return button;
		
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

