package model;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	
//	Read column names from database for int values.
	
	
	
	
	
	public final static String[] columnNames = {
		"INSURED", "CONTACT", "EMAIL ADD.", "POSTAL ADD.", "COVER",
		"INSURER", "POLICY NO", "COMM. DATE", "EXP. DATE", 
		"PREM. CHARGED", "PREM. PAID",  "PREM. O/S"
	};
	
//	Read Values for database into values	
	public Object[][] values = {}:		
	
	@Override
	public int getColumnCount() {
		return values.length;
	}

	@Override
	public int getRowCount() {
		return values[0].length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return values[row][column];
	}
	
	public String getColumnName(int column){
		return columnNames[column];
	}

}
