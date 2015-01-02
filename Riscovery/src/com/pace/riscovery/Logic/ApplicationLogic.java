package com.pace.riscovery.Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pace.riscovery.Motor;
import com.pace.riscovery.views.View;

public class ApplicationLogic implements Logic{

	private File currentFile;
	private View view;
	
	@Override
	public void onOpen() {
		File file = getView().showOpenFileChooser(currentFile);
		if(file!=null){
			if(file.isFile()){
				//Load file
				currentFile = file;
				try {
					FileInputStream inp = new FileInputStream(file.getAbsolutePath()); //work on the automatically attaching the parent directories
					XSSFWorkbook wb = new XSSFWorkbook(inp);
				    XSSFSheet sheet = wb.getSheetAt(0);				    
				    int rowNum = sheet.getLastRowNum();  // total number of rows in spreadsheet
				    int colNum = sheet.getRow(1).getLastCellNum();
//				    Motor m = new Motor();
				    String[] content = new String[colNum];
				    for (int j = 2; j < rowNum+1; j++) {
				    	XSSFRow row = sheet.getRow(j);  // get row at index j
				    	for(int i=0; i< colNum; i++){					    	
				    		XSSFCell cell = row.getCell(i);									
							content[i] = cell.toString();   // get String at cell at row j									
//										System.out.print(name + "\t\t");
				    	}
//							    	System.out.println("");
				    	Motor m = new Motor();
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
//								System.out.println(fileName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			} else {				
				getView().showWarning("Open File", "The file " + file.getName() + " is not a file");
			}
		}
	}
	
	private View getView(){
		if(view == null){
			throw new IllegalStateException("The view is not set");
		}
		return view;
	}

	@Override
	public void setView(View view) {
		this.view = view;		
	}
}
