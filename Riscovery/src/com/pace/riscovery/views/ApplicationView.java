package com.pace.riscovery.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
			    
	private Action openAction, importAction, exitAction, editAction, addAction, deleteAction;
//	private TableAction ;
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
	     {"2", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
	     {"3", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
	     {"4", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
	     {"5", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
	     {"6", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
	     {"7", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
	     {"8", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
	     {"9", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
	     {"10", "Lobo",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)}
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
	JTable table = new JTable();
	
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
		model.addElement("MOTOR");
		model.addElement("HOME & PERSONAL ASSETS");		
		model.addElement("ASSETS ALL RISKS");
		model.addElement("WORKMEN'S COMPENSATION");
	    model.addElement("BUSINESS COMBINED");
		model.addElement("HOME OWNERS LIABILITY");
		model.addElement("FIDELITY GUARANTEE");
		model.addElement("PROFESSIONAL INDEMNITY");
		model.addElement("GOODS IN TRANSIT");
		model.addElement("EMPLOYER'S LIABIITY");
		model.addElement("PUBLIC LIABILITY");
		model.addElement("FOREIGN TRAVEL");
		model.addElement("PLANT AND MACHINERY");
		model.addElement("PROFESSIONAL INDEMNITY");
		model.addElement("FIRE & ALLIED PERILS");
		model.addElement("HOTEL ALL RISK");
		model.addElement("FIRE");
		JList<String> list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(-1);
		list.setFont(new Font("Cambria", Font.PLAIN, 14));
		JScrollPane listScroller = new JScrollPane(list);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(10,0,0,0);  //top padding
		leftPanel.add(listScroller, c);
		
		JButton button;
		//Right component		
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
//		setSize(1000,400);
		pack();
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
			  table = new JTable(model);//{  				
//		      public boolean isCellEditable(int row, int column){  			//prevents editing of cells
//		          return false;  
//		        }  
//		      }; 
		table.getTableHeader().setReorderingAllowed(false);   //prevents reordering of columns
	    table.setRowSorter(sorter);	    
		table.setFont(new Font("Cambria", Font.PLAIN, 14));
		
		table.setAutoCreateRowSorter(true);		
//        table.setFillsViewportHeight(true);
        //set Table Column width
        
        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0 ) {
                column.setPreferredWidth(30); //third column is bigger
            } 
            else if (i == 4){
                column.setPreferredWidth(200);
            }
            else if(i == 3){
            	column.setPreferredWidth(140);
            }
            else if(i == 6){
            	column.setPreferredWidth(60);
            }
            else {
            	column.setPreferredWidth(90);
            }            
        }
        
        //add right click features
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }

                int rowindex = table.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem Edit = new JMenuItem("Edit");                    
                    JMenuItem addClient = new JMenuItem("Add New Client");
                    JMenuItem delete = new JMenuItem("Delete Client");
                    Edit.addActionListener(editAction);
                    addClient.addActionListener(addAction);
                    delete.addActionListener(deleteAction);
                    popup.add(Edit);
                    popup.add(addClient);
                    popup.add(delete);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
//        table.setPreferredSize(arg0)
//        table.setPreferredScrollableViewportSize(getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);//, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_ALWAYS);
//        scrollPane.setPreferredSize(new Dimension(1200,300));
//        scrollPane.setPreferredSize(table.getPreferredScrollableViewportSize());
//        scrollPane.setHorizontalScrollBarPolicy(1);
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
		
			private static final long serialVersionUID = -486693816522683076L;

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
		
		editAction = new AbstractAction(){
			
			private static final long serialVersionUID = 328174414077464627L;

			@Override
			public void actionPerformed(ActionEvent arg0) {			
//				table.setColumnSelectionAllowed(true);
//			    table.setRowSelectionAllowed(true);
		
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();	
				table.editCellAt(row, col);
//			    if (success) {
//			      boolean toggle = false;
//			      boolean extend = false;
//			      table.changeSelection(row, col, toggle, extend);
//			    }
			}													
		};
		
		deleteAction = new AbstractAction(){
			
			private static final long serialVersionUID = -2465981498379904234L;
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				int selectedRow = table.getSelectedRow();
				if(selectedRow != -1){
					((DefaultTableModel) table.getModel()).removeRow(selectedRow);
				}
			}			
		};
		
		addAction = new AbstractAction(){

			private static final long serialVersionUID = -6007734049543677139L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
					((DefaultTableModel) table.getModel()).addRow(new Object[]{});	
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

