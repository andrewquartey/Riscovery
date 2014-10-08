package com.pace.riscovery;


import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import java.io.FileInputStream;


public class Application extends JFrame{

	private static final long serialVersionUID = -3568036449067713639L;

	
	private Action openAction;
	private JMenuItem menuItem1;
	
	JFileChooser fc = new JFileChooser();
	
	public Application(){
		initAction();
		initComponents();
	}
	
	private void initComponents(){
		add(createMenuBar(), BorderLayout.PAGE_START);
	}
	
	private JMenuBar createMenuBar(){
		final JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
        menuBar.add(menu);
        menuItem1 = new JMenuItem(" Open File...   ");
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menuItem1.addActionListener(openAction);
        
        menu.add(menuItem1);
        
//		menuBar.add(openAction);
		return menuBar;
	}
	
	private void initAction(){
		//openAction is an holds an AbstractAction object
		openAction = new AbstractAction(" Open File...	"){  
			
			private static final long serialVersionUID = 1L;

			@Override
			//This is executed when menuItem1 is clicked
			public void actionPerformed(ActionEvent e) {   
				
				if (e.getSource() == menuItem1) {
			        int returnVal = fc.showOpenDialog(Application.this);
//			        System.out.println("File chooser...");

			        if (returnVal == JFileChooser.APPROVE_OPTION) {			            
			            File file = fc.getSelectedFile();
			            String fileName = file.getName();
//						System.out.println("File chooser is choosing the " + fileName + " file");
						try {
							FileInputStream inp = new FileInputStream("C:/Users/Andy/Documents/"+fileName); //work on the automatically attaching the parent directorries
							XSSFWorkbook wb = new XSSFWorkbook(inp);
						    XSSFSheet sheet = wb.getSheetAt(0);
						    
						    int rowNum = sheet.getLastRowNum();  // total number of rows in spreadsheet
						    int colNum = sheet.getRow(1).getLastCellNum();
						    Motor m = new Motor();
						    String[] content = new String[colNum];
						    for (int j = 2; j < rowNum+1; j++) {
						    	XSSFRow row = sheet.getRow(j);  // get row at index j
						    	for(int i=0; i< colNum; i++){					    	
						    		XSSFCell cell = row.getCell(i);									
									content[i] = cell.toString();   // get String at cell at row j									
//									System.out.print(name + "\t\t");
						    	}
//						    	System.out.println("");
						    	m.setInsured(content[1]);
						    	m.setContact(content[2]);
						    	m.setEmail(content[3]);
						    	m.setPostalAddress(content[4]);
						    	m.setCover(content[5]);
						    	m.setInsurer(content[6]);
						    	m.setPolicyNo(content[7]);
						    	m.setCommencmentDate(content[8]);
						    	m.setExpiryDate(content[9]);
						    	try{
							    	m.setPremiumCharged(Float.parseFloat(content[10]));
							    	m.setPaid(Float.parseFloat(content[11]));
						    	} catch(NumberFormatException numExp){
						    		JOptionPane.showMessageDialog(null,
											"Check premium values:\n Are they empty?",
											"Error",
											JOptionPane.ERROR_MESSAGE);
						    		if(content[10] == null) m.setPremiumCharged(0);
						    		else if(content[11] == null) m.setPaid(0);
						    		
						    	}
						    	for(int i=0;i<content.length; i++){
						    		if(content[i] == ""){ content[i] = null; continue; }
						    		System.out.println(i +"\t" + content[i]);
						    		
						    	}
						    	m.save();
						    }
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
//							System.out.println(fileName);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        }			
				}			
			}
		};
	}
}
	
