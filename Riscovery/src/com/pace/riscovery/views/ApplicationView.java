package com.pace.riscovery.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.pace.riscovery.Motor;
import com.pace.riscovery.MotorHelper;
import com.pace.riscovery.MyRunnable;
import com.pace.riscovery.Logic.Logic;


public class ApplicationView extends JFrame implements View{

	private static final long serialVersionUID = -7360610421229722456L;
	
	Logic logic;
			    
	private Action openAction, importAction, exitAction, editAction, addAction, deleteAction, enterAction;
//	private TableAction ;
//	private JButton button1, button2;	
	final JTextField filterText = new JTextField(20);	
	
	private String[] columnNames = {"NO.", "INSURED", "CONTACT",
        "EMAIL ADD.", "POSTAL ADD.", "COVER", "INSURER", "POLICY NO",
        "COMM. DATE", "EXP. DATE", "PREM. CHARGED", "PREM. PAID", "PREM. O/S"
        };
	
	//row contents	
	private Object[][] data = {
	    {"1", "Smith",
	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "Motor", "EIC", "DMC001", "12/10/2014", "17/10/2014",
	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"2", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	     {"1", "Lobo",
//	     "0265324264", "yestrup@gmail.com", "P.O. Box GP, 4025 Accra", "House", "NIC", "DMC001", "12/10/2014", "17/10/2014",
//	     new Double(1200.95), new Double(133.67), new Double(42)},
//	    {"John", "Doe",
//	     "Rowing", new Integer(3), new Boolean(true)},
//	    {"Sue", "Black",
//	     "Knitting", new Integer(2), new Boolean(false)},
//	    {"Jane", "White",
//	     "Speed reading", new Integer(20), new Boolean(true)},
//	    {"Joe", "Brown",
//	     "Pool", new Integer(10), new Boolean(false)}
	};
	
	
	DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
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
	    
	//Work on sorting later
//	final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	
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
		
		ListView list;
		list = createList();
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
	
	
	//Toolbar. Maybe later?
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
		
	public ListView createList(){
		final ListView list = new ListView();
		list.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				 if (!e.getValueIsAdjusting()){
					 JList jl = (JList)e.getSource();
					 String selected = jl.getSelectedValue().toString();
//					 System.out.println(selected);
					 switch(selected){
					 	case "MOTOR": 
					 		List<Motor> motors = MotorHelper.getInstance().getMotors();
					 		tableModel.setRowCount(0);
					 		for(Motor m: motors){
					 			int i=0;
					 			//update data object with data from the database
					 			data[i][0] = m.getId();
								data[i][1] = m.getInsured();
								data[i][2] = m.getContact();
								data[i][3] = m.getEmail();
								data[i][4] = m.getPostalAddress();
								data[i][5] = m.getCover();
								data[i][6] = m.getInsurer();
								data[i][7] = m.getPolicyNo();
								data[i][8] = m.getCommencementDate();
								data[i][9] = m.getExpiryDate();
								data[i][10] = m.getPremiumCharged();
								data[i][11] = m.getPaid();
								data[i][12] = m.getBalance();
								System.out.println(m.getInsured());
								tableModel.addRow(data[i]);
					 			i++;
					 		}
					 		tableModel.fireTableDataChanged();					 								 
					 }					 
				 }				    				
			}			
		});
		return list;
	}
	public JScrollPane createTable(){			
			  table = new JTable(tableModel);//{  				
//		      public boolean isCellEditable(int row, int column){  			//prevents editing of cells
//		          return false;  
//		        }  
//		      }; 
		table.getTableHeader().setReorderingAllowed(false);   //prevents reordering of columns
//	    table.setRowSorter(sorter);	    
		table.setFont(new Font("Cambria", Font.PLAIN, 14));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setAutoCreateRowSorter(true);		
        table.setFillsViewportHeight(true);
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
            	column.setPreferredWidth(130);
            }
            else if(i == 6){
            	column.setPreferredWidth(50);
            }
            else if(i ==8 ){
            	column.setPreferredWidth(95);
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
                // Right-click Popup
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
//        table.getModel().addTableModelListener(new TableModelListener(){
//
//			@Override
//			public void tableChanged(TableModelEvent e) {
//				int row = e.getFirstRow();
//				int col = e.getColumn();
//				TableModel tm = (TableModel)e.getSource();
//				Object data = tm.getValueAt(row, col);
////				System.out.println(data.toString());
//			}
//        	
//        });
//        
//        table.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//
//                    int row = table.getSelectedRow();
//                    int column = table.getSelectedColumn();
//                    System.out.println(row);
//                   
//                    
//                    // resul is the new value to insert in the DB
//                    String resul = table.getValueAt(row, column).toString();
//                    // id is the primary key of my DB
//                    String id = table.getValueAt(row, 0).toString();
//
//                    // update is my method to update. Update needs the id for
//                    // the where clausule. resul is the value that will receive
//                    // the cell and you need column to tell what to update.
////                    update(id, resul, column);
//                   
//                    System.out.println(resul);
//                }
//            }
//        });
                
        String solve = "Enter";
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true);
        table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter, solve);
        table.getActionMap().put(solve, enterAction);
        
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
	        //work on filter and sorting 
//	        if (text.length() == 0) {
//	          sorter.setRowFilter(null);
//	        } else {
//	          sorter.setRowFilter(RowFilter.regexFilter(text));
//	        }
	      };
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
				
//				System.out.println(table.getColumnName(4));
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
		
		enterAction = new AbstractAction(){			
						
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("kkk");
				int row = table.getSelectedRow();
	            int column = table.getSelectedColumn();
	            String resul = table.getValueAt(row, column).toString();
	            
	            System.out.println(resul);
			}            
		};
				
//		loadAction = new ActionListener{
			
//		}
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